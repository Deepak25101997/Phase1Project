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

		//Application and developer details	
		System.out.println("Welcome to LockedMe APP V1.0.1 - One Stop Solution for Files Management !");
		System.out.println("--------------------------------");
		System.out.println("Developed By : Deepak Kumar");
		System.out.println("Email - deepak.kumar@prolim.com");
		System.out.println("--------------------------------");

		//variable for choice1
		int ch = 0;

		Scanner scanner = new Scanner(System.in);

		FileService myService = new FileServiceImpl();

		do {

			System.out.println("\nApplication Menu");
			System.out.println("===================");
			System.out.println("1) View all files by name in ascending order");
			System.out.println("2) View list of available operations");
			System.out.println("3) Exit");
			System.out.println("===================");

			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {

			}

			switch (ch) {
			case 1:
				try {
					List<String> files = myService.getAllFilesNameInAsc();

					if (files != null && files.size() > 0) {
						System.out.println("\n\nFound " + files.size() + " files !");

						for (String s : files) {
							System.out.println(s);
						}
					}

				} catch (FileException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				//variable for choice 2
				int iCh = 0;
				do {
					System.out.println("\n\nSelect Operation to perform");
					System.out.println("=============================");
					System.out.println("1) Add a file ");
					System.out.println("2) Delete a file");
					System.out.println("3) Search a file ");
					System.out.println("4) View all files with details");
					System.out.println("5) Exit");
					System.out.println("=============================");

					iCh = Integer.parseInt(scanner.nextLine());

					switch (iCh) {
					case 1:
						System.out.println("\nEnter File Details Below.....");
						FileModel fModel = new FileModel();
						System.out.println(
								"\nEnter Name of the file. (alphanumeric, hyphen and underscore) [5-20 range]");
						fModel.setName(scanner.nextLine());
						System.out.println("Enter Content of the file. (5-1000 characters only.)");
						fModel.setContent(scanner.nextLine());
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						fModel.setDateOfCreation(dtf.format(now));

						try {
							fModel = myService.addFile(fModel);
							System.out.println("\nFile created with details " + fModel);
						} catch (FileException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 2:
						System.out.println("\nPlease Enter File name to be deleted");
						String name = scanner.nextLine();
						try {
							myService.deleteFile(name);
							System.out.println("\nFile with name : " + name + " deleted successfully !");
						} catch (FileException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 3:
						System.out.println("\nPlease Enter File name to be searched");
						String name2 = scanner.nextLine();
						try {
							FileModel fModel2 = new FileModel();
							fModel2 = myService.searchFile(name2);
							System.out.println("\nFile Name " + fModel2.getName() + " found !");
						} catch (FileException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 4:
						try {
							List<FileModel> files = myService.getAllFilesWithDetails();
							int cnt = 1;
							for (FileModel f : files) {
								System.out.println("\n\nFile" + cnt + "\nName : " + f.getName() + "\nContent : "
										+ f.getContent() + "\nDate Of Creation : " + f.getDateOfCreation());
								cnt++;
							}
						} catch (FileException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 5:
						System.out.println("\n\nReturning to main menu !!");
						break;

					default:
						System.out.println("\nEntered choice is invalid it should be numbers between 1-5 only");
						break;
					}
				} while (iCh != 5);
				break;

			case 3:
				System.out.println("\n\nThank You For Using The App !!");
				break;

			default:
				System.out.println("\n\nEntered choice is invalid it should be numbers between 1-3 only");
				break;
			}

		} while (ch != 3);

	}

}
