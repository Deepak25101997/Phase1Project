package com.lockedme.model;

public class FileModel {

	private String name;
	private String content;
	private String dateOfCreation;
	
	
	//default constructor
	public FileModel()
	{
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	@Override
	public String toString() {
		return "FileModel [name=" + name + ", content=" + content + ", dateOfCreation=" + dateOfCreation + "]";
	}
	
	public int compareTo(FileModel f)
	{
		String name1 = this.getName();
		String name2 = f.getName();
		
		return name1.compareTo(name2);
	}
	
	
}
