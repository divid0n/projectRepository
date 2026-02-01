package tracker;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("What are you working on today?");
		System.out.println("Goal 1: TU Wien Admission");
		System.out.println("Subgoal 2: Coding projects");
		System.out.println("Subgoal 3: Guitar");
		System.out.println("Option 4: Erase all data");

		Scanner skener = new Scanner(System.in);
		int option = skener.nextInt();

		System.out.println("How many hours have you worked?");

		int hoursWorked = skener.nextInt();

		skener.close();
		SessionManager mngr = new SessionManager(option, hoursWorked);
		if(option != 4) {
			mngr.writeFile();
			mngr.readFile();
			mngr.total();
		}
		
		
	}
}
