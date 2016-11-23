package com.github.titovartem.resterrortracker.service;


import com.github.titovartem.resterrortracker.dao.ErrorReportDao;
import com.github.titovartem.resterrortracker.entity.ErrorReport;
import com.github.titovartem.resterrortracker.utils.duplicate.EntityHashProxyFactory;
import com.github.titovartem.resterrortracker.utils.filter.EntityFilter;
import com.github.titovartem.resterrortracker.utils.duplicate.DuplicateFilter;
import com.github.titovartem.resterrortracker.utils.duplicate.ErrorReportHashProxyFactory;
import com.github.titovartem.resterrortracker.utils.duplicate.DuplicateGroupSeparator;
import com.github.titovartem.resterrortracker.utils.grouper.EntityGroupSeparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ErrorReportServiceImpl implements ErrorReportService {

    @Autowired
    private ErrorReportDao dao;

    private EntityFilter<ErrorReport> duplicateFilter =
            new DuplicateFilter<ErrorReport>(new ErrorReportHashProxyFactory());

    private EntityGroupSeparator<Object, ErrorReport> duplicateGroupSeparator =
            new DuplicateGroupSeparator<ErrorReport>(new ErrorReportHashProxyFactory());


    public void setErrorReportHashProxyFactory(EntityHashProxyFactory<ErrorReport> factory) {
        duplicateFilter = new DuplicateFilter<ErrorReport>(factory);
        duplicateGroupSeparator = new DuplicateGroupSeparator<ErrorReport>(factory);
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
    public List<ErrorReport> getFixedErrorReports() {
        return dao.getErrorReportsByFixedState(true);
    }

    @Override
    public List<ErrorReport> getOpenedErrorReports() {
        return dao.getErrorReportsByFixedState(false);
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
        return duplicateFilter.filter(errors);
    }

    @Override
    public List<List<ErrorReport>> getGroupedErrorReportsByDuplicates() {
        Map<Object, List<ErrorReport>> groups =
                duplicateGroupSeparator.group(dao.getAllErrorReports());
        return new ArrayList<List<ErrorReport>>(groups.values());
    }
}
