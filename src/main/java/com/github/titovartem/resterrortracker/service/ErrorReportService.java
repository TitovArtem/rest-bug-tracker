package com.github.titovartem.resterrortracker.service;

import com.github.titovartem.resterrortracker.entity.ErrorReport;

import java.util.List;


public interface ErrorReportService {

    void addErrorReport(ErrorReport error);
    void updateErrorReport(ErrorReport error);
    List<ErrorReport> getAllErrorReports();
    ErrorReport getErrorReportById(Long id);
    void deleteErrorReport(Long id);
}
