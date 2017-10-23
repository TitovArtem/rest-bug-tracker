package com.github.titovartem.resterrortracker.service;


import com.github.titovartem.resterrortracker.dao.ErrorReportDao;
import com.github.titovartem.resterrortracker.entity.ErrorReport;
import com.github.titovartem.resterrortracker.utils.Predicate;
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

    private EntityFilter<ErrorReport> filter =
            new DuplicateFilter<ErrorReport>(new ErrorReportHashProxyFactory());

    private EntityGroupSeparator<?, ErrorReport> groupSeparator =
            new DuplicateGroupSeparator<ErrorReport>(new ErrorReportHashProxyFactory());


    @Override
    public void setErrorReportDuplicateFilter(EntityFilter<ErrorReport> filter) {
        this.filter = filter;
    }

    @Override
    public EntityFilter<ErrorReport> getErrorReportDuplicateFilter() {
        return filter;
    }

    @Override
    public void setErrorReportDuplicateGroupSeparator(
            EntityGroupSeparator<?, ErrorReport> groupSeparator)
    {
        this.groupSeparator = groupSeparator;
    }

    @Override
    public EntityGroupSeparator<?, ErrorReport> getErrorReportDuplicateGroupSeparator() {
        return groupSeparator;
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

    /* Returns a list of fixed errors if the given state is true,
    otherwise returns a list of open errors. */
    private List<ErrorReport> getErrorReportsByFixedState(final boolean isFixed) {
        return dao.getAllErrorReports(new Predicate<ErrorReport>() {
            @Override
            public boolean apply(ErrorReport errorReport) {
                return errorReport.isFixed() == isFixed;
            }
        });
    }

    @Override
    public List<ErrorReport> getFixedErrorReports() {
        return getErrorReportsByFixedState(true);
    }

    @Override
    public List<ErrorReport> getOpenedErrorReports() {
        return getErrorReportsByFixedState(false);
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
        return filter.filter(errors);
    }

    @Override
    public List<List<ErrorReport>> getGroupedErrorReportsByDuplicates() {
        Map<?, List<ErrorReport>> groups = groupSeparator.group(dao.getAllErrorReports());
        return new ArrayList<List<ErrorReport>>(groups.values());
    }
}
