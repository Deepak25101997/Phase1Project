package com.lockedme.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lockedme.dao.FileDao;
import com.lockedme.exception.FileException;
import com.lockedme.model.FileModel;

public class FileDaoImpl implements FileDao {

	//name of file as key and file object as value
	private static Map<String,FileModel> fileMap = new HashMap<>();
	
	@Override
	public FileModel addFile(FileModel file) throws FileException {
		//store file object in the map
		fileMap.put(file.getName(), file);
		return file;
	}

	@Override
	public void deleteFile(String name) throws FileException {
		// TODO Auto-generated method stub
		if(fileMap.containsKey(name)) {
			fileMap.remove(name);
		}
		else {
			throw new FileException("Deletion Failed. File Not Found !");
		}
	}

	@Override
	public List<String> getAllFilesNameInAsc() throws FileException {
		
		if(fileMap.size()==0) {
			throw new FileException("No files exist currently ! Please add a file.");
		}

		List<String> fileNames= new ArrayList<>(fileMap.keySet());
		
		return fileNames;
	
	}

	@Override
	public FileModel searchFile(String name) throws FileException {
		if(fileMap.containsKey(name)) {
			return fileMap.get(name);
		}
		else {
			throw new FileException("Deletion Failed. File Not Found !");
		}
	}

	@Override
	public List<FileModel> getAllFilesWithDetails() throws FileException {
		if(fileMap.size()==0) {
			throw new FileException("No files exist currently ! Please add a file.");
		}
		
		return new ArrayList<>(fileMap.values());
	}

}








