package com.cs330meals.test;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.*;

public class RecipeFacade
{
	public RecipeFacade()
	{

	}

	public static String getRecipeByIdJSON(int id)
	{
		Gson gson = new Gson();
		return gson.toJson(getRecipeById(id));
	}

	public static Recipe getRecipeById(int getId)
	{
		Recipe recipe = null;
		try
		{
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/MealPlanner");
			Connection con = dataSource.getConnection();

			// MealsDataSourceSingleton ds =
			// MealsDataSourceSingleton.getInstance();
			// Connection con = ds.getConnection();

			// Statement stmt = con.createStatement();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM recipes WHERE id = ? AND hide = 0");
			stmt.setInt(1, getId);
			ResultSet rs;
			// change the query construction later
			rs = stmt.executeQuery();

			// get count of rows returned
			// int size = 0;
			if (rs != null)
			{
				rs.beforeFirst();
				rs.last();
				// size = rs.getRow();
			}
			rs.beforeFirst();

			// Build ingredient list from result set
			// String strbuf = ""; // just for debugging

			if (rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int calories = rs.getInt("calories");
				String inst = rs.getString("instructions");
				int servs = rs.getInt("servings");
				double rating = rs.getDouble("rating");
				String desc = rs.getString("description");
				int prepT = rs.getInt("preptime");
				int readyT = rs.getInt("readytime");
				int cookT = rs.getInt("cooktime");
				String comments = rs.getString("comments");

				recipe = new Recipe(id, name, null, null, calories, inst, servs, rating, desc, prepT, readyT, cookT, null, comments, null);

				// strbuf just for debugging
				// strbuf += "id: " + x + " , name: " + s + "<br />";

			}// end if

		}// end try
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}

