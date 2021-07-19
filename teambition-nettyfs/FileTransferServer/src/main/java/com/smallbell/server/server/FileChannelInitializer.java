package com.smallbell.server.server;

import com.smallbell.server.code.NettyMessageDecoder;
import com.smallbell.server.code.NettyMessageEncoder;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * 初始化channel
 */
public class FileChannelInitializer extends ChannelInitializer<Channel> {

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ch.pipeline().addLast(new ObjectEncoder());
		ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null))); 
		
		ch.pipeline().addLast(new NettyMessageDecoder());
		ch.pipeline().addLast(new NettyMessageEncoder());
        
		ch.pipeline().addLast(new SecureServerHandler());
		ch.pipeline().addLast(new FileTransferServerHandler());
	}

}
