package com.cs330meals.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class IngredientFacade {
	
	public static String getIngredientByIdJSON(int id)
		{
		Gson gson = new Gson();
		return gson.toJson(getIngredientById(id));
		}
	public static String getIngredientByNameJSON(String name)
	{
		Gson gson = new Gson();
		return gson.toJson(getIngredientByName(name));
	}
	public static String getIngredientListJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(getIngredientList());
	}
	
	public static Ingredient getIngredientById(int id) 
	{
		Connection con=null;
		Ingredient ing=null;
		try
		{
			MealsDataSourceSingleton ds=MealsDataSourceSingleton.getInstance();
			con =ds.getConnection();

			//Statement stmt = con.createStatement();
			PreparedStatement stmt = con.prepareStatement("SELECT id, name, category, aveUnitPrice, unitMeasurement FROM ingredient WHERE id = ?");
			stmt.setInt(1,  id);
			
			ResultSet rs;
			//change the query construction later
			rs = stmt.executeQuery();


		 	
		 	//get count of rows returned
		 	int size =0;  
		 	if (rs != null)   
		 	{  
		 	  rs.beforeFirst();  
		 	  rs.last();  
		 	  size = rs.getRow();  
		 	} 
		 	rs.beforeFirst();

		 	//Build ingredient list from result set
		 	String strbuf=""; //just for debugging

		 	
		 	if (rs.next() )
		 	{
		 		int x = rs.getInt("id");
		 		String s = rs.getString("name");
		 		String c = rs.getString("category");
		 		double a = rs.getDouble("aveUnitPrice");
		 		String u = rs.getString("unitMeasurement");
		 		
		 		ing = new Ingredient(x, s, c,a,u);

		 	
		 		//strbuf just for debugging
		 		strbuf+="id: "+x+ " , name: "+s+"<br />";
		 	}//end if

		  	
		}//end try
 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*finally
		{
			try
			{
				con.close();
			}
		
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 
		}*/
		return ing;
		}
	
		public static Ingredient getIngredientByName(String name) 
		{
		Ingredient ing=null;
		try
		{
		//Class.forName("com.mysql.jdbc.Driver") ;
		//Connection con = DriverManager.getConnection(
		       // "jdbc:mysql://localhost:3306/test1",
		        //"root",
		       // "Shalina69");
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource dataSource = (DataSource)envContext.lookup("jdbc/testdb");
		Connection con = dataSource.getConnection();

		//Statement stmt = con.createStatement();
		PreparedStatement stmt = con.prepareStatement("SELECT id, name, category, aveUnitPrice, unitMeasurement FROM ingredient WHERE name = ?");
		stmt.setString(1,  name);
		ResultSet rs;
		//change the query construction later
		rs = stmt.executeQuery();


		 	
		 	//get count of rows returned
		 	int size =0;  
		 	if (rs != null)   
		 	{  
		 	  rs.beforeFirst();  
		 	  rs.last();  
		 	  size = rs.getRow();  
		 	} 
		 	rs.beforeFirst();

		 	//Build ingredient list from result set
		String strbuf=""; //just for debugging

		 	
		if (rs.next() ) {
		 	int x = rs.getInt("id");
		String s = rs.getString("name");
		String c = rs.getString("category");
 		double a = rs.getDouble("aveUnitPrice");
 		String u = rs.getString("unitMeasurement");
		
		ing = new Ingredient(x, s, c, a, u);

		 	
		//strbuf just for debugging
		 	strbuf+="id: "+x+ " , name: "+s+"<br />";

		 	
		}//end if

		 	
		}//end try
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();


		} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}


		return ing;
		}
		
		public static Ingredient[] getIngredientList() {
			
			
			Ingredient[] ingA=null;
			try {
			Class.forName("com.mysql.jdbc.Driver") ;
			Connection con = DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/mealplan",
			        "root",
			        "Shalina69");

			Statement stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT id, name, category, aveUnitPrice, unitMeasurement FROM ingredient");


			 	
			 	//get count of rows returned
			 	int size =0;  
			 	if (rs != null)   
			 	{  
			 	  rs.beforeFirst();  
			 	  rs.last();  
			 	  size = rs.getRow();  
			 	} 
			 	rs.beforeFirst();

			 	//Build ingredient list from result set
			String strbuf=""; //just for debugging
			 	ingA = new Ingredient[size];
			int i=0;
			 	while (rs.next() ) {
			 	int x = rs.getInt("id");
			String s = rs.getString("name");
			String c = rs.getString("category");
	 		double a = rs.getDouble("aveUnitPrice");
	 		String u = rs.getString("unitMeasurement");
			Ingredient ing = new Ingredient(x, s, c,a,u);

			 	 	
			//strbuf just for debugging
			 	strbuf+="id: "+x+ " , name: "+s+"<br />";


			ingA[i] = ing;
			i++;
			}//end while

			 	
			}//end try
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}


			return ingA;
			}//end getIngredientList
		
		public static String addIngredient(Ingredient ingToAdd) 
		{
		Ingredient ing=null;
		int retVal=-1;
		
		
		try
		{
		Class.forName("com.mysql.jdbc.Driver") ;
		Connection con = DriverManager.getConnection(
		        "jdbc:mysql://localhost:3306/mealplan",
		        "root",
		        "Shalina69");

		Statement stmt = con.createStatement();
		//ResultSet rs;
		String theName=ingToAdd.getName();
		String theCategory=ingToAdd.getCategory();
		String theUnitMeasurement=ingToAdd.getMeasurement();
		double theAveUnitPrice=ingToAdd.getPrice();
		System.out.println(theName);
		//change the query construction later
		//rs = stmt.executeQuery("SELECT id, name FROM ingredient WHERE id ="+id);
		retVal = stmt.executeUpdate("INSERT INTO ingredient (name, category, aveUnitPrice, unitMeasurement) VALUES ('"+theName+"', '"+theCategory+"', '"+theAveUnitPrice+"', '"+theUnitMeasurement+"') ");
		System.out.println(retVal);  	
		}//end try
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();


		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		


		if (retVal !=1)
			return "Error inserting ingredient "+ingToAdd.getName();
		else
			return "Successfully added " +ingToAdd.getName();
		
		}//end addIngredient
		
		public static String deleteIngredientByName(String ingToDelete) 
		{
		int retVal=-1;
		//Ingredient ing=null;	
		String theName = ingToDelete;
		try
		{
		Class.forName("com.mysql.jdbc.Driver") ;
		Connection con = DriverManager.getConnection(
		        "jdbc:mysql://localhost:3306/mealplan",
		        "root",
		        "Shalina69");

		Statement stmt = con.createStatement();
		//ResultSet rs;
		
		
		//change the query construction later
		//rs = stmt.executeQuery("SELECT id, name FROM ingredient WHERE id ="+id);
		retVal = stmt.executeUpdate("DELETE FROM ingredient WHERE name ='"+theName+"'");
		System.out.println(retVal); 	
		}//end try
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();


		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		


		if (retVal !=1)
			return "Error deleting ingredient " +theName;
		else
			return "Successfully deleted " +theName;
		
		}
		
		
		
		


		public static void main(String[] args)
		{
			String s = getIngredientByIdJSON(1);
			System.out.println(s);
			String s2 = getIngredientByNameJSON("Sugar");
		    System.out.println(s2);
			String s3 = getIngredientListJSON();
			System.out.println(s3);
			String s4=addIngredient(new Ingredient("Lemon juice", "Spices", 1.78, "teaspoon" ));
			System.out.println(s4);
			String s5=deleteIngredientByName("Lemon juice");
			System.out.println(s5);
		}
}
