package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JobDataMapperTest {

    private JobDataMapper jobDataMapper;

    @BeforeEach
    public void setUp() {
        jobDataMapper = new JobDataMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(jobDataMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(jobDataMapper.fromId(null)).isNull();
    }
}
