package config;

import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

import model.PathBuilderMapValue;
import utils.MyFileVisitor;

public class PathBuilder {

	private Map<String, PathBuilderMapValue> paths;

	public Map<String, PathBuilderMapValue> buildFilePaths(String glob, Path filePath) {
		this.paths = new TreeMap<>();
		MyFileVisitor mfv = new MyFileVisitor(filePath, glob);
		for (Path path : mfv.getPaths()) {
			String fileName = this.createFileName(
					path.getName(path.getNameCount() - 1).toString());
			String fileKey = this.createFileKey(
					fileName);
			PathBuilderMapValue value = new PathBuilderMapValue();
			value.setPath(path);
			value.setFileName(fileName);
			this.paths.put(fileKey, value);
		}
		mfv = null;
		return this.paths;
	}
	
	private String createFileKey(final String fileName) {
		return fileName.replaceAll("_\\d+\\.xlsx", "");
	}

	private String createFileName(final String fileName) {
		String date = fileName.substring(fileName.lastIndexOf("_") + 1, fileName.lastIndexOf("."));
		String invertedDate = new StringBuilder(date).reverse().toString();
		String newFileName = fileName.replaceAll("\\d+", invertedDate);
		return newFileName;
	}
	
}
