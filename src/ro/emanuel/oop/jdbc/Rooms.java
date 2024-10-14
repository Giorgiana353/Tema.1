package ro.emanuel.oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Rooms {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Properties connectionProps = new Properties();
		connectionProps.put("user", "root");
		connectionProps.put("password", "");
		
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/oop2024",
				connectionProps);
		
		Statement stmt=conn.createStatement();
		
		//insert
		int bNumber = 2;
		String bType = "single";
		int bPrice = 22;
		
		
		String sqInsert = "insert into rooms (Number, Type, Price) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sqInsert);
		ps.setInt(1, bNumber);
		ps.setString(2, bType);
		ps.setInt(3, bPrice);
		
		int rowsAff = ps.executeUpdate();
		System.out.println(rowsAff);
		
		//update
		String update = "UPDATE rooms SET Type = ? WHERE Number = ?";
		PreparedStatement psu = conn.prepareStatement(update);
		psu.setString(1,bType);
		psu.setInt(2, 1);
		psu.executeUpdate();
				
		//delete
		String deleteValue ="DELETE FROM rooms WHERE Number >= ?";
		PreparedStatement ps3=conn.prepareStatement(deleteValue);
		ps3.setInt(1,5);
		int result=ps3.executeUpdate();
		System.out.println(result);

		ResultSet rs=stmt.executeQuery("select * from rooms");
		while(rs.next()) {
			int number=rs.getInt("Number");
			String type=rs.getString("Type");
			int price=rs.getInt("Price");
			
			System.out.println(number +" "+type+" "+price);
			}
		conn.close();
	}

}
