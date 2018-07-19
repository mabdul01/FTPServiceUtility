package com.ftp.service.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ftp.service.FTPService;
import com.ftp.service.impl.FTPServiceImpl;



public class FTPClient {

	
	private static final String CLASS_NAME = FTPClient.class.getName();
	private static final Logger logger = Logger.getLogger(FTPClient.class.getSimpleName());

	
	public static void main( String[] args ) throws Exception {

		logger.logp(Level.INFO, CLASS_NAME, "main", "FTP sending starts");
		boolean ftpStatus = false;
		
		FTPService  ftpService =  new FTPServiceImpl();
		
		ftpStatus = ftpService.ftpUploadFile("", "", "", "", "", "");
		
		if(!ftpStatus) {
			logger.logp(Level.INFO, CLASS_NAME, "main", "FTP failed " );
		} else {
			logger.logp(Level.INFO, CLASS_NAME, "main", "FTP sending completed" );			
		}
		

		
	}

}
