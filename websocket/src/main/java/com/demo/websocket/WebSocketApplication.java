package com.demo.websocket;

import com.demo.websocket.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.awt.*;

/**
 * Created on 2019/3/10.
 *
 * @author wangxiaodong
 */
public class WebSocketApplication {

    /**
     * 端口
     */
    private static final int PORT = 9556;

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast("http-code", new HttpServerCodec())
                                .addLast("aggregator", new HttpObjectAggregator(65535))
                                .addLast("http-chunked", new ChunkedWriteHandler())
                                .addLast("handler", new WebSocketHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 5)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            Channel channel = bootstrap.bind(PORT).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
