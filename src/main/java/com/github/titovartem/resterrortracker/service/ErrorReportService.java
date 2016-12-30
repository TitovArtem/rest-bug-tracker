package com.github.titovartem.resterrortracker.service;

import com.github.titovartem.resterrortracker.entity.ErrorReport;
import com.github.titovartem.resterrortracker.utils.filter.EntityFilter;
import com.github.titovartem.resterrortracker.utils.grouper.EntityGroupSeparator;

import java.util.List;


public interface ErrorReportService {

    void setErrorReportDuplicateFilter(EntityFilter<ErrorReport> filter);
    EntityFilter<ErrorReport> getErrorReportDuplicateFilter();
    void setErrorReportDuplicateGroupSeparator(EntityGroupSeparator<?, ErrorReport> groupSeparator);
    EntityGroupSeparator<?, ErrorReport> getErrorReportDuplicateGroupSeparator();

    void addErrorReport(ErrorReport error);
    void updateErrorReport(ErrorReport error);

    List<ErrorReport> getAllErrorReports();
    List<ErrorReport> getFixedErrorReports();
    List<ErrorReport> getOpenedErrorReports();
    ErrorReport getErrorReportById(Long id);
    void deleteErrorReport(Long id);

    List<ErrorReport> getErrorReportsWithoutDuplicates();
    List<List<ErrorReport>> getGroupedErrorReportsByDuplicates();
}
