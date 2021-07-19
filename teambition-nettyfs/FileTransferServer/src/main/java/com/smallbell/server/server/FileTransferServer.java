package com.smallbell.server.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.core.io.FileSystemResourceLoader;

import com.smallbell.server.util.FileTransferProperties;


public class FileTransferServer {
	
	private Logger log = Logger.getLogger(FileTransferServer.class);
	
	public void bind(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.childHandler(new FileChannelInitializer());
			
			log.info("bind port:"+port);
			
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		init();
		int port  = FileTransferProperties.getInt("port",10012);
		
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		try {
			new FileTransferServer().bind(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void init(){
		try
        {
			FileTransferProperties.load("classpath:systemConfig.properties");
			
			System.setProperty("WORKDIR", FileTransferProperties.getString("WORKDIR","/"));
			
            PropertyConfigurator.configure(new FileSystemResourceLoader().getResource(
                    "classpath:log4j.xml").getInputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
	}
}
