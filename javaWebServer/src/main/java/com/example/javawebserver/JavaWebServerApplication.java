package com.example.javawebserver;

import com.example.javawebserver.api.HelloWorldResource;
import com.example.javawebserver.api.SurveyResource;
import com.example.javawebserver.config.JavaWebServerConfiguration;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class JavaWebServerApplication extends Application<JavaWebServerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new JavaWebServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "javawebserver";
    }

    @Override
    public void initialize(final Bootstrap<JavaWebServerConfiguration> bootstrap) {}

    @Override
    public void run(final JavaWebServerConfiguration configuration,
                    final Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );

        // register resources(API endpoints)
        environment.jersey().register(resource);
        environment.jersey().register(new SurveyResource());
    }
} 