package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class JobDataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobData.class);
        JobData jobData1 = new JobData();
        jobData1.setId(1L);
        JobData jobData2 = new JobData();
        jobData2.setId(jobData1.getId());
        assertThat(jobData1).isEqualTo(jobData2);
        jobData2.setId(2L);
        assertThat(jobData1).isNotEqualTo(jobData2);
        jobData1.setId(null);
        assertThat(jobData1).isNotEqualTo(jobData2);
    }
}
