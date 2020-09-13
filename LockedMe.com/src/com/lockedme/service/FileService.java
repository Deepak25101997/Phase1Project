package com.lockedme.service;

import java.util.List;

import com.lockedme.exception.FileException;
import com.lockedme.model.FileModel;

public interface FileService {

	public FileModel addFile(FileModel file) throws FileException;
	public void deleteFile(String name) throws FileException;
	
	public List<String> getAllFilesNameInAsc() throws FileException;
	public FileModel searchFile(String name) throws FileException;
	public List<FileModel> getAllFilesWithDetails() throws FileException;
	
}
