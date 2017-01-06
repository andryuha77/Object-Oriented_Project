package ie.gmit.sw.client;

import java.util.Scanner;

public class Menu {
	private Scanner in;
	private String input;

	public Menu() throws Exception {
		input = "";
		in = new Scanner(System.in);
	}

	protected void menuSelection() throws Exception {
		int choice;
		String menuOption = "";
		while ((menuOption != null) && (!(menuOption.equals("4")))) {
			menuOption = getMenuOption();
			if (menuOption != null) {
				choice = Integer.parseInt(menuOption);
				if (choice != 4) {
					switch (choice) {
					case 1:
						System.out.println("1");
						break;

					case 2:
						System.out.println("2");
						break;

					case 3:
						System.out.println("3");
						break;
					}
				}
			}
		}
	}

	private String getMenuOption() {

		String menuOptions = "\n\nOptions: ";
		menuOptions += "\n 1.Connect to Server";
		menuOptions += "\n 2.Print File Listing";
		menuOptions += "\n 3.Download File";
		menuOptions += "\n 4.Quit";
		menuOptions += "\n \n Type Option [1-4]>";
		boolean validInput = false;
		String selection = "", menuChoicePattern = "[1-4]{1}";
		String errorMessage = "Invalid menu selection";
		errorMessage += "\n\nValid options are 1 to 4 inclusive.";
		errorMessage += "\nPlease retry with valid Number.";
		while (!validInput) {
			System.out.println("Choose number of option you wish to have executed" + menuOptions);
			selection = in.nextLine();
			if ((selection == null) || (selection.matches(menuChoicePattern)))
				validInput = true;
			else
				System.out.println(errorMessage + "Error in user input");
		}
		return selection;
	}

}
