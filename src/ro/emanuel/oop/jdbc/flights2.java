package ro.emanuel.oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class flights2 {

    public static void main(String[] args) throws SQLException {
       
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "");
        
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/oop2024",
                connectionProps);
        
        Statement stmt = conn.createStatement();
        
        // Inserare
        int flightNumber = 33;
        String city = "Budapesta";
        String time = "2024-10-01";
        
        String sqlInsert = "INSERT INTO flights2 (Flight, City, Time) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlInsert);
        ps.setInt(1, flightNumber);
        ps.setString(2, city);
        ps.setString(3, time);
        
        int rowsAffected = ps.executeUpdate();
        System.out.println("Rânduri afectate la inserare: " + rowsAffected);
        
        //update
        String update = "UPDATE flights2 SET City = ? WHERE Flight = ?";
        PreparedStatement psu = conn.prepareStatement(update);
        psu.setString(1, "Bucuresti");
        psu.setInt(2, 33);
        psu.executeUpdate();
        
        //delete
        String deleteValue = "DELETE FROM flights2 WHERE Flight >= ?";
        PreparedStatement ps3 = conn.prepareStatement(deleteValue);
        ps3.setInt(1, 100); // 
        int result = ps3.executeUpdate();
        System.out.println("Rânduri șterse: " + result);

      
        ResultSet rs = stmt.executeQuery("SELECT * FROM flights2");
        while (rs.next()) {
            int number = rs.getInt("Flight");
            String cityName = rs.getString("City");
            String flightTime = rs.getString("Time");
            
            System.out.println("Flight: " + number + ", City: " + cityName + ", Time: " + flightTime);
        }
        
        conn.close();
    }
}
