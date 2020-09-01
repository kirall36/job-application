package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.mycompany.myapp.domain.enumeration.JobResultStatus;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.JobResult} entity.
 */
public class JobResultDTO implements Serializable {
    
    private Long id;

    private String jobId;

    private JobResultStatus jrtStatusCode;

    private Instant jrtStartDttm;

    private Instant jrtEndDttm;

    private Long jrtResult;

    private Long jrtLog;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public JobResultStatus getJrtStatusCode() {
        return jrtStatusCode;
    }

    public void setJrtStatusCode(JobResultStatus jrtStatusCode) {
        this.jrtStatusCode = jrtStatusCode;
    }

    public Instant getJrtStartDttm() {
        return jrtStartDttm;
    }

    public void setJrtStartDttm(Instant jrtStartDttm) {
        this.jrtStartDttm = jrtStartDttm;
    }

    public Instant getJrtEndDttm() {
        return jrtEndDttm;
    }

    public void setJrtEndDttm(Instant jrtEndDttm) {
        this.jrtEndDttm = jrtEndDttm;
    }

    public Long getJrtResult() {
        return jrtResult;
    }

    public void setJrtResult(Long jrtResult) {
        this.jrtResult = jrtResult;
    }

    public Long getJrtLog() {
        return jrtLog;
    }

    public void setJrtLog(Long jrtLog) {
        this.jrtLog = jrtLog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobResultDTO)) {
            return false;
        }

        return id != null && id.equals(((JobResultDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JobResultDTO{" +
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
