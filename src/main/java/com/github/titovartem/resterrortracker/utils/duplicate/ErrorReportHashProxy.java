package com.github.titovartem.resterrortracker.utils.duplicate;

import com.github.titovartem.resterrortracker.entity.ErrorReport;


/**
 * This class defines {@link Object#hashCode()} and {@link Object#equals(Object)}
 * for some {@link com.github.titovartem.resterrortracker.entity.ErrorReport}
 * fields (source and stackTrace).
 */
public class ErrorReportHashProxy {

    private ErrorReport errorReport;

    public ErrorReportHashProxy(ErrorReport errorReport) {
        this.errorReport = errorReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorReport that = ((ErrorReportHashProxy) o).errorReport;
        ErrorReport self = errorReport;


        if (self.getSource() != null
                ? !self.getSource().equals(that.getSource())
                : that.getSource() != null) return false;


           return self.getStackTrace() != null
                   ? self.getStackTrace().equals(that.getStackTrace())
                   : that.getStackTrace() == null;
    }

    @Override
    public int hashCode() {
        int result = errorReport.getSource() != null ? errorReport.getSource().hashCode() : 0;
        return 31 * result + (errorReport.getStackTrace() != null
                ? errorReport.getStackTrace().hashCode() : 0);
    }
}
