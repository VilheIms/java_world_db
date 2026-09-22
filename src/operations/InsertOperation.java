package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertOperation {
	static Scanner scan = new Scanner(System.in);
		public void insert(Connection con, String table) {
			try {
				switch(table) {
					case "city" -> insertCity(con);
					//Vel vajag country un countrylanguage
					default -> System.out.println("Neatbalstita tabula: "+table);
				}
			}catch(SQLException e) {
				System.out.println("Insert Kluda: "+e.getMessage());
			}
	}

		private void insertCity(Connection con) throws SQLException{
			//Trukst ievades datu parbaude
			System.out.println("Ievadi pilsetas nosaukumu");
			String name = scan.nextLine();
			System.out.println("Ievadi valsts kodu (3 simboli");
			String CountryCode = scan.nextLine();
			System.out.println("Noradi apgabalu");
			String district = scan.nextLine();
			System.out.println("Noradi iedzivotaju skaitu");
			int population = scan.nextInt();
			scan.nextLine();
			
			String sql = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, name);
				ps.setString(2, CountryCode);
				ps.setString(3, district);
				ps.setInt(4, population);
				int rows = ps.executeUpdate();
				System.out.println("CITY tabula ir ievietotas: "+rows+" rindas");
			}
		}
}
