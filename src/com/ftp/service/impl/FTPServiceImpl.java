package com.ftp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.ftp.service.FTPService;

public class FTPServiceImpl implements FTPService {

	private static final String thisClassName = FTPServiceImpl.class.getName();
	private static Logger logger = Logger.getLogger(FTPServiceImpl.class.getSimpleName());
	private FTPClient ftpClient;

		

	@Override
	public  boolean ftpUploadFile(String sourcePath, String destinationPath, String serverName, String port,
			String userName, String password) {

		String thisMethodName = "ftpUploadFile";
		logger.logp(Level.INFO, thisClassName, thisMethodName, " Enter " + thisMethodName);

		ftpClient = null;
		InputStream inputStream = null;
		File inputFile;

		try {

			ftpClient = new FTPClient();

			try {
				ftpClient.connect(serverName, Integer.parseInt(port));
			} catch (Exception e) {
				ftpClient.connect(serverName, 21);
			}

			ftpClient.login(userName, password);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			logger.logp(Level.INFO, thisClassName, thisMethodName,
					" Trying to connect to serverName " + serverName + " port " + port + " username " + userName);

			if (!ftpClient.isConnected()) {
				logger.logp(Level.INFO, thisClassName, thisMethodName, " Unable to connect to serverName " + serverName
						+ " port " + port + " username " + userName + " Please validate credentials.");
			}

			inputFile = new File(sourcePath);
			inputStream = new FileInputStream(inputFile);

			logger.logp(Level.INFO, thisClassName, thisMethodName,
					" Input Source FileName " + sourcePath + "\n Output Destination FileName " + destinationPath);
			logger.logp(Level.INFO, thisClassName, thisMethodName, " Starting to upload file.");

			return ftpClient.storeFile(destinationPath, inputStream);

		} catch (Exception e) {
			logger.logp(Level.WARNING, thisClassName, thisMethodName,
					" Exception while  uploading ile to FTP destination folder: ", e);
		} finally {
			try {

				if (inputStream != null) {
					inputStream.close();
				}

			} catch (Exception e) {
				logger.logp(Level.WARNING, thisClassName, thisMethodName,
						" Exception while  closing input file stream : ", e);
			}

			try {

				if (ftpClient != null && ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}

			} catch (Exception e) {
				logger.logp(Level.WARNING, thisClassName, thisMethodName, " Exception while  closing FTP Client: ", e);
			}
		}
		return false;
	}

}
