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

			case "country" -> updateCountry(con);
			
			case "countrylanguage" -> updateCountryLanguage(con);
			
			default -> System.out.println("Neatbalstita tabula: "+table);
			}
		}catch(SQLException e) {
			System.out.println("update kluda: "+ e.getMessage());
		}
	}

	private void updateCity(Connection con) throws SQLException{
		String CountryCode;
		System.out.println("Kuru pilsetu labot? (noradi ID)");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.println("Noradi pilsetas nosaukumu");
		String name = scan.nextLine();
		do {
			System.out.println("Noradi valsts kodu (3 simboli");
			CountryCode = scan.nextLine();
			}while(CountryCode == null || CountryCode.chars().anyMatch(Character::isDigit) || CountryCode.length() > 3 || CountryCode.length() < 3);
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
	
	private void updateCountry(Connection con) throws SQLException{
		System.out.println("Ievadi valsts nosaukumu");
		String name = scan.nextLine();
		System.out.println("Ievadi valsts kodu (3 simboli");
		String Code = scan.nextLine();
		System.out.println("Noradi kontinentu");
		String Continent = scan.nextLine();
		System.out.println("Noradi regionu");
		String Region = scan.nextLine();
		System.out.println("Noradi valsts izmeru");
		double SurfaceArea = scan.nextDouble();
		System.out.println("Noradi neatkaribas datumu");
		int IndepYear = scan.nextInt();
		System.out.println("Noradi videju dzives gadu skaitu");
		double LifeExpectancy = scan.nextDouble();
		System.out.println("Noradi populaciju");
		int Population = scan.nextInt();
		System.out.println("Noradi GNP");
		double GNP = scan.nextDouble();
		System.out.println("Noradi veco GNP");
		double GNPOld = scan.nextDouble();
		scan.nextLine();
		System.out.println("Noradi vietejo vardu");
		String LocalName = scan.nextLine();
		System.out.println("Noradi valdibas formu");
		String GovernmentForm = scan.nextLine();
		System.out.println("Noradi valsts valdnieku");
		String HeadOfState = scan.nextLine();
		System.out.println("Noradi galvaspilsetas ID");
		String Capital = scan.nextLine();
		System.out.println("Noradi otro valsts kodu (2 burti)");
		String Code2 = scan.nextLine();
		
		String sql = "UPDATE country SET Name = ?, Continent = ?, Region = ?, SurfaceArea = ?, IndepYear = ?, LifeExpectancy = ?, Population = ?, GNP = ?, GNPOld = ?, LocalName = ?, GovernmentForm = ?, HeadOfState = ?, Capital = ?, Code2 = ? WHERE Code = ?;";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, Continent);
			ps.setString(3, Region);
			ps.setDouble(4, SurfaceArea);
			ps.setInt(5, IndepYear);
			ps.setDouble(6, LifeExpectancy);
			ps.setInt(7, Population);
			ps.setDouble(8, GNP);
			ps.setDouble(9, GNPOld);
			ps.setString(10, LocalName);
			ps.setString(11, GovernmentForm);
			ps.setString(12, HeadOfState);
			ps.setString(13, Capital);
			ps.setString(14, Code2);
			ps.setString(15, Code);
			int rows = ps.executeUpdate();
			System.out.println("COUNTRY tabula ir ievietotas: "+rows+" rindas");
	}
	}
	private void updateCountryLanguage(Connection con) throws SQLException{
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
		
		String sql = "INSERT INTO countrylanguage (CountryCode, Language, IsOfficial, Percentage) VALUES (?, ?, ?, ?)";
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
