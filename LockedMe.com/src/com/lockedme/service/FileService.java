package com.lockedme.service;

import java.util.List;

import com.lockedme.exception.FileException;
import com.lockedme.model.FileModel;

public interface FileService {

	// add file
	public FileModel addFile(FileModel file) throws FileException;
	
	// delete a file with user specified name
	public void deleteFile(String name) throws FileException;
	
	// get all the file names in ascending order
	public List<String> getAllFilesNameInAsc() throws FileException;
	
	//search a file with given name
	public FileModel searchFile(String name) throws FileException;
	
	//get all the files with detailed information
	public List<FileModel> getAllFilesWithDetails() throws FileException;
	
}
