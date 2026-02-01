package tracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WorkSession {

	int totalHours;

	int sessionHours;

	String name;

	LocalDate dateToday;

	int option;

	public WorkSession(String nameName, int hoursWorked, LocalDate date, int inputOption) throws IOException {

		this.name = nameName;
		this.sessionHours = hoursWorked;
		this.dateToday = date;
		this.option = inputOption;

	}

	public String getName() {
		return this.name;
	}

	public int getHours() {
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
			return "Guitar Practice";
		default:
			return "Oopsie";
		}
	}

	public int getOption() {
		return this.option;
	}
	
	public void setTotal(int hours) {
		this.totalHours = hours;

	}
	public int getTotal() {
		return totalHours;
	}
}
