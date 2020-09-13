package com.lockedme.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.lockedme.exception.FileException;
import com.lockedme.model.FileModel;
import com.lockedme.service.FileService;
import com.lockedme.service.impl.FileServiceImpl;

public class FileMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		System.out.println("Welcome to LockedMe APP V1.0.1 - One Stop Solution for Files Management !");
		System.out.println("--------------------------------");
		System.out.println("Developed By : Deepak Kumar");
		System.out.println("Email - deepak.kumar@prolim.com");

		int ch = 0;

		Scanner scanner = new Scanner(System.in);

		FileService myService = new FileServiceImpl();

		do {

			System.out.println("\nApplication Menu");
			System.out.println("===================");
			System.out.println("1) View all files by name in ascending order");
			System.out.println("2) View list of available operations");
			System.out.println("3) Exit");

			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {

			}

			switch (ch) {
			case 1:
				try {
					List<String> files = myService.getAllFilesNameInAsc();

					if (files != null && files.size() > 0) {
						System.out.println("Found " + files.size() + "files !");

						for (String s : files) {
							System.out.println(s);
						}
					}

				} catch (FileException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:

				int iCh = 0;
				do {
					System.out.println("Select Operation to perform");
					System.out.println("=============================");
					System.out.println("1) Add a file ");
					System.out.println("2) Delete a file");
					System.out.println("3) Search a file ");
					System.out.println("4) View all files with details");
					System.out.println("5) Exit");

					iCh = Integer.parseInt(scanner.nextLine());

					switch (iCh) {
					case 1:
						System.out.println("Enter File Details Below.....");
						FileModel fModel = new FileModel();
						System.out
								.println("Enter Name of the file. (alphanumeric, hyphen and underscore) [5-20 range]");
						fModel.setName(scanner.nextLine());
						System.out.println("Enter Content of the file. (5-1000 characters only.)");
						fModel.setContent(scanner.nextLine());
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						fModel.setDateOfCreation(dtf.format(now));

						try {
							fModel = myService.addFile(fModel);
							System.out.println("File created with details " + fModel);
						} catch (FileException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 2:
						System.out.println("Please Enter File name to be deleted");
						String name = scanner.nextLine();
						try {
							myService.deleteFile(name);
							System.out.println("File with name : " + name + " deleted successfully !");
						} catch (FileException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 3:
						System.out.println("Please Enter File name to be searched");
						String name2 = scanner.nextLine();
						try {
							FileModel fModel2 = new FileModel();
							fModel2 = myService.searchFile(name2);
							System.out.println("File Name " + fModel2.getName() + "found !");
						} catch (FileException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 4:
						try {
							List<FileModel> files = myService.getAllFilesWithDetails();
							for (FileModel f : files) {
								System.out.println("File details : " + f);
							}
						} catch (FileException e) {
							System.out.println(e.getMessage());
						}
						break;

					default:
						System.out.println("Entered choice is invalid it should be numbers between 1-5 only");
						break;
					}
				} while (iCh != 5);
				break;

			default:
				System.out.println("Entered choice is invalid it should be numbers between 1-3 only");
				break;
			}

		} while (ch != 3);

		System.out.println("Thank You For Using The App !!");

	}

}
