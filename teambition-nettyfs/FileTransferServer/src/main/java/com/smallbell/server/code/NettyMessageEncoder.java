package com.smallbell.server.code;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

import com.smallbell.server.util.ObjectConvertUtil;

/**
 * 信息编码器
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg,
			List<Object> out) throws Exception {
		out.add(ObjectConvertUtil.request(msg));
	}

}
