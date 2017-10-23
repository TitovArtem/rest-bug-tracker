package com.github.titovartem.resterrortracker.service;

import com.github.titovartem.resterrortracker.entity.ErrorReport;
import com.github.titovartem.resterrortracker.utils.filter.EntityFilter;
import com.github.titovartem.resterrortracker.utils.grouper.EntityGroupSeparator;

import java.util.List;


public interface ErrorReportService {

    /** Sets filter for filtering a duplicate error reports. */
    void setErrorReportDuplicateFilter(EntityFilter<ErrorReport> filter);
    /** @return a filter of duplicate error reports */
    EntityFilter<ErrorReport> getErrorReportDuplicateFilter();

    /** Sets group separator for grouping error reports by duplicates. */
    void setErrorReportDuplicateGroupSeparator(EntityGroupSeparator<?, ErrorReport> groupSeparator);
    /** @return a group separator of duplicate error reports */
    EntityGroupSeparator<?, ErrorReport> getErrorReportDuplicateGroupSeparator();

    /** Adds a new error report. */
    void addErrorReport(ErrorReport error);
    /** Updates state of the given error report. */
    void updateErrorReport(ErrorReport error);
    /** Deletes a error reports which has the given id. */
    void deleteErrorReport(Long id);

    /** @return a error report which has the given id */
    ErrorReport getErrorReportById(Long id);

    /** @return a list of all error reports */
    List<ErrorReport> getAllErrorReports();
    /** @return a list of reports for fixed errors */
    List<ErrorReport> getFixedErrorReports();
    /** @return a list of reports for open errors */
    List<ErrorReport> getOpenedErrorReports();

    /** @return a list of error reports without duplicates */
    List<ErrorReport> getErrorReportsWithoutDuplicates();
    /** @return a list of list with error reports that grouped by duplicates */
    List<List<ErrorReport>> getGroupedErrorReportsByDuplicates();
}
