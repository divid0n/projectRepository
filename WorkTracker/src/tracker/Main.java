package tracker;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("What are you working on today?");
		System.out.println("Goal 1: TU Wien Admission");
		System.out.println("Subgoal 2: Coding projects");
		System.out.println("Subgoal 3: Guitar Practice");
		System.out.println("Option 4: Erase all data"); // make an option just to display data
		System.out.println("Option 5: Check data"); // make it so that this also displays the total of each and maybe even averages...

		Scanner skener = new Scanner(System.in);

		int option = skener.nextInt();
		skener.nextLine();
		
		float duration = 0;
		
		if(option == 5) {
			SessionManager mngr = new SessionManager(option, duration);
			mngr.readFile();
			mngr.total();
		}

		

		if (option < 4) {
			System.out.println("Press enter when ready.");

			skener.nextLine();
			System.out.println("Start: " + LocalTime.now().truncatedTo(ChronoUnit.MINUTES));

			long startTime = System.currentTimeMillis();

			System.out.println("Press enter when finished.");
			skener.nextLine();

			System.out.println("End: " + LocalTime.now().truncatedTo(ChronoUnit.MINUTES));

			long endTime = System.currentTimeMillis();

			duration = (endTime - startTime) / 36000f;

			skener.close();
		

			SessionManager mngr = new SessionManager(option, duration);
			
			mngr.writeFile();
			mngr.readFile();
			mngr.today();
			mngr.total();
		}

	}
}
