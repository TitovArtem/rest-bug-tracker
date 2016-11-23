package com.github.titovartem.resterrortracker.dao;


import com.github.titovartem.resterrortracker.entity.ErrorReport;

import java.util.List;

/**
 * This DAO provides persistence access to
 * {@link com.github.titovartem.resterrortracker.entity.ErrorReport} instance.
 */
public interface ErrorReportDao {

    void addErrorReport(ErrorReport error);
    void updateErrorReport(ErrorReport error);
    List<ErrorReport> getAllErrorReports();
    List<ErrorReport> getErrorReportsByFixedState(boolean fixedState);
    ErrorReport getErrorReportById(Long id);
    void deleteErrorReport(Long id);
}
