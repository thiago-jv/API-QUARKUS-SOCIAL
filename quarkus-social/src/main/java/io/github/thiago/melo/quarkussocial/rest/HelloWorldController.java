package io.github.thiago.melo.quarkussocial.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/coffee")
public class HelloWorldController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWord(){
        return "Hello";
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld(@PathParam("name") final String name){
        return "Hello " + name;
    }

}
