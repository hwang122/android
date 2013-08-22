package com.learning.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDownloader {
	
	private URL url = null;
	/**
	 * Download file according to url
	 * the file should be a text file
	 * the function will return the content
	 */
	public String download(String urlStr){
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		
		try{
			
			//create a url variable
			url = new URL(urlStr);
			//create a http connection
			HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
			//use I/O stream to get the data
			buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			
			//InputStream input = getInputStreamFromUrl(urlStr);
			//buffer = new BufferedReader(new InputStreamReader(input));
			
			while((line = buffer.readLine()) != null){
				sb.append(line);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				buffer.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}	
		return sb.toString();
	}
	
	/**
	 * return value
	 * -1: download failed
	 *  0: download successful
	 *  1: file already exist
	 * 
	 * @param urlStr
	 * @param path
	 * @param filename
	 * @return
	 */
	public int downFile(String urlStr, String path, String filename){
		InputStream inputStream = null;
		try{
			fileUtility fileUtl = new fileUtility();
			
			if(fileUtl.isFileExist(path + filename)){
				return 1;
			}
			else{
				inputStream = getInputStreamFromUrl(urlStr);
				File resultFile = fileUtl.write2SDFromInput(path, filename, inputStream);
				if(resultFile == null){
					return -1;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		finally{
			try{
				inputStream.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}		
		}	
		return 0;
	}
	
	/**
	 * return InputStream from Url
	 * 
	 * @param UrlStr
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStreamFromUrl(String UrlStr) throws IOException{
		InputStream input = null;
		url = new URL(UrlStr);
		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
		urlConn.connect();
		input = urlConn.getInputStream();
		
		return input;
	}
}