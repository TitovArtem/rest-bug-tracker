package com.github.titovartem.resterrortracker.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.titovartem.resterrortracker.entity.ErrorReport;
import com.github.titovartem.resterrortracker.service.ErrorReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Class that handles REST requests for accessing to error reports.
 */
@Component
@Path("/errors")
public class ErrorReportResource {

    @Autowired
    private ErrorReportService errorReportService;

    public ErrorReportService getErrorReportService() {
        return errorReportService;
    }

    public void setErrorReportService(ErrorReportService errorReportService) {
        this.errorReportService = errorReportService;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ErrorReport> getAllErrorReports() {
        return errorReportService.getAllErrorReports();
    }

    @GET
    @Path("/fixed")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ErrorReport> getFixedErrorReports() {
        return errorReportService.getFixedErrorReports();
    }

    @GET
    @Path("/opened")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ErrorReport> getOpenedErrorReports() {
        return errorReportService.getOpenedErrorReports();
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

    @GET
    @Path("/group-by-duplicates")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getErrorReportsGroupedByDuplicates() {
        List<List<ErrorReport>> errors = errorReportService.getGroupedErrorReportsByDuplicates();

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(errors);
        } catch (JsonProcessingException exc) {
            // TODO: log exception
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().entity(json).build();
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
        report.setId(errorReport.getId());
        errorReportService.updateErrorReport(report);
        return Response.status(Response.Status.OK)
                .entity("The error has been updated").build();
    }

    @POST
    @Path("{id}/fix")
    public Response fixErrorReport(@PathParam("id") Long id) {
        ErrorReport errorReport = errorReportService.getErrorReportById(id);
        if (errorReport == null) {
            return Response.status(Response.Status.BAD_REQUEST).
                    entity("The error with the given id doesn't exist").build();
        }
        errorReport.setFixed(true);
        errorReportService.updateErrorReport(errorReport);
        return Response.status(Response.Status.OK)
                .entity("The error has been fixed").build();
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
