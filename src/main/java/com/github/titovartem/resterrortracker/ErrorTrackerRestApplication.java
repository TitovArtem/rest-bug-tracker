package com.github.titovartem.resterrortracker;

import com.github.titovartem.resterrortracker.entity.ErrorReport;
import com.github.titovartem.resterrortracker.rest.resources.ErrorReportResource;
import com.github.titovartem.resterrortracker.service.ErrorReportService;
import com.github.titovartem.resterrortracker.service.ErrorReportServiceImpl;
import com.github.titovartem.resterrortracker.utils.duplicate.DuplicateFilter;
import com.github.titovartem.resterrortracker.utils.duplicate.ErrorReportHashProxyFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * It's the entry point for RESTful API.
 */
public class ErrorTrackerRestApplication extends ResourceConfig {

    public ErrorTrackerRestApplication() {
        packages("com.github.titovartem.resterrortracker");


        ErrorReportResource errorReportResource = new ErrorReportResource();

        /* The example how to change filter (uncomment lines below).

        ErrorReportService errorReportService = new ErrorReportServiceImpl();
        errorReportService.setErrorReportDuplicateFilter(new DuplicateFilter<ErrorReport>(
                new ErrorReportHashProxyFactory()));

        errorReportResource.setErrorReportService(errorReportService);
        */

        register(errorReportResource);
    }
}
