package com.flypaas.hdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flypaas.utils.SysConfig;

public class FastDfsClient {
	private static final Logger logger = LoggerFactory.getLogger(FastDfsClient.class);
	private static StorageClient storageClient = null;
	
	static {
		try {
			ClientGlobal.init(SysConfig.config_file_path);
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			storageClient = new StorageClient(trackerServer, null);
		} catch (Throwable e) {
			logger.error("FastDFS文件服务器初始化失败", e);
		}
	}

	public FastDfsClient() {
	}

	/**
	 * 下载文件
	 * 
	 * @param remote_filename
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MyException
	 */
	public byte[] download(String remote_filenpath) throws FileNotFoundException, IOException, MyException {
		int i = remote_filenpath.substring(1).indexOf("/");
		String group_name = remote_filenpath.substring(1, i + 1);
		String remote_filename = remote_filenpath.substring(i + 2);
		byte[] byteArray = storageClient.download_file(group_name, remote_filename);
		return byteArray;
	}
}