		return recipe;
	}

	public static String getRecipeByNameJSON(String name)
	{
		Gson gson = new Gson();
		return gson.toJson(getRecipeByName(name));
	}

	public static Recipe getRecipeByName(String getName)
	{
		Recipe recipe = null;
		try
		{
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/MealPlanner");
			Connection con = dataSource.getConnection();

			// Statement stmt = con.createStatement();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM recipes WHERE name = ? AND hide = 0");
			stmt.setString(1, getName);
			ResultSet rs;
			// change the query construction later
			rs = stmt.executeQuery();

			// get count of rows returned
			// int size = 0;
			if (rs != null)
			{
				rs.beforeFirst();
				rs.last();
				// size = rs.getRow();
			}
			rs.beforeFirst();

			// Build ingredient list from result set
			// String strbuf = ""; // just for debugging

			if (rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int calories = rs.getInt("calories");
				String inst = rs.getString("instructions");
				int servs = rs.getInt("servings");
				double rating = rs.getDouble("rating");
				String desc = rs.getString("description");
				int prepT = rs.getInt("preptime");
				int readyT = rs.getInt("readytime");
				int cookT = rs.getInt("cooktime");
				String comments = rs.getString("comments");

				recipe = new Recipe(id, name, null, null, calories, inst, servs, rating, desc, prepT, readyT, cookT, null, comments, null);

				// strbuf just for debugging
				// strbuf += "id: " + x + " , name: " + s + "<br />";

			}// end if

		}// end try
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return recipe;
	}

	public static String getRecipeListJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(getRecipeList());
	}

	public static Recipe[] getRecipeList()
	{

		Recipe[] recipeA = null;
		try
		{
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/MealPlanner");
			Connection con = dataSource.getConnection();

			// Statement stmt = con.createStatement();
			PreparedStatement stmt = con.prepareStatement("SELECT id, name FROM recipes WHERE hide = 0");
			ResultSet rs;
			rs = stmt.executeQuery();

			// get count of rows returned
			int size = 0;
			if (rs != null)
			{
				rs.beforeFirst();
				rs.last();
				size = rs.getRow();
			}
			rs.beforeFirst();

			// Build ingredient list from result set
			// String strbuf=""; //just for debugging
			recipeA = new Recipe[size];
			int i = 0;
			while (rs.next())
			{
				int x = rs.getInt("id");
				String s = rs.getString("name");
				Recipe recipe = new Recipe(x, s);

				// strbuf just for debugging
				// strbuf+="id: "+x+ " , name: "+s+"<br />";

				recipeA[i] = recipe;
				i++;
			}// end while

		}// end try
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return recipeA;
	}// end getIngredientList

	public static String addRecipe(Recipe recipeAdd)
	{
		String recipeName = recipeAdd.getName();
		String returnString = "Recipe already in database: " + recipeName;
		int retVal = -1;
		boolean hidden = false;
		try
		{
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/MealPlanner");
			Connection con = dataSource.getConnection();

			Statement stmt = con.createStatement();
			// PreparedStatement stmt =
			// con.prepareStatement("SELECT id, name, hide FROM ingredients WHERE name = ?");
			// stmt.setString(1, ingName);
			// change the query construction later

			ResultSet rs = stmt.executeQuery("SELECT id, name, hide FROM recipes WHERE name = '" + recipeName + "'");
			if (!rs.first())
			{
				retVal = stmt
						.executeUpdate("INSERT INTO recipes (name, calories, instructions, servings, rating, description, preptime, readytime, cooktime, image, comments, hide) VALUES ('"
								+ recipeName
								+ "', "
								+ recipeAdd.getCalories()
								+ ", '"
								+ recipeAdd.getInstructions()
								+ "', "
								+ recipeAdd.getServings()
								+ ", "
								+ recipeAdd.getRating()
								+ ", '"
								+ recipeAdd.getDescription()
								+ "', "
								+ recipeAdd.getPrepTime()
								+ ", "
								+ recipeAdd.getReadyTime()
								+ ", "
								+ recipeAdd.getCookTime()
								+ ", '"
								+ recipeAdd.getImage() + "', '" + recipeAdd.getComments() + "', 0)");
				returnString = (retVal == 1) ? "Successfully added recipe: " + recipeName : "Error adding recipe: " + recipeName;
			}
			else
			{
				hidden = rs.getInt("hide") == 1 ? true : false;
				if (hidden)
				{
					int id = rs.getInt("id");
					retVal = stmt.executeUpdate("UPDATE recipes set hide = 0 where id = " + id);
					returnString = (retVal == 1) ? "Successfully added recipe: " + recipeName : "Error adding recipe: " + recipeName;
				}
			}
		}// end try
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnString;
	}

	public static String deleteRecipe(Recipe recipeDel)
	{
		String recipeName = recipeDel.getName();
		String returnString = "Recipe not in database: " + recipeName;
		int retVal = -1;
		boolean hidden = false;
		try
		{
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/MealPlanner");
			Connection con = dataSource.getConnection();

			Statement stmt = con.createStatement();
			// change the query construction later

			ResultSet rs = stmt.executeQuery("SELECT id, name, hide FROM recipes WHERE name = '" + recipeName + "'");

			if (rs.first())
			{
				hidden = rs.getInt("hide") == 1 ? true : false;
				int id = rs.getInt("id");
				if (!hidden)
				{
					retVal = stmt.executeUpdate("UPDATE recipes set hide = 1 where id = " + id);
					returnString = (retVal == 1) ? "Successfully deleted recipe: " + recipeName : "Error deleting recipe: " + recipeName;
				}
			}
		}// end try
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnString;
	}

	public static void main(String[] args)
	{
		// Ingredient s1 = getIngredientByName("foo");
		// System.out.println(s1 == null);
		//
		// String s3 = getIngredientByIdJSON(4);
		// System.out.println(s3);
		//
		// String s4 = getIngredientByIdJSON(2);
		// System.out.println(s4);
		//
		// System.out.println(getIngredientList().length == 0);
		//
		// Ingredient[] ing = getIngredientList();
		// for (int i = 0; i < ing.length; i++)
		// {
		// System.out.println(ing[i]);
		// }
		//
		// String s4 = addIngredient(new Ingredient("pear"));
		// System.out.println(s4);
		//
		// Ingredient[] ing2 = getIngredientList();
		// for (int i = 0; i < ing2.length; i++)
		// {
		// System.out.println(ing2[i]);
		// }
		//
		// String s5 = deleteIngredient(new Ingredient("pear"));
		// System.out.println(s5);
		//
		// Ingredient[] ing3 = getIngredientList();
		// for (int i = 0; i < ing3.length; i++)
		// {
		// System.out.println(ing3[i]);
		// }
	}
}
