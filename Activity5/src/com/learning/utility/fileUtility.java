package com.learning.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class fileUtility {
	private String SDPATH;
	
	public String getSDPath(){
		return SDPATH;
	}

	public fileUtility() {
		// TODO Auto-generated constructor stub
		SDPATH = Environment.getExternalStorageDirectory() + "/";
	}
	
	/**
	 * create file on the SD card
	 */
	public File createSDFile(String filename) throws IOException{
		File file = new File(SDPATH + filename);
		file.createNewFile();
		return file;
	}
	
	/**
	 * create directory on the SD card
	 */
	public File createSDDir(String dirName){
		File dir = new File(SDPATH + dirName);
		dir.mkdir();
		return dir;
	}
	
	/**
	 * check existent
	 */
	public boolean isFileExist(String filename){
		File file = new File(SDPATH + filename);
		return file.exists();
	}
	
	/**
	 * write the file to SD card
	 */
	public File write2SDFromInput(String path, String filename, InputStream input){
		File file = null;
		OutputStream output = null;
		try{
			createSDDir(path);
			file = createSDFile(path + filename);
			output = new FileOutputStream(file);
			byte buffer [] = new byte[4 * 1024];
			while(input.read(buffer) != -1){
				output.write(buffer);
			}
			output.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				output.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return file;
	}
}
