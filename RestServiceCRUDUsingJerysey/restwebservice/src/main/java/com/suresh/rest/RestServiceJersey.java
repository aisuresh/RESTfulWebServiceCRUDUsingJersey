package com.suresh.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.suresh.dao.User;
import com.suresh.dao.UserDao;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

@Path("/restpath")
public class RestServiceJersey {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getStartingPage() {
		String output = "<h1>Hello Suresh!!!<h1>" + "<p>RESTful Service is running ... <br>Ping @ " + new Date().toString()
				+ "</p<br>";
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/{param}")
	public Response getMessage(@PathParam("param") String name) {
		String output = "Rest Web Service from Server Jersey say, Welcome : " + name;

		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/allusers")
	public Response getAllUsers() {
		List<String> users = new ArrayList<String>();
		users.add("Suresh");
		users.add("Suba");
		users.add("Sanjana");

		GenericEntity<List<String>> entity = new GenericEntity<List<String>>(users) {};
		
		return Response.ok(entity).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/postoperation")
	public String postMessage(User user) {
		 System.out.println("User Name = "+user.getName());
	     	        
	     return "Your name is " + user.getName() + " is ok";
	}

	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	public Response getUsers() {
		UserDao userDao = new UserDao();
		
		GenericEntity<List<User>> entity = new GenericEntity<List<User>>(userDao.getAllUsers()) {};
		
		return Response.ok(entity).build();		
	}
	
	@DELETE
	@Path("/delete/{user}")
	@Produces(MediaType.TEXT_HTML)
	public Response deleteUser(@PathParam("user") String user) {
		
		String output = " User "+ user + " has been deleted";

		return Response.status(200).entity(output).build();	
	}

}
