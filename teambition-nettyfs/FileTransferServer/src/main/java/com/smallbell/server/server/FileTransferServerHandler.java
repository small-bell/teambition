package com.smallbell.server.server;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smallbell.server.model.RequestFile;
import com.smallbell.server.model.ResponseFile;
import com.smallbell.server.util.FileTransferProperties;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class FileTransferServerHandler extends ChannelInboundHandlerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(FileTransferServerHandler.class);
	
	private volatile int byteRead;
	private volatile long start = 0;
	
	
	private String file_dir = FileTransferProperties.getString("file_write_path","/");
	
	private RandomAccessFile randomAccessFile; 
	private File file ;
	private long fileSize = -1 ;
	

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof RequestFile) {
			RequestFile ef = (RequestFile) msg;
			byte[] bytes = ef.getBytes();
			byteRead = ef.getEndPos();
			
			String md5 = ef.getFile_md5();
			
			if(start == 0){ 
				String path = file_dir + File.separator + md5+ef.getFile_type();
				file = new File(path);
				fileSize = ef.getFile_size();
				
				if( file.exists() ) {
					log.info("file exists:" + ef.getFile_name()+"--" +ef.getFile_md5() +"[" + ctx.channel().remoteAddress()+"]");
					ResponseFile responseFile = new ResponseFile(start,md5,getFilePath());
					ctx.writeAndFlush(responseFile); 
					
					return ;
				}
				
				randomAccessFile = new RandomAccessFile(file, "rw");
			}
			
			randomAccessFile.seek(start);
			randomAccessFile.write(bytes);
			start = start + byteRead;
			
			if (byteRead > 0 && (start < fileSize && fileSize != -1)) {
				//log.info((start*100)/fileSize+"::::" +fileSize+"::: " +(start*100));
				ResponseFile responseFile = new ResponseFile(start,md5,(start*100)/fileSize);
				ctx.writeAndFlush(responseFile);
			} else {
				log.info("create file success:" +ef.getFile_name()+"--" +ef.getFile_md5() +"[" + ctx.channel().remoteAddress() +"]");
				
				ResponseFile responseFile = new ResponseFile(start,md5,getFilePath());
				ctx.writeAndFlush(responseFile);
				
				randomAccessFile.close();
				file = null ;
				fileSize = -1;
				randomAccessFile = null;
				//ctx.close(); 
			}
		}
	}
	

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		
		if(randomAccessFile != null ){
			try {
				randomAccessFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ctx.close();
	}
	
	private String getFilePath(){
		if( file != null )
			return FileTransferProperties.getString("download_root_path") +"/" + file.getName();
		else 
			return null ;
	}
	
}
