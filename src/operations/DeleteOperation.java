package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteOperation {
	static Scanner scan = new Scanner(System.in);
	
	public void delete(Connection con, String table) {
		try {
			switch(table) {
			case "city" -> deleteCity(con);
			// country un language VAJAG
			
			default -> System.out.println("Neatbalstita tabula: "+table);
			}
		}catch(SQLException e) {
			System.out.println("DELETE Kluda: "+e.getMessage());
		}
	}

	private void deleteCity(Connection con) throws SQLException{
		System.out.println("Noradi pilsetas ID, kuru velies dzest: ");
		int id = scan.nextInt();
		scan.nextLine();
		
		String sql = "DELETE FROM city WHERE ID = ? ";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			System.out.println("CITY tabula dzesti "+rows+ " ieraksti");
		}
	}
}
