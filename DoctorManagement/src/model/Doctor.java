package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Doctor {
	
	private Connection connect(){   		
		
		Connection con = null; 
	 
		try{     
			Class.forName("com.mysql.jdbc.Driver");           
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare", "root", "");   
			
			
			
		}catch (Exception e){
		  
			e.printStackTrace();
		  
		} 
	  
		return con;  
		
	}
	public String AddDoctor(String name, String dept, Date bday, String phoneno, String address)  {   
		
		String output = ""; 
	 
		try   {    
			Connection con = connect(); 
	 
			if (con == null){
				
				return "Error while connecting to the database for inserting."; 
				
			} 
	 
			String query = "INSERT INTO `doctor`(`did`, `dname`, `dept`, `bday`, `phoneno`, `address`) values (?, ?, ?, ?, ?, ?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
	 
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, dept);
			preparedStmt.setDate(4, bday);    
			preparedStmt.setString(5, phoneno);    
			preparedStmt.setString(6, address); 
	 
	
			preparedStmt.execute();    
			
			con.close(); 
	 
			String newDoc = readDoctorDetails();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newDoc + "\"}";   
			
		}catch (Exception e){    
			
			output = "{\"status\":\"error\", \"data\":"
					+ "\"Error while inserting the item.\"}";
					 System.err.println(e.getMessage());  
			
		} 
	 
	  return output;  
	  
	}
	public String readDoctorDetails(){   
		
		String output = "";
	
		try{    
			Connection con = connect(); 
	   
			if (con == null){
				
				return "Error while connecting to the database for reading."; 
				
			} 
			

			output = "<table border=\"1\">"
					+ "<tr><th>Doctor's Name</th>"
					+ "<th>Department</th>"
					+ "<th>Bithday</th>"
					+ "<th>Phone No</th>"
					+ "<th>Address</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>"; 
	 
			String query = "select * from doctor";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			while (rs.next()){     
				
				String did = Integer.toString(rs.getInt("did"));     
				String dname = rs.getString("dname");
				String dept = rs.getString("dept");
				Date bday = rs.getDate("bday");     
				String phoneno = rs.getString("phoneno");       
				String address = rs.getString("address"); 
			
				
				output += "<tr><td><input id='hidDIDUpdate'"
						+ "name='hidDIDUpdate' "
						+ "type='hidden' value='" + did + "'>" + dname + "</td>"; 
				output += "<td>" + dept + "</td>"; 
				output += "<td>" + bday + "</td>";     
				output += "<td>" + phoneno + "</td>";     
				output += "<td>" + address + "</td>"; 
		 
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						 + "<td><input name='btnRemove' type='button'  value='Remove' class='btnRemove btn btn-danger' data-did='"+ did + "'>"
						 + "</td></tr>"; 
				
			} 

			
	 
			con.close(); 
			output += "</table>";   
			
		}catch (Exception e){    
			
			output = "Error while reading the Doctor's Details.";    
			System.err.println(e.getMessage());   
			
		} 
	 
	  return output;  
	  
	}
	public String updateDoctor(String did, String name, String dept, Date bday, String phoneno, String address){   
		
		String output = ""; 
	 
		try{    
			Connection con = connect(); 
	 
			if (con == null){
				
				return "Error while connecting to the database for updating."; 
				
			} 
	 
			String query = "UPDATE doctor SET dname=?, dept=?, bday=?, phoneno=?, address=? WHERE did=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, dept);
			preparedStmt.setDate(3, bday);     
			preparedStmt.setString(4, phoneno);    
			preparedStmt.setString(5, address);    
			preparedStmt.setInt(6, Integer.parseInt(did)); 
	 
			preparedStmt.execute();    
			con.close(); 
	 
			String newdoc = readDoctorDetails();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newdoc + "\"}"; 
			
			
		}catch (Exception e){    
			
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";     
			System.err.println(e.getMessage());   
			
		} 
	 
	  return output;  
	  
	}
	public String deleteDoctor(String did){   
		
		String output = ""; 
	 
		try{    
			
			Connection con = connect(); 
	 
			if (con == null){
				
				return "Error while connecting to the database for deleting."; 
				
			} 
			
	 
			String query = "Delete from doctor where did=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			preparedStmt.setInt(1, Integer.parseInt(did)); 
	 
			preparedStmt.execute();    
			con.close(); 
	 
			String newdoc = readDoctorDetails();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newdoc + "\"}";  
			
		}catch (Exception e){    
			
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";     
			System.err.println(e.getMessage());   
			
		} 
	 
	  return output;  
	  
	} 
	

}
