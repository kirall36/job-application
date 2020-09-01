package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.mycompany.myapp.domain.enumeration.JobStatus;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.JobData} entity.
 */
public class JobDataDTO implements Serializable {
    
    private Long id;

    private String jobCode;

    private String jobName;

    private String jobUser;

    private String jobParameterJson;

    private Instant jobCreateDttm;

    private Instant jobIntervalStartDttm;

    private Instant jobIntervalEndDttm;

    private Instant jobNextDttm;

    private String jobScheduleExpression;

    private Boolean jobLastIsSuccessBool;

    private JobStatus jobStatusCode;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobUser() {
        return jobUser;
    }

    public void setJobUser(String jobUser) {
        this.jobUser = jobUser;
    }

    public String getJobParameterJson() {
        return jobParameterJson;
    }

    public void setJobParameterJson(String jobParameterJson) {
        this.jobParameterJson = jobParameterJson;
    }

    public Instant getJobCreateDttm() {
        return jobCreateDttm;
    }

    public void setJobCreateDttm(Instant jobCreateDttm) {
        this.jobCreateDttm = jobCreateDttm;
    }

    public Instant getJobIntervalStartDttm() {
        return jobIntervalStartDttm;
    }

    public void setJobIntervalStartDttm(Instant jobIntervalStartDttm) {
        this.jobIntervalStartDttm = jobIntervalStartDttm;
    }

    public Instant getJobIntervalEndDttm() {
        return jobIntervalEndDttm;
    }

    public void setJobIntervalEndDttm(Instant jobIntervalEndDttm) {
        this.jobIntervalEndDttm = jobIntervalEndDttm;
    }

    public Instant getJobNextDttm() {
        return jobNextDttm;
    }

    public void setJobNextDttm(Instant jobNextDttm) {
        this.jobNextDttm = jobNextDttm;
    }

    public String getJobScheduleExpression() {
        return jobScheduleExpression;
    }

    public void setJobScheduleExpression(String jobScheduleExpression) {
        this.jobScheduleExpression = jobScheduleExpression;
    }

    public Boolean isJobLastIsSuccessBool() {
        return jobLastIsSuccessBool;
    }

    public void setJobLastIsSuccessBool(Boolean jobLastIsSuccessBool) {
        this.jobLastIsSuccessBool = jobLastIsSuccessBool;
    }

    public JobStatus getJobStatusCode() {
        return jobStatusCode;
    }

    public void setJobStatusCode(JobStatus jobStatusCode) {
        this.jobStatusCode = jobStatusCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobDataDTO)) {
            return false;
        }

        return id != null && id.equals(((JobDataDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JobDataDTO{" +
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
