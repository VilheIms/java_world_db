package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateOperation {
	static Scanner scan = new Scanner(System.in);
	
	public void update(Connection con, String table) {
		try {
			switch(table) {
			case "city" -> updateCity(con);
			//Pasiem vajag parejas tabulas pievienot
			
			default -> System.out.println("Neatbalstita tabula: "+table);
			}
		}catch(SQLException e) {
			System.out.println("update kluda: "+ e.getMessage());
		}
	}

	private void updateCity(Connection con) throws SQLException{
		System.out.println("Kuru pilsetu labot? (noradi ID)");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.println("Noradi pilsetas nosaukumu");
		String name = scan.nextLine();
		System.out.println("Noradi valsts kodu (3 simboli");
		String CountryCode = scan.nextLine();
		System.out.println("Noradi apgabalu");
		String district = scan.nextLine();
		System.out.println("Noradi iedzivotaju skaitu");
		int population = scan.nextInt();
		scan.nextLine();
		
		String sql = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, CountryCode);
			ps.setString(3, district);
			ps.setInt(4, population);
			ps.setInt(5, id);
			int rows = ps.executeUpdate();
			System.out.println("CITY tabula ir atjaunotas: "+rows+" rindas");
		}
		
	}
}
