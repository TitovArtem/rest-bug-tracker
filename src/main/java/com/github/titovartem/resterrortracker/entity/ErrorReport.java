package com.github.titovartem.resterrortracker.entity;

import javax.persistence.*;
import java.util.Date;


/**
 * It describes an information about programming error.
 */
@Entity
@Table(name = "ERROR_REPORT")
public class ErrorReport {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "SOURCE", columnDefinition = "TEXT")
    private String source;

    @Column(name = "STACK_TRACE", columnDefinition = "TEXT")
    private String stackTrace;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTRATION_TIMESTAMP", updatable = false)
    private Date registrationTimestamp;

    @Column(name = "FIXED", nullable = false)
    private boolean isFixed;

    @PrePersist
    void preInsert() {
        // Set up now timestamp while inserting
        registrationTimestamp = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public Date getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(Date registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorReport that = (ErrorReport) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ErrorReport{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", registrationDateTime=" + registrationTimestamp +
                ", isFixed=" + isFixed +
                '}';
    }
}
