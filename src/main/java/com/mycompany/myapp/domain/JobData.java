package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.mycompany.myapp.domain.enumeration.JobStatus;

/**
 * A JobData.
 */
@Entity
@Table(name = "job_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JobData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "job_code")
    private String jobCode;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_user")
    private String jobUser;

    @Column(name = "job_parameter_json")
    private String jobParameterJson;

    @Column(name = "job_create_dttm")
    private Instant jobCreateDttm;

    @Column(name = "job_interval_start_dttm")
    private Instant jobIntervalStartDttm;

    @Column(name = "job_interval_end_dttm")
    private Instant jobIntervalEndDttm;

    @Column(name = "job_next_dttm")
    private Instant jobNextDttm;

    @Column(name = "job_schedule_expression")
    private String jobScheduleExpression;

    @Column(name = "job_last_is_success_bool")
    private Boolean jobLastIsSuccessBool;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_status_code")
    private JobStatus jobStatusCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobCode() {
        return jobCode;
    }

    public JobData jobCode(String jobCode) {
        this.jobCode = jobCode;
        return this;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public JobData jobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobUser() {
        return jobUser;
    }

    public JobData jobUser(String jobUser) {
        this.jobUser = jobUser;
        return this;
    }

    public void setJobUser(String jobUser) {
        this.jobUser = jobUser;
    }

    public String getJobParameterJson() {
        return jobParameterJson;
    }

    public JobData jobParameterJson(String jobParameterJson) {
        this.jobParameterJson = jobParameterJson;
        return this;
    }

    public void setJobParameterJson(String jobParameterJson) {
        this.jobParameterJson = jobParameterJson;
    }

    public Instant getJobCreateDttm() {
        return jobCreateDttm;
    }

    public JobData jobCreateDttm(Instant jobCreateDttm) {
        this.jobCreateDttm = jobCreateDttm;
        return this;
    }

    public void setJobCreateDttm(Instant jobCreateDttm) {
        this.jobCreateDttm = jobCreateDttm;
    }

    public Instant getJobIntervalStartDttm() {
        return jobIntervalStartDttm;
    }

    public JobData jobIntervalStartDttm(Instant jobIntervalStartDttm) {
        this.jobIntervalStartDttm = jobIntervalStartDttm;
        return this;
    }

    public void setJobIntervalStartDttm(Instant jobIntervalStartDttm) {
        this.jobIntervalStartDttm = jobIntervalStartDttm;
    }

    public Instant getJobIntervalEndDttm() {
        return jobIntervalEndDttm;
    }

    public JobData jobIntervalEndDttm(Instant jobIntervalEndDttm) {
        this.jobIntervalEndDttm = jobIntervalEndDttm;
        return this;
    }

    public void setJobIntervalEndDttm(Instant jobIntervalEndDttm) {
        this.jobIntervalEndDttm = jobIntervalEndDttm;
    }

    public Instant getJobNextDttm() {
        return jobNextDttm;
    }

    public JobData jobNextDttm(Instant jobNextDttm) {
        this.jobNextDttm = jobNextDttm;
        return this;
    }

    public void setJobNextDttm(Instant jobNextDttm) {
        this.jobNextDttm = jobNextDttm;
    }

    public String getJobScheduleExpression() {
        return jobScheduleExpression;
    }

    public JobData jobScheduleExpression(String jobScheduleExpression) {
        this.jobScheduleExpression = jobScheduleExpression;
        return this;
    }

    public void setJobScheduleExpression(String jobScheduleExpression) {
        this.jobScheduleExpression = jobScheduleExpression;
    }

    public Boolean isJobLastIsSuccessBool() {
        return jobLastIsSuccessBool;
    }

    public JobData jobLastIsSuccessBool(Boolean jobLastIsSuccessBool) {
        this.jobLastIsSuccessBool = jobLastIsSuccessBool;
        return this;
    }

    public void setJobLastIsSuccessBool(Boolean jobLastIsSuccessBool) {
        this.jobLastIsSuccessBool = jobLastIsSuccessBool;
    }

    public JobStatus getJobStatusCode() {
        return jobStatusCode;
    }

    public JobData jobStatusCode(JobStatus jobStatusCode) {
        this.jobStatusCode = jobStatusCode;
        return this;
    }

    public void setJobStatusCode(JobStatus jobStatusCode) {
        this.jobStatusCode = jobStatusCode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobData)) {
            return false;
        }
        return id != null && id.equals(((JobData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JobData{" +
            "id=" + getId() +
            ", jobCode='" + getJobCode() + "'" +
            ", jobName='" + getJobName() + "'" +
            ", jobUser='" + getJobUser() + "'" +
            ", jobParameterJson='" + getJobParameterJson() + "'" +
            ", jobCreateDttm='" + getJobCreateDttm() + "'" +
            ", jobIntervalStartDttm='" + getJobIntervalStartDttm() + "'" +
            ", jobIntervalEndDttm='" + getJobIntervalEndDttm() + "'" +
            ", jobNextDttm='" + getJobNextDttm() + "'" +
            ", jobScheduleExpression='" + getJobScheduleExpression() + "'" +
            ", jobLastIsSuccessBool='" + isJobLastIsSuccessBool() + "'" +
            ", jobStatusCode='" + getJobStatusCode() + "'" +
            "}";
    }
}
