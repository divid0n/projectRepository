package tracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class SessionManager {
	//protected float[] totalHours = new float[5];
	ArrayList<Integer> options = new ArrayList<>();
	float totalHours = 0;
	LocalDate dateNow;
	ArrayList<String> dates = new ArrayList<>();
	ArrayList<Float> hoursPerDay = new ArrayList<>();
	WorkSession session;
	int option;
	float average;

//	public SessionManager(int optionChosen, float hoursWorked) throws IOException {
//		LocalDate date = LocalDate.now();
//		this.option = optionChosen;
//
//		switch (optionChosen) {
//		case 1:
//			session = new WorkSession("TU Wien Admission", hoursWorked, date, optionChosen);
//			break;
//		case 2:
//			session = new WorkSession("Coding Project", hoursWorked, date, optionChosen);
//			break;
//		case 3:
//			session = new WorkSession("Guitar Practice", hoursWorked, date, optionChosen);
//			break;
//		case 4:
//			session = new WorkSession("School Assignments", hoursWorked, date, optionChosen);
//			break;
//		case 5:
//			eraseData();
//			break;
//		case 6:
//			session = new WorkSession("Check Data", hoursWorked, date, optionChosen);
//			readFile();
//			total();
//			showAveragePerSession();
//			break;
//		default:
//			System.out.println("Something went wrong :)");
//		}
//
//	}
	
	public SessionManager(String goalChosen, float hoursWorked, int optionChosen) throws IOException {
		LocalDate date = LocalDate.now();
		
		session = new WorkSession(goalChosen, hoursWorked, date, optionChosen);

	}

	public void writeFile() throws IOException {
		try (FileWriter writer = new FileWriter("sessions.csv", true)) {
			writer.write(session.getOption() + "," + session.getHours() + "," + session.getDate() + "\n");
		}
	}

	public void readFile() throws FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader("sessions.csv"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				options.add(Integer.parseInt(parts[0]));
				dates.add(parts[2]);
				hoursPerDay.add(Float.parseFloat(parts[1]));
				totalHours += Float.parseFloat(parts[1]);
			}
			average = totalHours / dates.size();
		}
	}

	public String today() {
		//session.setTotal(totalHours[session.getOption() - 1]);
		return String.format("Hours worked this session: %.2f%n", session.getHours());
	}

	public String total() {
		String output = "";
		for (int i = dates.size() - 1; i >= 0; i--) {
			output += String.format("Date: %-10s | Goal: %-18s  | Hours worked: %.2f%n", dates.get(i),
					session.getName(options.get(i)), hoursPerDay.get(i));
		}
		return output;
	}
	
	public String showAveragePerSession() {
		return String.format("Average hours worked per session: %.4f%n", average);
	}

	public String eraseData() throws IOException {
		FileWriter writer = new FileWriter("sessions.csv", false);
		writer.write("");
		writer.close();
		return "All data erased successfully.";
	}
	
	public void operate() throws IOException {
		writeFile();
		readFile();
		today();
		total();
	}

}
