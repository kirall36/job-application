package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class JobResultDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobResultDTO.class);
        JobResultDTO jobResultDTO1 = new JobResultDTO();
        jobResultDTO1.setId(1L);
        JobResultDTO jobResultDTO2 = new JobResultDTO();
        assertThat(jobResultDTO1).isNotEqualTo(jobResultDTO2);
        jobResultDTO2.setId(jobResultDTO1.getId());
        assertThat(jobResultDTO1).isEqualTo(jobResultDTO2);
        jobResultDTO2.setId(2L);
        assertThat(jobResultDTO1).isNotEqualTo(jobResultDTO2);
        jobResultDTO1.setId(null);
        assertThat(jobResultDTO1).isNotEqualTo(jobResultDTO2);
    }
}
