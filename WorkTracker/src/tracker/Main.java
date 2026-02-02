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
		System.out.println("Subgoal 3: Guitar");
		System.out.println("Option 4: Erase all data");

		Scanner skener = new Scanner(System.in);

		int option = skener.nextInt();
		skener.nextLine();

		System.out.println("Press enter when ready.");

		skener.nextLine();
		System.out.println("Start: " + LocalTime.now().truncatedTo(ChronoUnit.MINUTES));

		long startTime = System.currentTimeMillis();

		System.out.println("Press enter when finished.");
		skener.nextLine();

		System.out.println("End: " + LocalTime.now().truncatedTo(ChronoUnit.MINUTES));

		long endTime = System.currentTimeMillis();

		double duration = (endTime - startTime) / (60 * 60 * 1000);

		skener.close();
		SessionManager mngr = new SessionManager(option, duration);
		if (option != 4) {
			mngr.writeFile();
			mngr.readFile();
			mngr.total();
		}

	}
}
