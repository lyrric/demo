import com.alibaba.fastjson.JSONObject;
import com.demo.elastic.search.core.EsTransPortClient;
import com.demo.elastic.search.dao.UserDao;
import com.demo.elastic.search.ennum.EsEnum;
import com.demo.elastic.search.entity.User;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * Created on 2018/6/11.
 *
 * @author wangxiaodong
 */
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.demo.elastic.search"})
@ContextConfiguration(classes =  EsTest.class)
public class EsTest {


    @Autowired
    private UserDao userDao;
    @Autowired
    private EsTransPortClient esTransPortClient;


    @Test
    public void search() throws UnknownHostException {
        TransportClient client = esTransPortClient.getClient();
        BoolQueryBuilder queryBuilders = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchPhraseQuery("username","测试"));
        SearchRequestBuilder requestBuilder = client.prepareSearch(EsEnum.INDEX.getValue())
                .setQuery(queryBuilders);
        SearchResponse response = requestBuilder.get();
        SearchHit[] hits = response.getHits().getHits();

        for(SearchHit hit : hits){
            User user = JSONObject.parseObject(hit.getSourceAsString(), User.class);
            if(user != null){
                System.out.println(JSONObject.toJSONString(user));
            }
        }
    }
    @Test
    public void find() throws UnknownHostException, ExecutionException, InterruptedException {
        User user = userDao.findById(1);
        System.out.println(JSONObject.toJSONString(user));
    }
}
