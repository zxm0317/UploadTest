package com.zxm.upload;

/**
 * 用于存储远程文件信息
 * 
 * @author Administrator
 * 
 */
public class RemoteFile {
	private long size;
	private String realUrl;
	
	
	public RemoteFile(long size, String realUrl) {
		this.size = size;
		this.realUrl = realUrl;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getRealUrl() {
		return realUrl;
	}
	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}
}
