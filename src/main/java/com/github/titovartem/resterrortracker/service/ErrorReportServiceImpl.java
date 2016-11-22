package com.github.titovartem.resterrortracker.service;


import com.github.titovartem.resterrortracker.dao.ErrorReportDao;
import com.github.titovartem.resterrortracker.entity.ErrorReport;
import com.github.titovartem.resterrortracker.utils.filter.EntityFilter;
import com.github.titovartem.resterrortracker.utils.filter.duplicate.DuplicateFilter;
import com.github.titovartem.resterrortracker.utils.filter.duplicate.ErrorReportHashProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ErrorReportServiceImpl implements ErrorReportService {

    @Autowired
    private ErrorReportDao dao;

    private EntityFilter<ErrorReport> duplicateErrorReportFilter =
            new DuplicateFilter<ErrorReport>(new ErrorReportHashProxyFactory());

    public void setDuplicateErrorReportFilter(EntityFilter<ErrorReport> duplicateErrorFilter) {
        this.duplicateErrorReportFilter = duplicateErrorFilter;
    }

    @Override
    @Transactional
    public void addErrorReport(ErrorReport error) {
        dao.addErrorReport(error);
    }

    @Override
    @Transactional
    public void updateErrorReport(ErrorReport error) {
        dao.updateErrorReport(error);
    }

    @Override
    public List<ErrorReport> getAllErrorReports() {
        return dao.getAllErrorReports();
    }

    @Override
    public ErrorReport getErrorReportById(Long id) {
        return dao.getErrorReportById(id);
    }

    @Override
    @Transactional
    public void deleteErrorReport(Long id) {
        dao.deleteErrorReport(id);
    }

    @Override
    public List<ErrorReport> getErrorReportsWithoutDuplicates() {
        List<ErrorReport> errors = dao.getAllErrorReports();
        return duplicateErrorReportFilter.filter(errors);
    }
}
