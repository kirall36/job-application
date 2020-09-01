package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class JobResultTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobResult.class);
        JobResult jobResult1 = new JobResult();
        jobResult1.setId(1L);
        JobResult jobResult2 = new JobResult();
        jobResult2.setId(jobResult1.getId());
        assertThat(jobResult1).isEqualTo(jobResult2);
        jobResult2.setId(2L);
        assertThat(jobResult1).isNotEqualTo(jobResult2);
        jobResult1.setId(null);
        assertThat(jobResult1).isNotEqualTo(jobResult2);
    }
}
