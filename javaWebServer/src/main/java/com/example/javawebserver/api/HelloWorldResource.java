package com.example.javawebserver.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;

@Path("/hello-world")
@Produces("application/json")
public class HelloWorldResource {
    private final String template;
    private final String defaultName;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
    }

    @GET
    public String sayHello(@QueryParam("name") @DefaultValue("") String name) {
        System.out.println("Received request with name: " + name);
        final String value = String.format(template, name.isEmpty() ? defaultName : name);
        return value;
    }
} 