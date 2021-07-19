package com.smallbell.client.code;

import java.util.List;

import com.smallbell.client.util.ObjectConvertUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;


public class NettyMessageDecoder extends MessageToMessageDecoder<Object> {

	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg,
			List<Object> out) throws Exception {
		String o = msg.toString();
		Object outobj = ObjectConvertUtil.convertModle(o);
		out.add(outobj);
	}

    
}
