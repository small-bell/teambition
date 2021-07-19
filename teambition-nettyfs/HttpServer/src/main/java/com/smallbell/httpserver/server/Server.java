package com.smallbell.httpserver.server;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.core.io.FileSystemResourceLoader;

import com.smallbell.httpserver.utils.FileTransferProperties;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    public static void main(String[] args) {
    	init();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(
            		FileTransferProperties.getInt("port", 10013)).sync();

            channelFuture.addListener(new ChannelFutureListener() {

				public void operationComplete(ChannelFuture future) throws Exception {
                  if(future.isSuccess()){
	                  System.out.println("port " + 
                  FileTransferProperties.getInt("port", 10013)
	                  + " is listening!");
	              }else{
	                  System.out.println("error");
	              }
				}
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
    
    private static void init(){
		FileTransferProperties.load("classpath:systemConfig.properties");
		
		System.setProperty("WORKDIR", FileTransferProperties.getString("WORKDIR","/"));
	}
}
