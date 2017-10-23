package com.github.titovartem.resterrortracker.dao;


import com.github.titovartem.resterrortracker.entity.ErrorReport;
import com.github.titovartem.resterrortracker.utils.Predicate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Repository
public class ErrorReportDaoImpl implements ErrorReportDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addErrorReport(ErrorReport error) {
        em.persist(error);
    }

    @Override
    public void updateErrorReport(ErrorReport error) {
        em.merge(error);
    }

    @Override
    public List<ErrorReport> getAllErrorReports() {
        return em.createQuery("select p from ErrorReport p", ErrorReport.class).getResultList();
    }

    @Override
    public List<ErrorReport> getAllErrorReports(Predicate<ErrorReport> predicate) {
        List<ErrorReport> errors = em.createQuery("select e from ErrorReport e",
                ErrorReport.class).getResultList();

        Iterator<ErrorReport> it = errors.iterator();
        while (it.hasNext()) {
            if (!predicate.apply(it.next())) {
                it.remove();
            }
        }
        return errors;
    }

    @Override
    public ErrorReport getErrorReportById(Long id) {
        return em.find(ErrorReport.class, id);
    }

    @Override
    public void deleteErrorReport(Long id) {
        ErrorReport error = em.find(ErrorReport.class, id);
        em.remove(error);
    }
}
