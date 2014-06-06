package com.cs330meals.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;

@Path("recipes")
public class IngredientWebServices {
	
	
	@Path("/ingredient/i/")
	@GET
	@Produces("application/json")
	public String getIngredientById(@QueryParam("id") int id) {
	return IngredientFacade.getIngredientByIdJSON(id);
	}
	@Path("/ingredient/n/")
	@GET
	@Produces("application/json")
	public String getIngredientByName(@QueryParam("name") String name)
	{
		return IngredientFacade.getIngredientByNameJSON(name);
	}
	
	@Path("/ingredients")
	@GET
	@Produces("application/json")
	public String getIngredients()
	{
		return IngredientFacade.getIngredientListJSON();
	}
	
	@Path("/ingredient/add/")
	@POST
	@Produces ("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String addIngredient(MultivaluedMap<String, String> formParams)
	{
		//return "got it"
		String theName = formParams.getFirst("iname");
		//double thePrice = Double.parseDouble(    formParams.getFirst("iprice"));
		//System.out.println(theName);
		String theCategory = formParams.getFirst("icat");
		double thePrice = Double.parseDouble(formParams.getFirst("iprice"));
		String theMeasurement = formParams.getFirst("imeas");
		return IngredientFacade.addIngredient(new Ingredient(theName, theCategory, thePrice, theMeasurement));
	}
	
	@Path("/ingredient/delete/")
	@POST
	@Produces ("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String deleteIngredient(MultivaluedMap<String, String> formParams)
	{
		//return "got it"
		String theName = formParams.getFirst("iname");
		//double thePrice = Double.parseDouble(    formParams.getFirst("iprice"));
		
		System.out.println(theName);
		String result = IngredientFacade.deleteIngredientByName(theName);
		return result;
		//return theName;
	}
}

