import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main{
	
	public static void main(String[] args) {
		//CONNECT TO THE DATABASE
		String url = "jdbc:mysql://localhost:3306/toyshop";
		String username = "root";
		String password = "SlytherinPr1de";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			
			System.out.println("Connected to the database.");

			//START PROGRAM
			Scanner firstScan = new Scanner(System.in);
			System.out.println("Please select an option: " + "\n" 
						+ "1. Enter Toy" + "\n"
						+ "2. Update Toy" + "\n"
						+ "3. Delete Toy" + "\n"
						+ "4. Search Toy" + "\n"
						+ "0. Exit");
					
			String userSelect = firstScan.nextLine();
			switch(userSelect) {
			
			case "1":
				//run method to add a toy to database	NAME: createMethod
				createMethod();
				break;
			
			case "2":
				//run method to updated a toy in the database NAME: updateMethod
				updateMethod();
				break;
				
			case "3":
				//run method to remove a toy from the database NAME: deleteMethod
				deleteMethod();
				break;
				
			case "4":
				//run method to search for a toy in database NAME: searchMethod
				searchMethod();
				break;
				
			case "0":
				//exit the program
				System.out.println("Thank you for using the system! Have a good day!");
				break;
			}

			

				connection.close();
				firstScan.close();
			
		} catch (SQLException e) {
			System.out.println("Error in connection - recheck code.");
			e.printStackTrace();
		}
		
		
	}
	
	static void createMethod() {
		// the connection
		String url = "jdbc:mysql://localhost:3306/toyshop";
		String username = "root";
		String password = "SlytherinPr1de";
		
		try {
			Connection conCreate = DriverManager.getConnection(url,username,password);
			System.out.println("Connected to Database - start with record creation:");
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter the id of the new toy: ");
			String newToyid = scanner.nextLine();
			System.out.println("Please enter the Name of the new Toy: ");
			String newToyName = scanner.nextLine();
			System.out.println("Please enter the new Company: ");
			String newToyComp = scanner.nextLine();
			System.out.println("Please enter the current quantity of the toy: ");
			String newToyQuant = scanner.nextLine();
			
			String insertToy = "INSERT INTO toys (id, Toy, Company, Qty) VALUES (?,?,?,?)";
			
			PreparedStatement statement = conCreate.prepareStatement(insertToy);
			statement.setString(1, newToyid);
			statement.setString(2, newToyName);
			statement.setString(3, newToyComp);
			statement.setString(4, newToyQuant);
			
			int rows = statement.executeUpdate();
				if (rows >0) {
					System.out.println("The new toy has been inserted.");
				}
				scanner.close();
			
			
		}catch(SQLException e) {
			System.out.println("Method Error - check code and retry.");
			e.printStackTrace();
		}
		
	}
	
	static void updateMethod() {
	    // the connection
	    String url = "jdbc:mysql://localhost:3306/toyshop";
	    String username = "root";
	    String password = "SlytherinPr1de";

	    try {
	        Connection conUpdate = DriverManager.getConnection(url, username, password);
	        System.out.println("Connected to Database - start with record update:");

	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Please enter the id of the item you wish to update: ");
	        String toyToUpdate = scanner.nextLine();
	        System.out.println("Please enter the new toy name: ");
	        String name = scanner.nextLine();
	        System.out.println("Please enter the new company: ");
	        String company = scanner.nextLine();
	        System.out.println("Please enter the new quantity: ");
	        String qty = scanner.nextLine();
	        scanner.close();

	        String sql = "UPDATE toys SET id = ?, company = ?, Qty = ? WHERE id = ?";
	        PreparedStatement statement = conUpdate.prepareStatement(sql);
	        
	        statement.setString(1, name);
	        statement.setString(2, company);
	        statement.setString(3, qty);
	        statement.setString(4, toyToUpdate);

	        int rowsUpdated = statement.executeUpdate();

	        if(rowsUpdated > 0) {
	            System.out.println("The existing record for toy ID " + toyToUpdate + " has been successfully updated.");
	        }

	        statement.close();
	        conUpdate.close();
	    }catch(SQLException e) {
	        System.out.println("Method Error - check code and retry.");
	        e.printStackTrace();
	    }
	}
	
	static void deleteMethod() {
		//setting connection
			String url = "jdbc:mysql://localhost:3306/toyshop";
			String username = "root";
			String password = "SlytherinPr1de";
			
		try {
			Connection conDelete = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to Database - start with selction and deletion: ");
			
			Scanner infoDelete = new Scanner(System.in);
			System.out.println("Please enter the id of the toy you wish to delete from the server: ");
			String toyDelete = infoDelete.nextLine();
			
			String sql = "DELETE FROM toys WHERE id=?";
			
			PreparedStatement statement = conDelete.prepareStatement(sql);
			statement.setString(1, toyDelete);
			
			int rowsDeleted = statement.executeUpdate();
				if(rowsDeleted > 0) {
					System.out.println("The record of this toy has been deleted successfully.");
					
					infoDelete.close();
				}

		}catch (SQLException e) {
			System.out.println("Method Error - check code and retry.");
			e.printStackTrace();
		}
			
	}
	
	static void searchMethod() {
		//setting up connection to database
		String url = "jdbc:mysql://localhost:3306/toyshop";
		String username = "root";
		String password = "SlytherinPr1de";
		
		try {
			
			Connection conView = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to Database - start selection of data to view: ");
			
			Scanner viewRow = new Scanner(System.in);
			System.out.println("Please enter the ID of the toy you wish to view: ");
			String viewData = viewRow.nextLine();
			
			Statement statement = conView.createStatement();		
			String sql = "SELECT id, Toy, Company, Qty FROM toys WHERE id=" + viewData;
			ResultSet rs =statement.executeQuery(sql);
			

				while(rs.next()) {
					int id = rs.getInt("id");
					String toy = rs.getString("Toy");
					String company = rs.getString("Company");
					int Qty = rs.getInt("Qty");
					
					System.out.println("ID: " + id);
					System.out.println("Toy: " + toy);
					System.out.println("Company: " + company);
					System.out.println("Quantity: " + Qty);
				}
			
				viewRow.close();
				
		}catch (SQLException e) {
			System.out.println("Method Error - check code and retry.");
			e.printStackTrace();
		}

	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}