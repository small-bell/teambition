package com.smallbell.httpserver.server;

import java.net.URI;

import com.smallbell.httpserver.model.HttpHeaderNames;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;


public class MyTestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {

            // System.out.println("msg type: " + httpObject.getClass());
            System.out.println("client address: " + channelHandlerContext.channel().remoteAddress());
            HttpRequest request = (HttpRequest) httpObject;
            URI uri = new URI(request.getUri());
            System.out.println(uri.toString());

            ByteBuf content = Unpooled.copiedBuffer("hello this is server......", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/palin");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            channelHandlerContext.writeAndFlush(response);
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	ctx.close();
    }
}
