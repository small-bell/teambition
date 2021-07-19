package com.smallbell.teambition.fastdfs.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

//#fastdfs.connect_timeout_in_seconds = 5 
//#fastdfs.network_timeout_in_seconds = 30 
//#fastdfs.charset = UTF‐8 
//#fastdfs.http_anti_steal_token = false 
//#fastdfs.http_secret_key = FastDFS1234567890 
//#fastdfs.http_tracker_http_port = 80 
public class FastDFSUtil {
	
	/**
	 * @param localFile
	 * @param postfix
	 * @return 服务器组名和文件名
	 */
	public static String[] upload(String localFile, String postfix) {
		try {
			ClientGlobal.init("fastdfs-client.properties");
			TrackerClient tc = new TrackerClient();
			TrackerServer ts = tc.getTrackerServer();
			StorageServer ss = tc.getStoreStorage(ts);
			StorageClient sc = new StorageClient(ts,ss);
			String[] result = sc.upload_appender_file(localFile, postfix, null);
			return result;
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void download(String groupName, String remoteFileName, String localFileName) {
		try {
			ClientGlobal.init("fastdfs-client.properties");
			TrackerClient tc = new TrackerClient();
			TrackerServer ts = tc.getTrackerServer();
			StorageServer ss = tc.getStoreStorage(ts);
			StorageClient sc = new StorageClient(ts,ss);
			sc.download_file(groupName, remoteFileName,localFileName);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param groupName
	 * @param remoteFileName
	 * @return 0是成功删除，其他都是删除失败
	 */
	public static int delete(String groupName, String remoteFileName) {
		try {
			ClientGlobal.init("fastdfs-client.properties");
			TrackerClient tc = new TrackerClient();
			TrackerServer ts = tc.getTrackerServer();
			StorageServer ss = tc.getStoreStorage(ts);
			StorageClient sc = new StorageClient(ts,ss);
			int result = sc.delete_file(groupName, remoteFileName);
			return result;
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
}
