package com.cbsexam;

import cache.UserCache;
import com.google.gson.Gson;
import controllers.UserController;
import java.util.ArrayList;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.User;
import utils.Encryption;
import utils.Log;

@Path("user")
public class UserEndpoints {
  UserCache userCache = new UserCache();

  /**
   * @param idUser
   * @return Responses
   */
  @GET
  @Path("/{idUser}")
  public Response getUser(@PathParam("idUser") int idUser) {

    // Use the ID to get the user from the controller.
    User user = UserController.getUser(idUser);

    // TODO: Add Encryption to JSON: FIXED
    // Convert the user object to json in order to return the object
    String json = new Gson().toJson(user);

    // encryption to json rwaString object(ret. utils Encryption)
    json = Encryption.encryptDecryptXOR(json);


    // TODO: What should happen if something breaks down?: FIXED
    if (user != null) {
      // Return the user with the status code 200 - successful
      return Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity(json).build();
    } else {
      // Return the user with the status code 400 - client error
      return Response.status(400).entity("Could not find the user, please try again").build();
    } //else {
      // Return the user with the status code 500 - Internal Server Error
      //return Response.status(500).entity("The server encountered an unexpected condition which prevented it from fulfilling the request").build();
    //}
  }

  /** @return Responses */
  @GET
  @Path("/")
  public Response getUsers() {

    // Write to log that we are here
    Log.writeLog(this.getClass().getName(), this, "Get all users", 0);

    // Get a list of users
    ArrayList<User> users = userCache.getUsers(true);

    // TODO: Add Encryption to JSON: FIXED
    // Transfer users to json in order to return it to the user
    String json = new Gson().toJson(users);

    // encryption to json rwaString object(ret. utils Encryption)
    json = Encryption.encryptDecryptXOR(json);

    // Return the users with the status code 200
    return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json).build();
  }

  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createUser(String body) {

    // Read the json from body and transfer it to a user class
    User newUser = new Gson().fromJson(body, User.class);

    // Use the controller to add the user
    User createUser = UserController.createUser(newUser);

    // Get the user back with the added ID and return it to the user
    String json = new Gson().toJson(createUser);

    // Return the data to the user
    if (createUser != null) {
      // Return a response with status 200 and JSON as type
      return Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity(json).build();
    } else {
      return Response.status(400).entity("Could not create user").build();
    }
  }

  // TODO: Make the system able to login users and assign them a token to use throughout the system.
  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response loginUser(String body) {

    User user = new Gson().fromJson(body, User.class);

    User currentUser = new Gson().fromJson(String.valueOf(id), User.class);
    User databaseUser = UserController.get(currentUser.getId());

    if (currentUser == databaseUser) {
      // Return a response with status 200 and JSON as type
      return Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity(databaseUser).build();
    } else {
      // Return the user with the status code 400 - client error
      return Response.status(400).entity("The endpoint are not implemented yet").build();
    }

  }


  // TODO: Make the system able to delete users: FIXED
  @DELETE
  @Path("/delete/{idUser}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response deleteUser(@PathParam("idUser") int idUser) {

    User deleteUser1 = UserController.getUser(idUser);
    User deleteUser2 = UserController.deleteUser(deleteUser1);
    String json = new Gson().toJson(deleteUser2);

    if (deleteUser2 != null) {
      // Return a response with status 200 and JSON as type
      return Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity(json).build();
    } else {
      // Return the user with the status code 400 - client error  
      return Response.status(400).entity("Endpoint not implemented yet").build();
    }
  }


  // TODO: Make the system able to update users: FIXED
  @PUT
  @Path("/{idUser}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateUser(String body) {

    User UserUpdate = new Gson().fromJson(body, User.class);

    User updateUser = UserController.updateUser(UserUpdate);

    String json = new Gson().toJson(updateUser);

    if (updateUser != null) {
      return Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity(json).build();
    } else {
      // Return a response with status 200 and JSON as type
      return Response.status(400).entity("Endpoint not implemented yet").build();
    }
  }


}

