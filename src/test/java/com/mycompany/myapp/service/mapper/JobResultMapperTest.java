package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JobResultMapperTest {

    private JobResultMapper jobResultMapper;

    @BeforeEach
    public void setUp() {
        jobResultMapper = new JobResultMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(jobResultMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(jobResultMapper.fromId(null)).isNull();
    }
}
