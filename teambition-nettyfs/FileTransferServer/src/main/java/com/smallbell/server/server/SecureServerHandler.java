package com.smallbell.server.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smallbell.server.model.SecureModel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


public class SecureServerHandler extends ChannelInboundHandlerAdapter {
	private static final Logger log = LoggerFactory.getLogger(SecureServerHandler.class);
    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if( msg  instanceof SecureModel ){
        	SecureModel secureModel = (SecureModel)msg;
        	if(secureModel.getToken() != null ){
    			log.info("NEW TCP >" + ctx.channel().remoteAddress());
    			log.info("now connection count >" +channels.size());
    			
    			channels.add(ctx.channel());
    			secureModel.setAutoSuccess(true);
            	ctx.writeAndFlush(secureModel);
    			return ;
        	}
        	secureModel.setAutoSuccess(false);
        	ctx.writeAndFlush(secureModel);
        	ctx.close();
        }else{
        	if( !channels.contains(ctx.channel()) ) {
        		SecureModel secureModel = new SecureModel();
        		secureModel.setAutoSuccess(false);
            	ctx.writeAndFlush(secureModel);
            	ctx.close();
        	} else {
        		ctx.fireChannelRead(msg);  
        	}
        }
    }
    
    
    @Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}


}
