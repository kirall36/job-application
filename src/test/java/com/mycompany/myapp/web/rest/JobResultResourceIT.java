package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JobApplicationApp;
import com.mycompany.myapp.domain.JobResult;
import com.mycompany.myapp.repository.JobResultRepository;
import com.mycompany.myapp.service.JobResultService;
import com.mycompany.myapp.service.dto.JobResultDTO;
import com.mycompany.myapp.service.mapper.JobResultMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.JobResultStatus;
/**
 * Integration tests for the {@link JobResultResource} REST controller.
 */
@SpringBootTest(classes = JobApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class JobResultResourceIT {

    private static final String DEFAULT_JOB_ID = "AAAAAAAAAA";
    private static final String UPDATED_JOB_ID = "BBBBBBBBBB";

    private static final JobResultStatus DEFAULT_JRT_STATUS_CODE = JobResultStatus.SCHEDULED;
    private static final JobResultStatus UPDATED_JRT_STATUS_CODE = JobResultStatus.CANCELLED;

    private static final Instant DEFAULT_JRT_START_DTTM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JRT_START_DTTM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_JRT_END_DTTM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JRT_END_DTTM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_JRT_RESULT = 1L;
    private static final Long UPDATED_JRT_RESULT = 2L;

    private static final Long DEFAULT_JRT_LOG = 1L;
    private static final Long UPDATED_JRT_LOG = 2L;

    @Autowired
    private JobResultRepository jobResultRepository;

    @Autowired
    private JobResultMapper jobResultMapper;

    @Autowired
    private JobResultService jobResultService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJobResultMockMvc;

    private JobResult jobResult;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JobResult createEntity(EntityManager em) {
        JobResult jobResult = new JobResult()
            .jobId(DEFAULT_JOB_ID)
            .jrtStatusCode(DEFAULT_JRT_STATUS_CODE)
            .jrtStartDttm(DEFAULT_JRT_START_DTTM)
            .jrtEndDttm(DEFAULT_JRT_END_DTTM)
            .jrtResult(DEFAULT_JRT_RESULT)
            .jrtLog(DEFAULT_JRT_LOG);
        return jobResult;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JobResult createUpdatedEntity(EntityManager em) {
        JobResult jobResult = new JobResult()
            .jobId(UPDATED_JOB_ID)
            .jrtStatusCode(UPDATED_JRT_STATUS_CODE)
            .jrtStartDttm(UPDATED_JRT_START_DTTM)
            .jrtEndDttm(UPDATED_JRT_END_DTTM)
            .jrtResult(UPDATED_JRT_RESULT)
            .jrtLog(UPDATED_JRT_LOG);
        return jobResult;
    }

    @BeforeEach
    public void initTest() {
        jobResult = createEntity(em);
    }

    @Test
    @Transactional
    public void createJobResult() throws Exception {
        int databaseSizeBeforeCreate = jobResultRepository.findAll().size();
        // Create the JobResult
        JobResultDTO jobResultDTO = jobResultMapper.toDto(jobResult);
        restJobResultMockMvc.perform(post("/api/job-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jobResultDTO)))
            .andExpect(status().isCreated());

        // Validate the JobResult in the database
        List<JobResult> jobResultList = jobResultRepository.findAll();
        assertThat(jobResultList).hasSize(databaseSizeBeforeCreate + 1);
        JobResult testJobResult = jobResultList.get(jobResultList.size() - 1);
        assertThat(testJobResult.getJobId()).isEqualTo(DEFAULT_JOB_ID);
        assertThat(testJobResult.getJrtStatusCode()).isEqualTo(DEFAULT_JRT_STATUS_CODE);
        assertThat(testJobResult.getJrtStartDttm()).isEqualTo(DEFAULT_JRT_START_DTTM);
        assertThat(testJobResult.getJrtEndDttm()).isEqualTo(DEFAULT_JRT_END_DTTM);
        assertThat(testJobResult.getJrtResult()).isEqualTo(DEFAULT_JRT_RESULT);
        assertThat(testJobResult.getJrtLog()).isEqualTo(DEFAULT_JRT_LOG);
    }

    @Test
    @Transactional
    public void createJobResultWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jobResultRepository.findAll().size();

        // Create the JobResult with an existing ID
        jobResult.setId(1L);
        JobResultDTO jobResultDTO = jobResultMapper.toDto(jobResult);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobResultMockMvc.perform(post("/api/job-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jobResultDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JobResult in the database
        List<JobResult> jobResultList = jobResultRepository.findAll();
        assertThat(jobResultList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllJobResults() throws Exception {
        // Initialize the database
        jobResultRepository.saveAndFlush(jobResult);

        // Get all the jobResultList
        restJobResultMockMvc.perform(get("/api/job-results?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jobResult.getId().intValue())))
            .andExpect(jsonPath("$.[*].jobId").value(hasItem(DEFAULT_JOB_ID)))
            .andExpect(jsonPath("$.[*].jrtStatusCode").value(hasItem(DEFAULT_JRT_STATUS_CODE.toString())))
            .andExpect(jsonPath("$.[*].jrtStartDttm").value(hasItem(DEFAULT_JRT_START_DTTM.toString())))
            .andExpect(jsonPath("$.[*].jrtEndDttm").value(hasItem(DEFAULT_JRT_END_DTTM.toString())))
            .andExpect(jsonPath("$.[*].jrtResult").value(hasItem(DEFAULT_JRT_RESULT.intValue())))
            .andExpect(jsonPath("$.[*].jrtLog").value(hasItem(DEFAULT_JRT_LOG.intValue())));
    }
    
    @Test
    @Transactional
    public void getJobResult() throws Exception {
        // Initialize the database
        jobResultRepository.saveAndFlush(jobResult);

        // Get the jobResult
        restJobResultMockMvc.perform(get("/api/job-results/{id}", jobResult.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jobResult.getId().intValue()))
            .andExpect(jsonPath("$.jobId").value(DEFAULT_JOB_ID))
            .andExpect(jsonPath("$.jrtStatusCode").value(DEFAULT_JRT_STATUS_CODE.toString()))
            .andExpect(jsonPath("$.jrtStartDttm").value(DEFAULT_JRT_START_DTTM.toString()))
            .andExpect(jsonPath("$.jrtEndDttm").value(DEFAULT_JRT_END_DTTM.toString()))
            .andExpect(jsonPath("$.jrtResult").value(DEFAULT_JRT_RESULT.intValue()))
            .andExpect(jsonPath("$.jrtLog").value(DEFAULT_JRT_LOG.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingJobResult() throws Exception {
        // Get the jobResult
        restJobResultMockMvc.perform(get("/api/job-results/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJobResult() throws Exception {
        // Initialize the database
        jobResultRepository.saveAndFlush(jobResult);

        int databaseSizeBeforeUpdate = jobResultRepository.findAll().size();

        // Update the jobResult
        JobResult updatedJobResult = jobResultRepository.findById(jobResult.getId()).get();
        // Disconnect from session so that the updates on updatedJobResult are not directly saved in db
        em.detach(updatedJobResult);
        updatedJobResult
            .jobId(UPDATED_JOB_ID)
            .jrtStatusCode(UPDATED_JRT_STATUS_CODE)
            .jrtStartDttm(UPDATED_JRT_START_DTTM)
            .jrtEndDttm(UPDATED_JRT_END_DTTM)
            .jrtResult(UPDATED_JRT_RESULT)
            .jrtLog(UPDATED_JRT_LOG);
        JobResultDTO jobResultDTO = jobResultMapper.toDto(updatedJobResult);

        restJobResultMockMvc.perform(put("/api/job-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jobResultDTO)))
            .andExpect(status().isOk());

        // Validate the JobResult in the database
        List<JobResult> jobResultList = jobResultRepository.findAll();
        assertThat(jobResultList).hasSize(databaseSizeBeforeUpdate);
        JobResult testJobResult = jobResultList.get(jobResultList.size() - 1);
        assertThat(testJobResult.getJobId()).isEqualTo(UPDATED_JOB_ID);
        assertThat(testJobResult.getJrtStatusCode()).isEqualTo(UPDATED_JRT_STATUS_CODE);
        assertThat(testJobResult.getJrtStartDttm()).isEqualTo(UPDATED_JRT_START_DTTM);
        assertThat(testJobResult.getJrtEndDttm()).isEqualTo(UPDATED_JRT_END_DTTM);
        assertThat(testJobResult.getJrtResult()).isEqualTo(UPDATED_JRT_RESULT);
        assertThat(testJobResult.getJrtLog()).isEqualTo(UPDATED_JRT_LOG);
    }

    @Test
    @Transactional
    public void updateNonExistingJobResult() throws Exception {
        int databaseSizeBeforeUpdate = jobResultRepository.findAll().size();

        // Create the JobResult
        JobResultDTO jobResultDTO = jobResultMapper.toDto(jobResult);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJobResultMockMvc.perform(put("/api/job-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jobResultDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JobResult in the database
        List<JobResult> jobResultList = jobResultRepository.findAll();
        assertThat(jobResultList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJobResult() throws Exception {
        // Initialize the database
        jobResultRepository.saveAndFlush(jobResult);

        int databaseSizeBeforeDelete = jobResultRepository.findAll().size();

        // Delete the jobResult
        restJobResultMockMvc.perform(delete("/api/job-results/{id}", jobResult.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JobResult> jobResultList = jobResultRepository.findAll();
        assertThat(jobResultList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
