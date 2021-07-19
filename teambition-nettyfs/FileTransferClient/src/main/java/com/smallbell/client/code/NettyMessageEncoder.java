package com.smallbell.client.code;

import java.util.List;

import com.smallbell.client.util.ObjectConvertUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;


public class NettyMessageEncoder extends MessageToMessageEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg,
			List<Object> out) throws Exception {
		out.add(ObjectConvertUtil.request(msg));
	}

}
