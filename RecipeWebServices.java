package com.cs330meals.test;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

//@Path("recipes")
public class RecipeWebServicesG
{
	@Path("/i/")
	@GET
	@Produces("application/json")
	public String getRecipeById(@QueryParam("id") int id)
	{
		return RecipeFacade.getRecipeByIdJSON(id);
	}

	@Path("/n/")
	@GET
	@Produces("application/json")
	public String getRecipeByName(@QueryParam("name") String name)
	{
		return RecipeFacade.getRecipeByNameJSON(name);
	}

	@Path("/all")
	@GET
	@Produces("application/json")
	public String getRecipes()
	{
		return RecipeFacade.getRecipeListJSON();
	}

	@Path("/add/")
	@POST
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String addRecipe(MultivaluedMap<String, String> formParams)
	{
		String theName = formParams.getFirst("rname");
		return RecipeFacade.addRecipe(new Recipe(theName));
	}

	@Path("/delete/")
	@POST
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String deleteRecipe(MultivaluedMap<String, String> formParams)
	{
		String theName = formParams.getFirst("rdel");
		return RecipeFacade.deleteRecipe(new Recipe(theName));
	}

}
