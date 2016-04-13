package org.main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileContentSaveRead {

	List<File> inputFiles;
	File outputFile;
	Zipper zipper;
	FileOutputStream fos;
	FileInputStream fis;
	BufferedWriter bw;
	BufferedReader br;

	public FileContentSaveRead(String str) {
		inputFiles = new ArrayList<>();
		inputFiles.add(new File(str));
		outputFile = new File(str.substring(0, str.indexOf('.')) + "_statistic.txt");
		createFile();
	}

	public FileContentSaveRead(String inputName, String outputName) {
		if ((inputName.contains(File.separator) && !inputName.contains(File.separator + File.separator))) {
			inputName = inputName.replaceAll(File.separator, File.separator + File.separator);
		} else if (outputName.contains(File.separator) && !outputName.contains(File.separator + File.separator)) {
			outputName = outputName.replaceAll(File.separator, File.separator + File.separator);
		}
		inputFiles = new ArrayList<>();
		inputFiles.add(new File(inputName));
		outputFile = new File(outputName);
		createFile();
	}

	private void generateExample() {
		String str = "Sed ut perspiciatis unde omnis iste natus error sit "
				+ "voluptatem accusantium doloremque laudantium, totam rem aperiam,"
				+ " eaque ipsa quae ab illo inventore veritatis et quasi architecto "
				+ "beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia "
				+ "voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni"
				+ " dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est,"
				+ " qui dolorem. Nemo enim ipsam voluptatem quia "
				+ "voluptas sit aspernatur aut odit aut fugit doloremque laudantium Nemo enim ipsam";
		writeInputExampleToFile(str);
	}

	public void createFile() {
		try {
			if (!isZip()) {
				if (inputFiles.get(0).createNewFile()) {
					System.out.println("File " + inputFiles.get(0).getName() + " was created!");
					if (inputFiles.get(0).length() == 0) {
						this.generateExample();
					}
				} else {
					System.out.println("File " + inputFiles.get(0).getName() + " already exists!");
				}
			} else {
				zipper = new Zipper(inputFiles.get(0));
				zipper.unZip();
				inputFiles = zipper.getFileList();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createFile(String str) {

	}

	public void writeInputExampleToFile(String str) {
		try {
			fos = new FileOutputStream(inputFiles.get(0));
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(str);
			bw.close();
			System.out.println("Content was wrote to file!");
		} catch (FileNotFoundException e) {
			System.err.println("Error writeToFile");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error writeToFile");
			e.printStackTrace();
		}
	}

	public void writeOutputToFile(Map<String, Integer> stat) {
		try {
			fos = new FileOutputStream(outputFile);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (Map.Entry<String, Integer> entry : stat.entrySet()) {
				bw.write(entry.getKey() + " <-> " + entry.getValue());
				bw.newLine();
			}
			bw.close();
			System.out.println("Content was wrote to file!");
		} catch (FileNotFoundException e) {
			System.err.println("Error writeToFile");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error writeToFile");
			e.printStackTrace();
		}
	}

	public String readFromFile() {
		return readFromFile(inputFiles.get(0));
	}

	public String readFiles() {
		StringBuilder content = new StringBuilder();
		for (File file : inputFiles) {
			if(!file.isDirectory()) {
				content.append(readFromFile(file));
				
			}
		}
		return content.toString();
	}

	public String readFromFile(File file) {
		StringBuilder content = new StringBuilder();

		try {
			String line;
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				content.append(line);
			}
			br.close();
			System.out.println("Content was red to file!");
			System.out.println(content);
		} catch (FileNotFoundException e) {
			System.err.println("Error readFromFile");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error readFromFile");
			e.printStackTrace();
		}

		return content.toString();

	}

	public boolean isZip() {
		return Zipper.isZip(inputFiles.get(0));
	}

	public List<File> getInputFiles() {
		return inputFiles;
	}

	public void setInputFiles(List<File> inputFiles) {
		this.inputFiles = inputFiles;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

}
