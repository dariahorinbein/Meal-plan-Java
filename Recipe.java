package com.cs330meals.test;

import java.util.*;

public class Recipe
{
	int id;
	String name;
	ArrayList<Ingredient> ingredients;
	ArrayList<Double> ingAmounts;
	int calories;
	String instructions;
	int servings;
	double rating;
	String description;
	int prepTime;
	int readyTime;
	int cookTime;
	String image;
	String comments;
	ArrayList<String> tags;

	public Recipe()
	{
		id = -1;
		name = "";
		ingredients = new ArrayList<Ingredient>();
		ingAmounts = new ArrayList<Double>();
		calories = 0;
		instructions = "";
		servings = 0;
		rating = 0;
		description = "";
		prepTime = 0;
		readyTime = 0;
		cookTime = 0;
		image = null;
		comments = "";
		tags = new ArrayList<String>();
	}

	public Recipe(int id, String name, ArrayList<Ingredient> ingredients, ArrayList<Double> ingAmounts, int calories, String instructions,
			int servings, double rating, String description, int prepTime, int readyTime, int cookTime, String image, String comments,
			ArrayList<String> tags)
	{
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.ingAmounts = ingAmounts;
		this.calories = calories;
		this.instructions = instructions;
		this.servings = servings;
		this.rating = rating;
		this.description = description;
		this.prepTime = prepTime;
		this.readyTime = readyTime;
		this.cookTime = cookTime;
		this.image = image;
		this.comments = comments;
		this.tags = tags;
	}

	public Recipe(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public Recipe(String name)
	{
		this.id = -1;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the ingredients
	 */
	public ArrayList<Ingredient> getIngredients()
	{
		return ingredients;
	}

	/**
	 * @param ingredients
	 *            the ingredients to set
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients)
	{
		this.ingredients = ingredients;
	}

	/**
	 * @return the ingAmounts
	 */
	public ArrayList<Double> getIngAmounts()
	{
		return ingAmounts;
	}

	/**
	 * @param ingAmounts
	 *            the ingAmounts to set
	 */
	public void setIngAmounts(ArrayList<Double> ingAmounts)
	{
		this.ingAmounts = ingAmounts;
	}

	/**
	 * @return the calories
	 */
	public int getCalories()
	{
		return calories;
	}

	/**
	 * @param calories
	 *            the calories to set
	 */
	public void setCalories(int calories)
	{
		this.calories = calories;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions()
	{
		return instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(String instructions)
	{
		this.instructions = instructions;
	}

	/**
	 * @return the servings
	 */
	public int getServings()
	{
		return servings;
	}

	/**
	 * @param servings
	 *            the servings to set
	 */
	public void setServings(int servings)
	{
		this.servings = servings;
	}

	/**
	 * @return the rating
	 */
	public double getRating()
	{
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(double rating)
	{
		this.rating = rating;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the prepTime
	 */
	public int getPrepTime()
	{
		return prepTime;
	}

	/**
	 * @param prepTime
	 *            the prepTime to set
	 */
	public void setPrepTime(int prepTime)
	{
		this.prepTime = prepTime;
	}

	/**
	 * @return the readyTime
	 */
	public int getReadyTime()
	{
		return readyTime;
	}

	/**
	 * @param readyTime
	 *            the readyTime to set
	 */
	public void setReadyTime(int readyTime)
	{
		this.readyTime = readyTime;
	}

	/**
	 * @return the cookTime
	 */
	public int getCookTime()
	{
		return cookTime;
	}

	/**
	 * @param cookTime
	 *            the cookTime to set
	 */
	public void setCookTime(int cookTime)
	{
		this.cookTime = cookTime;
	}

	/**
	 * @return the image
	 */
	public String getImage()
	{
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image)
	{
		this.image = image;
	}

	/**
	 * @return the comments
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}

	/**
	 * @return the tags
	 */
	public ArrayList<String> getTags()
	{
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(ArrayList<String> tags)
	{
		this.tags = tags;
	}
}
