package org.main.java;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class FilesStat {
	private Map<String, Integer> stat;
	private FileContentSaveRead fileReaderSaver;
	
	public FilesStat() {
		stat = new HashMap<>();
		fileReaderSaver = new FileContentSaveRead("example.txt");
	}
	
	public FilesStat(String inputFileName) {
		stat = new HashMap<>();
		fileReaderSaver = new FileContentSaveRead(inputFileName);
	}
	
	public FilesStat(String input, String output) {
		stat = new HashMap<>();
		fileReaderSaver = new FileContentSaveRead(input, output);
	}
	
	
	
	public static void main(String[] args) {
		FilesStat fileStat = null;
		if(args.length != 0) {
			fileStat = new FilesStat(args[0], args[1]);
		} else {
			fileStat = new FilesStat("examples.zip");
		}
		FileContentSaveRead readerSaver = fileStat.getFileReaderSaver();
		String str =  "";
		if(readerSaver.getInputFiles().size() > 1) {
			str = readerSaver.readFiles();
		} else {
			str = readerSaver.readFromFile();			
		}
		fileStat.resolveStat(str);
		
		
	}
	
	public void resolveStat(String str) {
		String[] tokens = str.split("\\W+");
		for (String string : tokens) {
			if (!stat.containsKey(string)) {
				stat.put(string, 1);
			} else {
				Integer key = stat.get(string);
				key++;
				stat.put(string, key);
			}
		}
		
		for (Map.Entry<String, Integer> entry : stat.entrySet()) {
			System.out.println(entry.getKey() + " <-> " + entry.getValue());
		}
		fileReaderSaver.writeOutputToFile(stat);
	}
	
	public Map<String, Integer> getStat() {
		return stat;
	}

	public void setStat(Map<String, Integer> stat) {
		this.stat = stat;
	}

	public FileContentSaveRead getFileReaderSaver() {
		return fileReaderSaver;
	}

	public void setFileReaderSaver(FileContentSaveRead fileReaderSaver) {
		this.fileReaderSaver = fileReaderSaver;
	}


	
	

	
	
}
