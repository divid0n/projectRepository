package tracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SessionManager {
	protected double[] totalHours = { 0, 0, 0 };
	ArrayList<Integer> options = new ArrayList<>();
	int hours;
	LocalDate dateNow;
	ArrayList<String> dates = new ArrayList<>();
	ArrayList<Double> hoursPerDay = new ArrayList<>();
	WorkSession session;
	int option;

	public SessionManager(int optionChosen, double hoursWorked) throws IOException {
		LocalDate date = LocalDate.now();
		this.option = optionChosen;

		switch (optionChosen) {
		case 1:
			session = new WorkSession("TU Wien Admission", hoursWorked, date, optionChosen);
			break;
		case 2:
			session = new WorkSession("Coding Project", hoursWorked, date, optionChosen);
			break;
		case 3:
			session = new WorkSession("Guitar Practice", hoursWorked, date, optionChosen);
			break;
		case 4:
			eraseData();
			break;
		default:
			System.out.println("Something went wrong :)");
		}

	}

	public void writeFile() throws IOException {
		try (FileWriter writer = new FileWriter("sessions.csv", true)) {
			writer.write(session.getOption() + "," + session.getHours() + "," + session.getDate() + "\n");
			System.out.println("Hours logged.");
		}
	}

	public void readFile() throws FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader("sessions.csv"))) {
			String line;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				options.add(Integer.parseInt(parts[0]));
				totalHours[options.get(i) - 1] += Double.parseDouble(parts[1]);
				dates.add(parts[2]);
				hoursPerDay.add(Double.parseDouble(parts[1]));
				i++;
			}

		}
	}

	public void total() {
		session.setTotal(totalHours[session.getOption() - 1]);
		System.out.println("Total hours worked on " + session.getName() + ": " + totalHours[session.getOption() - 1]);
		for (int i = dates.size() - 1; i >= 0; i--) {

			System.out.println("Date: " + dates.get(i) + " | Goal: " + session.getName(options.get(i))
					+ " | Hours worked: " + hoursPerDay.get(i));
		}
	}

	public void eraseData() throws IOException {
		FileWriter writer = new FileWriter("sessions.csv", false);
		writer.write("");
		writer.close();
		System.out.println("All data erased successfully.");
	}

}
