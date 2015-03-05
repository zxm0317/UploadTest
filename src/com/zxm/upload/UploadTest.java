package com.zxm.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ֧�ֶϵ�����
 * 
 * @author zxm
 * 
 */
public class UploadTest {
	final String url = "http://xiazai.xiazaiba.com/Phone/W/weipan_3.3.2_XiaZaiBa.apk";
	String filePath = "c://a.apk";

	/**
	 * �ļ�����
	 * 
	 * @param filePath
	 * @param rfile
	 */
	public void downFile(String filePath, final RemoteFile rfile) {
		final long fileSize;
		File f = new File(filePath);
		if (f.exists()) {
			fileSize = f.length();
		} else {
			fileSize = 0;
		}
		if (fileSize < rfile.getSize()) {
			// ���ز���
			new Thread(new Runnable() {
				@Override
				public void run() {
					continteDown(fileSize, rfile.getRealUrl());
				}
			}).start();

		}
	}

	/**
	 * �ϵ�����
	 * 
	 * @param fileSize
	 *            �����ļ���С
	 * @param uri
	 *            ��ʵ�����ַ
	 */
	private void continteDown(long filesize, String uri) {
		RandomAccessFile raf = null;
		HttpURLConnection con = null;
		try {
			URL u = new URL(uri);
			con = (HttpURLConnection) u.openConnection();
			// �������ز���
			con.setRequestProperty("RANGE", "bytes=" + filesize + "-");
			con.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.1.4322)");
			InputStream in = con.getInputStream();
			raf = new RandomAccessFile(filePath, "rw");
			raf.seek(filesize);
			byte[] bytes = new byte[1024];
			int nread = 0;
			long readsize = filesize;
			while ((nread = in.read(bytes, 0, 1024)) > 0) {
				readsize += nread;
				System.out.println("���صĴ�С=" + readsize);
				raf.write(bytes, 0, nread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.disconnect();
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��ȡԶ���ļ���Ϣ
	 * 
	 * @param url
	 * @return
	 */
	public static RemoteFile getRemoteFile(String url) {
		URL u;
		try {
			u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			long size = conn.getContentLength();
			String rurl = conn.getURL().toString();

			return new RemoteFile(size, rurl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		final String url = "http://xiazai.xiazaiba.com/Phone/W/weipan_3.3.2_XiaZaiBa.apk";
		String filePath = "c://a.apk";

		new UploadTest().downFile(filePath, getRemoteFile(url));
	}
}
