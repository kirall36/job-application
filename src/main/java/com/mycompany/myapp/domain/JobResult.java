package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.mycompany.myapp.domain.enumeration.JobResultStatus;

/**
 * A JobResult.
 */
@Entity
@Table(name = "job_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JobResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "job_id")
    private String jobId;

    @Enumerated(EnumType.STRING)
    @Column(name = "jrt_status_code")
    private JobResultStatus jrtStatusCode;

    @Column(name = "jrt_start_dttm")
    private Instant jrtStartDttm;

    @Column(name = "jrt_end_dttm")
    private Instant jrtEndDttm;

    @Column(name = "jrt_result")
    private Long jrtResult;

    @Column(name = "jrt_log")
    private Long jrtLog;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public JobResult jobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public JobResultStatus getJrtStatusCode() {
        return jrtStatusCode;
    }

    public JobResult jrtStatusCode(JobResultStatus jrtStatusCode) {
        this.jrtStatusCode = jrtStatusCode;
        return this;
    }

    public void setJrtStatusCode(JobResultStatus jrtStatusCode) {
        this.jrtStatusCode = jrtStatusCode;
    }

    public Instant getJrtStartDttm() {
        return jrtStartDttm;
    }

    public JobResult jrtStartDttm(Instant jrtStartDttm) {
        this.jrtStartDttm = jrtStartDttm;
        return this;
    }

    public void setJrtStartDttm(Instant jrtStartDttm) {
        this.jrtStartDttm = jrtStartDttm;
    }

    public Instant getJrtEndDttm() {
        return jrtEndDttm;
    }

    public JobResult jrtEndDttm(Instant jrtEndDttm) {
        this.jrtEndDttm = jrtEndDttm;
        return this;
    }

    public void setJrtEndDttm(Instant jrtEndDttm) {
        this.jrtEndDttm = jrtEndDttm;
    }

    public Long getJrtResult() {
        return jrtResult;
    }

    public JobResult jrtResult(Long jrtResult) {
        this.jrtResult = jrtResult;
        return this;
    }

    public void setJrtResult(Long jrtResult) {
        this.jrtResult = jrtResult;
    }

    public Long getJrtLog() {
        return jrtLog;
    }

    public JobResult jrtLog(Long jrtLog) {
        this.jrtLog = jrtLog;
        return this;
    }

    public void setJrtLog(Long jrtLog) {
        this.jrtLog = jrtLog;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobResult)) {
            return false;
        }
        return id != null && id.equals(((JobResult) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JobResult{" +
            "id=" + getId() +
            ", jobId='" + getJobId() + "'" +
            ", jrtStatusCode='" + getJrtStatusCode() + "'" +
            ", jrtStartDttm='" + getJrtStartDttm() + "'" +
            ", jrtEndDttm='" + getJrtEndDttm() + "'" +
            ", jrtResult=" + getJrtResult() +
            ", jrtLog=" + getJrtLog() +
            "}";
    }
}
