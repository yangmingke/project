package com.network.monitor.util.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	public static class ZipEntryData {
		private final String name;
		private byte[] datas;

		public ZipEntryData(String name) {
			this.name = name;
		}

		public byte[] getDatas() {
			return datas;
		}

		public void setDatas(byte[] datas) {
			this.datas = datas;
		}

		public ZipEntry toZe() {
			ZipEntry ze = new ZipEntry(name);
			ze.setSize(datas.length);
			ze.setTime(System.currentTimeMillis());
			return ze;
		}

	}

	public final static void zip(OutputStream out, List<ZipEntryData> zipEntries)
			throws IOException {
		ZipOutputStream zip = new ZipOutputStream(out);
		ZipEntry ze = null;
		for (ZipEntryData zeInner : zipEntries) {
			ze = zeInner.toZe();
			zip.putNextEntry(ze);
			zip.write(zeInner.getDatas());
			zip.flush();
			zip.closeEntry();
		}
		zip.finish();
		zip.close();
	}

	public final static byte[] inputStream2bytes(InputStream inputStream)
			throws Exception {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		inputStream.close();
		baos.flush();
		return baos.toByteArray();
	}

	public static void main(String[] args) throws Exception {
		ByteArrayOutputStream outTmp = new ByteArrayOutputStream();
		List<ZipEntryData> zipEntries  = new ArrayList<ZipUtils.ZipEntryData>();
		
		ZipEntryData data = new ZipEntryData("E:/aa.xml");
		byte [] date = "aaaa".getBytes();
		data.setDatas(date);
		
		ZipEntryData data1 = new ZipEntryData("E:/bb.xml");
		byte [] date1 = "bbbb".getBytes();
		data1.setDatas(date1);
		
		zipEntries.add(data);
		zipEntries.add(data1);
		
		zip(outTmp, zipEntries);
		
	}
}
