package com.flypaas.admin.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flypaas.admin.util.ConfigUtils;

/**
 * FastDFS文件服务器客户端
 * 
 * @author xiejiaan
 */
public class FastDfsClient {
	private static final Logger logger = LoggerFactory
			.getLogger(FastDfsClient.class);
	static {
		try {
			ClientGlobal.init(ConfigUtils.config_file_path);
		} catch (Throwable e) {
			logger.error("FastDFS文件服务器初始化失败", e);
		}
	}

	public static String upload(String filePath) {
		String fileExtName = filePath.substring(filePath.lastIndexOf(".") + 1);
		File file = new File(filePath);
		byte[] file_buff = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			if (fis != null) {
				int len = fis.available();
				file_buff = new byte[len];
				fis.read(file_buff);
			}
		} catch (Exception e) {
			throw new RuntimeException("upload file fail! : "
					+ e.getLocalizedMessage(), e);
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}
		TrackerClient tc = new TrackerClient();
		TrackerServer ts = null;
		StorageClient sc = null;
		String group_name = null;
		String remote_filename = null;
		try {
			ts = tc.getConnection();
			sc = new StorageClient(ts, null);
			StorageServer[] storageServers = tc
					.getStoreStorages(ts, group_name);
			if (storageServers == null) {
				logger.debug("get store storage servers fail, error code: "
						+ sc.getErrorCode());
			} else {
				logger.debug("store storage servers count: "
						+ storageServers.length);
				for (int k = 0; k < storageServers.length; k++) {
					logger.debug("list[" + (k + 1) + "] :  "
							+ storageServers[k].getInetSocketAddress());
				}
			}
			long startTime = System.currentTimeMillis();
			NameValuePair[] metaList = new NameValuePair[1];
			metaList[0] = new NameValuePair("fileName", file.getName());
			String[] results = sc.upload_appender_file(file_buff, fileExtName,
					metaList);
			logger.debug("upload_file time used: "
					+ (System.currentTimeMillis() - startTime) + " ms");
			if (results == null) {
				throw new RuntimeException("upload file fail, error code: "
						+ tc.getErrorCode());
			}
			group_name = results[0];
			remote_filename = results[1];
			logger.debug("group_name: " + group_name + ", remote_filename: "
					+ remote_filename);
			logger.debug(sc.get_file_info(group_name, remote_filename)
					.toString());
			ServerInfo[] servers = tc.getFetchStorages(ts, group_name,
					remote_filename);
			if (servers == null) {
				throw new RuntimeException("no server, error code: "
						+ tc.getErrorCode());
			} else {
				logger.debug("storage servers count: " + servers.length);
				for (int k = 0; k < servers.length; k++) {
					logger.debug(k + 1 + ". " + servers[k].getIpAddr() + ":"
							+ servers[k].getPort());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("upload file fail! : "
					+ e.getLocalizedMessage(), e);
		} finally {
			closeServer(ts);
		}
		return "/" + group_name + "/" + remote_filename;
	}

	private static final void closeServer(TrackerServer ts) {
		if (null != ts) {
			try {
				ts.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param remotePath
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	public static final byte[] download(String remotePath) throws IOException {
		TrackerClient tc = new TrackerClient();
		TrackerServer ts = null;
		StorageClient sc = null;
		byte[] byteArray = null;
		try {
			int i = remotePath.substring(1).indexOf("/");
			String group_name = remotePath.substring(1, i + 1);
			String remote_filename = remotePath.substring(i + 2);
			ts = tc.getConnection();
			int jj = 3;
			boolean isOk = false;
			while (jj > 0) {
				try {
					if (ProtoCommon.activeTest(ts.getSocket())) {
						isOk = true;
						break;
					}
					jj--;
					closeServer(ts);
					ts = tc.getConnection();
				} catch (Exception e) {
					jj--;
					closeServer(ts);
					ts = tc.getConnection();
				}
			}
			if (isOk) {
				sc = new StorageClient(ts, null);
				byteArray = sc.download_file(group_name, remote_filename);
				logger.debug("download [ " + remotePath + " ]  success!");
			} else {
				logger.debug("download [ " + remotePath
						+ " ]  fail! can not connection fastdfs server!");
			}
		} catch (Exception e) {
			logger.error("download by [ " + remotePath + " ]  Error : " + e, e);
			throw new IOException("download by [ " + remotePath
					+ " ]  Error : " + e);
		} finally {
			closeServer(ts);
		}
		return byteArray;
	}

	public static final void delete(String remote_filename) throws IOException {
		TrackerClient tc = new TrackerClient();
		TrackerServer ts = null;
		StorageClient sc = null;
		String group_name = "group1";
		try {
			int jj = 3;
			boolean isOk = false;
			while (jj > 0) {
				try {
					if (ProtoCommon.activeTest(ts.getSocket())) {
						isOk = true;
						break;
					}
					jj--;
					closeServer(ts);
					ts = tc.getConnection();
				} catch (Exception e) {
					jj--;
					closeServer(ts);
					ts = tc.getConnection();
				}
			}
			if (isOk) {
				ts = tc.getConnection();
				sc = new StorageClient(ts, null);
				sc.delete_file(group_name, remote_filename);
				logger.debug("delete [ " + (group_name + "/" + remote_filename)
						+ " ] success.");
			} else {
				logger.debug("delete [ " + (group_name + "/" + remote_filename)
						+ " ] fail.");
			}
		} catch (Exception e) {
			logger.error("delete by [ " + (group_name + "/" + remote_filename)
					+ " ]  Error : " + e, e);
			throw new IOException("delete by [ "
					+ (group_name + "/" + remote_filename) + " ]  Error : " + e);
		} finally {
			closeServer(ts);
		}

	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException, MyException {
		ConfigUtils.config_file_path = "D:/JAVA_HOME/ws/w2/flypaas-task/src/config/config_devtest.properties";
		for (int i = 0; i < 1000; i++) {
			new Thread() {
				@Override
				public void run() {
					// try {
					// String remotePath = FastDfsClient
					// .upload("E:/asb.xls");
					// logger.debug(
					// "\nisActive={}\nhttp://172.16.10.32:9190/flypaas-task/download?remotePath={}",
					// FastDfsClient.isActive(), remotePath);
					// } catch (Exception e) {
					// e.printStackTrace();
					// }

					try {
						download("/group1/M00/00/12/rBAKIFS0yDYEAAAAAAAAACepI30135.xls");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
}
