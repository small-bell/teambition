package com.smallbell.client.model;

import java.io.File;
import java.io.Serializable;

/**
 * 构造上传文件
 */
public class RequestFile implements Serializable {

	
	private static final long serialVersionUID = -1425307876096494974L;
	
	// 文件
	private File file;
	// 文件名
	private String file_name;
	// 开始位置
	private long starPos;
	// 文件字节数组
	private byte[] bytes;
	// 结尾位置
	private int endPos;
	//文件的MD5值
	private String file_md5; 
	//文件类型
	private String file_type;  
	//文件总长度
	private long file_size; 

	public long getStarPos() {
		return starPos;
	}

	public void setStarPos(long starPos) {
		this.starPos = starPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFile_md5() {
		return file_md5;
	}

	public void setFile_md5(String file_md5) {
		this.file_md5 = file_md5;
	}
	
	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	
	
	
}
