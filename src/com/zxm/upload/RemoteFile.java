package com.zxm.upload;

/**
 * ���ڴ洢Զ���ļ���Ϣ
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
