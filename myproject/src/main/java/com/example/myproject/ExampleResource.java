package com.example.myproject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PathParam;
/**
 * Simple JAXRS resource class.
 */
@Path("/example")
@Dependent

public class ExampleResource {
@PersistenceContext 
private EntityManager em;
    @Produces("text/plain")
    @Path("/")
    @GET
    public String get() {
        return "It works!";
    }

    @GET
   @Path("response/{salutation}")
  @Produces("text/plain")
   @Transactional 
public String getResponse(@PathParam("salutation") String salutation) {
    final Greeting greeting = this.em.find(Greeting.class, salutation);
    final String returnValue;
    if (greeting == null) {
        returnValue = null;
    } else {
        returnValue = greeting.getResponse();
    }
    return returnValue;
}
}
