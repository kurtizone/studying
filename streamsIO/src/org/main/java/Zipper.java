package org.main.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Zipper {

	private File sourceFile;
	private List<File> fileList;

	public Zipper() {
	}

	public Zipper(File file) {
		if (isZip(file)) {
			this.sourceFile = file;
		}
		fileList = new ArrayList<>();
	}

	@SuppressWarnings("resource")
	public static boolean isZip(File file) {
		boolean isZipped = false;
		try {
			isZipped = new ZipInputStream(new FileInputStream(file)).getNextEntry() != null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isZipped;
	}

	public void unZip() {
		unZip(sourceFile);
	}

	public void unZip(File sourceFile) {

		try {

			String zipPath = sourceFile.getName().substring(0, sourceFile.getName().length() - 4);
			File temp = new File(zipPath);
			if (!temp.exists()) {
				temp.mkdir();
			}
			System.out.println("Folder " + zipPath + "   created");

			ZipFile zipFile = new ZipFile(sourceFile);
			Enumeration e = zipFile.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				File destinationPath = new File(zipPath, entry.getName());
				fileList.add(destinationPath);
				destinationPath.getParentFile().mkdirs();

				if (entry.isDirectory()) {
					continue;
				} else {
					System.out.println("Extracting " + destinationPath);

					BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
					int b;
					byte[] buffer = new byte[1024];
					FileOutputStream fos = new FileOutputStream(destinationPath);
					BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);

					while ((b = bis.read(buffer, 0, 1024)) != -1) {
						bos.write(buffer, 0, b);
					}
					bos.flush();
					bos.close();
				}
			}

		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

}
