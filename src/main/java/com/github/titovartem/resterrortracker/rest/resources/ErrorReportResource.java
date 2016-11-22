package com.github.titovartem.resterrortracker.rest.resources;

import com.github.titovartem.resterrortracker.entity.ErrorReport;
import com.github.titovartem.resterrortracker.service.ErrorReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Component
@Path("/errors")
public class ErrorReportResource {

    @Autowired
    private ErrorReportService errorReportService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ErrorReport> getAllErrorReports() {
        return errorReportService.getAllErrorReports();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public ErrorReport getErrorReportById(@PathParam("id") Long id) {
        return errorReportService.getErrorReportById(id);
    }

    @GET
    @Path("/without-duplicates")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ErrorReport> getErrorReportsWithoutDuplicates() {
        return errorReportService.getErrorReportsWithoutDuplicates();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addErrorReport(ErrorReport report) {
        errorReportService.addErrorReport(report);
        return Response.status(Response.Status.CREATED)
                .entity("A new error has been added").build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateErrorReport(@PathParam("id") Long id, ErrorReport report) {
        ErrorReport errorReport = errorReportService.getErrorReportById(id);
        if (errorReport == null) {
            return Response.status(Response.Status.BAD_REQUEST).
                    entity("The error with the given id doesn't exist").build();
        }
        errorReportService.updateErrorReport(report);
        return Response.status(Response.Status.OK)
                .entity("The error has been updated").build();
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteErrorReport(@PathParam("id") Long id) {
        errorReportService.deleteErrorReport(id);
        return Response.status(Response.Status.OK)
                .entity("The error has been deleted").build();
    }
}
