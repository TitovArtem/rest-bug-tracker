package com.github.titovartem.resterrortracker;

import com.github.titovartem.resterrortracker.rest.resources.ErrorReportResource;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * It's the entry point for RESTful API.
 */
public class ErrorTrackerRestApplication extends ResourceConfig {

    public ErrorTrackerRestApplication() {
        packages("com.github.titovartem.resterrortracker");
        register(ErrorReportResource.class);
    }
}
