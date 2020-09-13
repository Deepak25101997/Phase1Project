package com.lockedme.service.impl;

import java.util.List;

import com.lockedme.dao.FileDao;
import com.lockedme.dao.impl.FileDaoImpl;
import com.lockedme.exception.FileException;
import com.lockedme.model.FileModel;
import com.lockedme.service.FileService;

public class FileServiceImpl implements FileService {

	FileDao dao = new FileDaoImpl();

	@Override
	public FileModel addFile(FileModel file) throws FileException {

		if (!isValidName(file.getName())) {
			throw new FileException(
					"Please Enter File Name (alphanumeric, hyphen and underscore allowed) in range 5-20 characters !");
		}

		if (!isValidContent(file.getContent())) {
			throw new FileException("Please Enter content on the range 5-1000 characters only !");
		}

		return dao.addFile(file);
	}

	private boolean isValidName(String name) {
		boolean b = false;
		if (name.trim().matches("[a-zA-Z0-9_-]{5,20}")) {
			b = true;
		}
		return b;
	}

	private boolean isValidContent(String content) {
		boolean b = false;
		if (content.length() > 5 && content.length() < 1000) {
			b = true;
		}
		return b;
	}

	@Override
	public void deleteFile(String name) throws FileException {
		if (!isValidName(name)) {
			throw new FileException(
					"Invalid Input. Enter File Name (alphanumeric, hyphen and underscore allowed) in range 5-20 characters !");
		}

		dao.deleteFile(name);
	}

	@Override
	public List<String> getAllFilesNameInAsc() throws FileException {

		return dao.getAllFilesNameInAsc();
	}

	@Override
	public FileModel searchFile(String name) throws FileException {

		if (isValidName(name)) {
			throw new FileException(
					"Invalid Input. Enter File Name (alphanumeric, hyphen and underscore allowed) in range 5-20 characters !");
		}

		return dao.searchFile(name);
	}

	@Override
	public List<FileModel> getAllFilesWithDetails() throws FileException {
		return dao.getAllFilesWithDetails();
	}

}
