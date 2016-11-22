package com.github.titovartem.resterrortracker.dao;


import com.github.titovartem.resterrortracker.entity.ErrorReport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
//        TypedQuery<ErrorReport> query = em.createQuery("select p from ErrorReport p", ErrorReport.class);
//        if (query != null) {
//            return query.getResultList();
//        }
//        return null;
        return em.createQuery("select p from ErrorReport p", ErrorReport.class).getResultList();
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
