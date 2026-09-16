package operations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DatabaseConnection;

public class WorldDB {
	private static Connection con;
	private static final Scanner scan = new Scanner(System.in);
	static final String RESET = "\u001B[0m";
	static final String CYAN = "\u001B[36m";
	static final String GREEN = "\u001B[32m";
	static final String RED = "\u001B[31m";
	
	
	private static String chooseTable() {
		while(true) {
			System.out.println(CYAN + "\n--- TABULAS ---\n" + RESET
					+ "1. City\n"
					+ "2. Country\n"
					+ "3. CountryLanguage\n"
					+ "0. Atpakal\n"
					+ "Izvelies tabulu: ");
			String c = scan.nextLine().trim();
			
			return switch(c) {
			case "1" -> "city";
			case "2" -> "country";
			case "3" -> "countrylanguage";
			case "0" -> "exit";
			default -> {
				System.out.println("Nepareiza izvele.");
				yield "exit";
				}
			};
		}
	}
	
	private static void tableMenu(String table, SelectOperation selectOp) {
		boolean back = false;
		while(!back) {
			System.out.println("\n---" + table.toUpperCase() + "---\n"
					+ "1. Atlasit (SELECT)\n"
					+ "2. Pievienot (INSERT)\n"
					+ "3. Atjauninat (UPDATE)\n"
					+ "4. Dzest (DELETE)\n"
					+ "0. Atpakal\n"
					+ "Izvele: ");
			String c = scan.nextLine().trim();
			
			switch(c) {
			case "1" -> System.out.println("Jataisa select metode");
			//selectOp.select(conn, table);
			// Turpinajuma bus parejie case
			case "0" -> back = true;
			default -> System.out.println();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			con = DatabaseConnection.getConnection();
			System.out.println("Izveidots savienojums ar datu bazi World!");
			
			SelectOperation selectOp = new SelectOperation();
			//Velak bus vel InsertOperation, DeleteOperation utt.
			
			boolean running = true;
			
			while(running) {
				System.out.println(CYAN + "\n----- WORLD DB -----\n" + RESET
						+ GREEN +  "1. Tabulas \n" 
						+ "2. Skati\n" + RESET
						+ RED + "0. Apturet\n" + RESET
						+ CYAN + "Izvele:" + RESET);
				
				String mainChoice = scan.nextLine().trim();
				
				switch(mainChoice) {
				case "1" ->{
					String table = chooseTable();
					if(!table.equals("exit")) {
						tableMenu(table, selectOp);
					}
				}
				
				case "2" ->
				System.out.println("Bus skati...");
				case "0" -> running = false;
				
				default -> System.out.println(RED + "Nepareiza izvele!" + RESET);
				
				}
			}
				con.close();
				System.out.println("Savienojums ar DB slegts.");
			
		}catch(SQLException e) {
			System.out.println("DB kluda: "+e.getMessage());
		}
	}

}
