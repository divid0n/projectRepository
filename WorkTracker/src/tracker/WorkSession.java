package tracker;

import java.io.IOException;
import java.time.LocalDate;

public class WorkSession {

	float totalHours;

	float sessionHours;
	
	int numberOfSessions = 0;

	String name;

	LocalDate dateToday;

	int option;

	public WorkSession(String nameName, float hoursWorked, LocalDate date, int inputOption) throws IOException {

		this.name = nameName;
		this.sessionHours = hoursWorked;
		this.dateToday = date;
		this.option = inputOption;
		totalHours += hoursWorked;
		
		
	}

	public String getName() {
		return this.name;
	}

	public float getHours() {
		return this.sessionHours;
	}

	public LocalDate getDate() {
		return this.dateToday;
	}

	public String getName(int id) {
		switch (id) {
		case 1:
			return "TU Wien Admission";
		case 2:
			return "Coding Projects";
		case 3:
			return "School Assignments";
		case 4:
			return "Guitar Practice";
		case 5:
			return "Gaming (slacking off)";
		default:
			return "Oopsie";
		}
	}

	public int getOption() {
		return this.option;
	}

	public void setTotal(float hours) {
		this.totalHours = hours;

	}

	public float getTotal() {
		return totalHours;
	}
	
	public float getAverage() {
		return totalHours / numberOfSessions;
	}
}
