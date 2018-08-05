package com.demo.elastic.search.dao;

import com.alibaba.fastjson.JSONObject;
import com.demo.elastic.search.core.EsTransPortClient;
import com.demo.elastic.search.ennum.EsEnum;
import com.demo.elastic.search.entity.BaseEntity;
import com.demo.elastic.search.util.EsAnnotationUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

/**
 * Created on 2018/6/11.
 *
 * @author wangxiaodong
 */
public abstract class BaseDao<T extends BaseEntity> {

    private Class<T> clazz;

    private EsTransPortClient esTransPortClient;

    @SuppressWarnings("unchecked")
    BaseDao(EsTransPortClient esTransPortClient) {
        this.esTransPortClient = esTransPortClient;
        //获取当前实体的class
        //先获取超类的类型(从子类的角度看)->BaseDao<User>
        Type genType = getClass().getGenericSuperclass();
        //获取泛型类型数据,可能有多个如Map<K,V>
        Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
        clazz = (Class<T>) types[0];
    }

    /**
     * 插入操作
     * @param t 待插入的数据,需继承BaseEntity
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void insert(T t) throws ExecutionException, InterruptedException {
        if(t == null){
            throw new NullPointerException("The data can not be null.");
        }
        String json = JSONObject.toJSONString(t);
        esTransPortClient.getClient().prepareIndex(EsEnum.INDEX.getValue(), EsAnnotationUtil.getTypeByClass(t.getClass()), t.getId().toString())
                .setSource(json, XContentType.JSON)
                .execute();
    }

    /**
     * 通过主键Id查找
     * @param id not null
     * @return
     */
    public T findById(Integer id){
        if(id == null){
            throw new NullPointerException("id must not be null");
        }
        TransportClient client = esTransPortClient.getClient();
        BoolQueryBuilder queryBuilders = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("id",id));
        SearchRequestBuilder requestBuilder = client.prepareSearch(EsEnum.INDEX.getValue())
                .setQuery(queryBuilders)
                .setTypes(EsAnnotationUtil.getTypeByClass(clazz));
        SearchResponse response = requestBuilder.get();
        SearchHit[] hits = response.getHits().getHits();
        T t = null;
        if(hits != null && hits.length > 0){
            t = JSONObject.parseObject(hits[0].getSourceAsString(),  clazz);
        }
        return t;
    }
}
