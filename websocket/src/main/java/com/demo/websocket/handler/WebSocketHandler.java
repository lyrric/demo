package com.demo.websocket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;

/**
 * Created on 2019/3/10.
 *
 * @author wangxiaodong
 */
public class WebSocketHandler extends ChannelInboundHandlerAdapter {
    /**
     * 用于websocket握手的处理类
     */
    private WebSocketServerHandshaker handshaker;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FullHttpRequest){
            handHttpRequest(ctx, (FullHttpRequest)msg);
        }else{
            handWebSocketRequest(ctx, (WebSocketFrame)msg);
        }
    }

    /**
     * 处理连接请求
     * @param ctx
     * @param request
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request){
        WebSocketServerHandshakerFactory wsFactory =
                new WebSocketServerHandshakerFactory("ws://localhost:9556/ws", null ,false);
        handshaker = wsFactory.newHandshaker(request);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else{
            handshaker.handshake(ctx.channel(), request);
        }
    }

    /**
     * websocket消息
     */
    private void handWebSocketRequest(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        if (msg instanceof CloseWebSocketFrame) {
            // 关闭websocket连接
            handshaker.close(ctx.channel(), (CloseWebSocketFrame)msg.retain());
            return;
        }
        if (msg instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(msg.content().retain()));
            return;
        }
        if (!(msg instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException("当前只支持文本消息，不支持二进制消息");
        }
        if (ctx == null || this.handshaker == null || ctx.isRemoved()) {
            throw new Exception("尚未握手成功，无法向客户端发送WebSocket消息");
        }
        ctx.channel().writeAndFlush(new TextWebSocketFrame(((TextWebSocketFrame)msg).text()));
    }
}
