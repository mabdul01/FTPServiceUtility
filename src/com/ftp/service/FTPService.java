package com.ftp.service;

public interface FTPService {
	
	/**
	 * FTP a file from local file system source path to destination path in the given FTP server.
	 * @param sourcePath
	 * @param destinationPath
	 * @param serverName
	 * @param port
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean ftpUploadFile(String sourcePath, String destinationPath, String serverName, String port,
			String userName, String password);
	
	
}
