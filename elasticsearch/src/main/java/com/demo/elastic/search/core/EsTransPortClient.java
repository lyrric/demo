package com.demo.elastic.search.core;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created on 2018/6/8.
 * es连接简单实现
 * @author wangxiaodong
 */
@Component
public class EsTransPortClient {

    private final Logger logger = LoggerFactory.getLogger(EsTransPortClient.class);

    private final String clusterName = "data-test";

    private final String nodeStr = "10.28.100.31:59303,10.28.100.31:59304,10.28.100.30:59303,10.28.100.30:59304";

    private volatile TransportClient transportClient;


    public TransportClient getClient(){
        if(transportClient == null){
            Settings settings = Settings.builder()
                    .put("cluster.name", clusterName)
                    .put("client.transport.sniff", false)
                    .build();
            transportClient = new PreBuiltTransportClient(settings);
            //加入节点
            String[] nodes = nodeStr.split(",");
            for (String node : nodes){
                String[] temp = node.split(":");
                try {
                    //ES版本5.3
                    transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(temp[0]), Integer.valueOf(temp[1])));
                    //ES版本6.2
                    //transportClient.addTransportAddress( new TransportAddress(inetAddress, Integer.valueOf(temp[1])));
                    logger.info("es加入加点{}",node);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    transportClient = null;
                    return transportClient;
                }
            }
        }
        return transportClient;
    }
}
