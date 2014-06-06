package com.cs330meals.test;


import com.google.gson.Gson;


public class Ingredient
{
	private int id;
	private String name;
	private String category;
	private double aveUnitPrice;	
	private String unitMeasurement; 
	
	public Ingredient(int i, String n)
	{
		id=i;
		name=n;
		category=null;
		aveUnitPrice=0;
		unitMeasurement=null;
	
	}
	public Ingredient(String s)
	{
		name=s;
		id=0;
		category=null;
		aveUnitPrice=0;
		unitMeasurement=null;
	}
	public Ingredient(int i, String s, String c, double a, String u)
	{
		name=s;
		id=i;
		category=c;
		aveUnitPrice=a;
		unitMeasurement=u;
	}
	public Ingredient(String s, String c, double a, String u)
	{
		name=s;
		id=0;
		category=c;
		aveUnitPrice=a;
		unitMeasurement=u;
	}
	public String getName()
	{
		return name;
	}
	public double getPrice()
	{
		return aveUnitPrice;
	}
	public String getCategory()
	{
		return category;
	}
	public String getMeasurement()
	{
		return unitMeasurement;
	}
	public int getID()
	{
		return id;
	}
	public String toStringLong()
	{
		return  "Name: "+name+", ID: "+id+", Category: "+category+", Unit Price: "+aveUnitPrice+", Measurement: "+unitMeasurement;
	}
	public String toString()
	{
		return name+", "+id;
	}
	public static void main(String[] args)
	{
		Ingredient ing = new Ingredient(50, "chicken","Poltry", 6.87, "lb");
		System.out.println(ing.toStringLong());
		Gson gson = new Gson();
		String json = gson.toJson(ing);
		System.out.println(json);
	}
}
