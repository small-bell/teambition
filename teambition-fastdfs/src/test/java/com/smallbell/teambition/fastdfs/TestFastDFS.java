package com.smallbell.teambition.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

public class TestFastDFS {
    @Test
    public void testUpload(){
        //通过fastDSF的client代码访问tracker和storage
        try {
            //加载fastDFS客户端的配置 文件
            ClientGlobal.initByProperties("fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            //创建tracker的客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage的客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            //文件元信息
            NameValuePair[] metaList = new NameValuePair[1];
            metaList[0] = new NameValuePair("fileName", "1.png");
            //执行上传
            String fileId = client.upload_file1("C:\\Users\\admin\\Desktop\\1.png", "png", metaList);
            System.out.println("upload success. file id is: " + fileId);
            //关闭trackerServer的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //查询 文件上传
    @Test
    public void testQuery(){
        try {
            //加载fastDFS客户端的配置 文件
            ClientGlobal.initByProperties("fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            //创建tracker的客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage的客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            FileInfo group1 = client.query_file_info("group1", "M00/00/02/wKhlQFrKX0mATW_LAALcAg10vf4198.png");
            FileInfo fileInfo = client.query_file_info1("group1/M00/00/02/wKhlQFrKX0mATW_LAALcAg10vf4198.png");
            System.out.println(group1);
            System.out.println(fileInfo);
            //查询文件元信息
            NameValuePair[] metadata1 = client.get_metadata1("group1/M00/00/02/wKhlQFrKX0mATW_LAALcAg10vf4198.png");
            System.out.println(metadata1);
            //关闭trackerServer的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //下载 文件上传
    @Test
    public void testDownload(){
        try {
            //加载fastDFS客户端的配置 文件
            ClientGlobal.initByProperties("fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            //创建tracker的客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage的客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            byte[] bytes = client.download_file1("group1/M00/00/02/wKhlQFrKX0mATW_LAALcAg10vf4198.png");
            File file = new File("d:/a.png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
            //关闭trackerServer的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
