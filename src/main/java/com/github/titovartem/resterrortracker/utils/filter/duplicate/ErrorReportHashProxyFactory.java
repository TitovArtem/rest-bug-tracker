package com.github.titovartem.resterrortracker.utils.filter.duplicate;

import com.github.titovartem.resterrortracker.entity.ErrorReport;


public class ErrorReportHashProxyFactory implements EntityHashProxyFactory<ErrorReport> {

    @Override
    public ErrorReportHashProxy create(ErrorReport entity) {
        return new ErrorReportHashProxy(entity);
    }
}
