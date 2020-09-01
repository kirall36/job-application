package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class JobDataDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobDataDTO.class);
        JobDataDTO jobDataDTO1 = new JobDataDTO();
        jobDataDTO1.setId(1L);
        JobDataDTO jobDataDTO2 = new JobDataDTO();
        assertThat(jobDataDTO1).isNotEqualTo(jobDataDTO2);
        jobDataDTO2.setId(jobDataDTO1.getId());
        assertThat(jobDataDTO1).isEqualTo(jobDataDTO2);
        jobDataDTO2.setId(2L);
        assertThat(jobDataDTO1).isNotEqualTo(jobDataDTO2);
        jobDataDTO1.setId(null);
        assertThat(jobDataDTO1).isNotEqualTo(jobDataDTO2);
    }
}
