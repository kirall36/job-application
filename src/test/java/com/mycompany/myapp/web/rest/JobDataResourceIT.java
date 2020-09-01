package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JobApplicationApp;
import com.mycompany.myapp.domain.JobData;
import com.mycompany.myapp.repository.JobDataRepository;
import com.mycompany.myapp.service.JobDataService;
import com.mycompany.myapp.service.dto.JobDataDTO;
import com.mycompany.myapp.service.mapper.JobDataMapper;

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

import com.mycompany.myapp.domain.enumeration.JobStatus;
/**
 * Integration tests for the {@link JobDataResource} REST controller.
 */
@SpringBootTest(classes = JobApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class JobDataResourceIT {

    private static final String DEFAULT_JOB_CODE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_JOB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_USER = "AAAAAAAAAA";
    private static final String UPDATED_JOB_USER = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_PARAMETER_JSON = "AAAAAAAAAA";
    private static final String UPDATED_JOB_PARAMETER_JSON = "BBBBBBBBBB";

    private static final Instant DEFAULT_JOB_CREATE_DTTM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOB_CREATE_DTTM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_JOB_INTERVAL_START_DTTM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOB_INTERVAL_START_DTTM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_JOB_INTERVAL_END_DTTM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOB_INTERVAL_END_DTTM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_JOB_NEXT_DTTM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOB_NEXT_DTTM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_JOB_SCHEDULE_EXPRESSION = "AAAAAAAAAA";
    private static final String UPDATED_JOB_SCHEDULE_EXPRESSION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_JOB_LAST_IS_SUCCESS_BOOL = false;
    private static final Boolean UPDATED_JOB_LAST_IS_SUCCESS_BOOL = true;

    private static final JobStatus DEFAULT_JOB_STATUS_CODE = JobStatus.SCHEDULED;
    private static final JobStatus UPDATED_JOB_STATUS_CODE = JobStatus.FINISHED;

    @Autowired
    private JobDataRepository jobDataRepository;

    @Autowired
    private JobDataMapper jobDataMapper;

    @Autowired
    private JobDataService jobDataService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJobDataMockMvc;

    private JobData jobData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JobData createEntity(EntityManager em) {
        JobData jobData = new JobData()
            .jobCode(DEFAULT_JOB_CODE)
            .jobName(DEFAULT_JOB_NAME)
            .jobUser(DEFAULT_JOB_USER)
            .jobParameterJson(DEFAULT_JOB_PARAMETER_JSON)
            .jobCreateDttm(DEFAULT_JOB_CREATE_DTTM)
            .jobIntervalStartDttm(DEFAULT_JOB_INTERVAL_START_DTTM)
            .jobIntervalEndDttm(DEFAULT_JOB_INTERVAL_END_DTTM)
            .jobNextDttm(DEFAULT_JOB_NEXT_DTTM)
            .jobScheduleExpression(DEFAULT_JOB_SCHEDULE_EXPRESSION)
            .jobLastIsSuccessBool(DEFAULT_JOB_LAST_IS_SUCCESS_BOOL)
            .jobStatusCode(DEFAULT_JOB_STATUS_CODE);
        return jobData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JobData createUpdatedEntity(EntityManager em) {
        JobData jobData = new JobData()
            .jobCode(UPDATED_JOB_CODE)
            .jobName(UPDATED_JOB_NAME)
            .jobUser(UPDATED_JOB_USER)
            .jobParameterJson(UPDATED_JOB_PARAMETER_JSON)
            .jobCreateDttm(UPDATED_JOB_CREATE_DTTM)
            .jobIntervalStartDttm(UPDATED_JOB_INTERVAL_START_DTTM)
            .jobIntervalEndDttm(UPDATED_JOB_INTERVAL_END_DTTM)
            .jobNextDttm(UPDATED_JOB_NEXT_DTTM)
            .jobScheduleExpression(UPDATED_JOB_SCHEDULE_EXPRESSION)
            .jobLastIsSuccessBool(UPDATED_JOB_LAST_IS_SUCCESS_BOOL)
            .jobStatusCode(UPDATED_JOB_STATUS_CODE);
        return jobData;
    }

    @BeforeEach
    public void initTest() {
        jobData = createEntity(em);
    }

    @Test
    @Transactional
    public void createJobData() throws Exception {
        int databaseSizeBeforeCreate = jobDataRepository.findAll().size();
        // Create the JobData
        JobDataDTO jobDataDTO = jobDataMapper.toDto(jobData);
        restJobDataMockMvc.perform(post("/api/job-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jobDataDTO)))
            .andExpect(status().isCreated());

        // Validate the JobData in the database
        List<JobData> jobDataList = jobDataRepository.findAll();
        assertThat(jobDataList).hasSize(databaseSizeBeforeCreate + 1);
        JobData testJobData = jobDataList.get(jobDataList.size() - 1);
        assertThat(testJobData.getJobCode()).isEqualTo(DEFAULT_JOB_CODE);
        assertThat(testJobData.getJobName()).isEqualTo(DEFAULT_JOB_NAME);
        assertThat(testJobData.getJobUser()).isEqualTo(DEFAULT_JOB_USER);
        assertThat(testJobData.getJobParameterJson()).isEqualTo(DEFAULT_JOB_PARAMETER_JSON);
        assertThat(testJobData.getJobCreateDttm()).isEqualTo(DEFAULT_JOB_CREATE_DTTM);
        assertThat(testJobData.getJobIntervalStartDttm()).isEqualTo(DEFAULT_JOB_INTERVAL_START_DTTM);
        assertThat(testJobData.getJobIntervalEndDttm()).isEqualTo(DEFAULT_JOB_INTERVAL_END_DTTM);
        assertThat(testJobData.getJobNextDttm()).isEqualTo(DEFAULT_JOB_NEXT_DTTM);
        assertThat(testJobData.getJobScheduleExpression()).isEqualTo(DEFAULT_JOB_SCHEDULE_EXPRESSION);
        assertThat(testJobData.isJobLastIsSuccessBool()).isEqualTo(DEFAULT_JOB_LAST_IS_SUCCESS_BOOL);
        assertThat(testJobData.getJobStatusCode()).isEqualTo(DEFAULT_JOB_STATUS_CODE);
    }

    @Test
    @Transactional
    public void createJobDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jobDataRepository.findAll().size();

        // Create the JobData with an existing ID
        jobData.setId(1L);
        JobDataDTO jobDataDTO = jobDataMapper.toDto(jobData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobDataMockMvc.perform(post("/api/job-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jobDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JobData in the database
        List<JobData> jobDataList = jobDataRepository.findAll();
        assertThat(jobDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllJobData() throws Exception {
        // Initialize the database
        jobDataRepository.saveAndFlush(jobData);

        // Get all the jobDataList
        restJobDataMockMvc.perform(get("/api/job-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jobData.getId().intValue())))
            .andExpect(jsonPath("$.[*].jobCode").value(hasItem(DEFAULT_JOB_CODE)))
            .andExpect(jsonPath("$.[*].jobName").value(hasItem(DEFAULT_JOB_NAME)))
            .andExpect(jsonPath("$.[*].jobUser").value(hasItem(DEFAULT_JOB_USER)))
            .andExpect(jsonPath("$.[*].jobParameterJson").value(hasItem(DEFAULT_JOB_PARAMETER_JSON)))
            .andExpect(jsonPath("$.[*].jobCreateDttm").value(hasItem(DEFAULT_JOB_CREATE_DTTM.toString())))
            .andExpect(jsonPath("$.[*].jobIntervalStartDttm").value(hasItem(DEFAULT_JOB_INTERVAL_START_DTTM.toString())))
            .andExpect(jsonPath("$.[*].jobIntervalEndDttm").value(hasItem(DEFAULT_JOB_INTERVAL_END_DTTM.toString())))
            .andExpect(jsonPath("$.[*].jobNextDttm").value(hasItem(DEFAULT_JOB_NEXT_DTTM.toString())))
            .andExpect(jsonPath("$.[*].jobScheduleExpression").value(hasItem(DEFAULT_JOB_SCHEDULE_EXPRESSION)))
            .andExpect(jsonPath("$.[*].jobLastIsSuccessBool").value(hasItem(DEFAULT_JOB_LAST_IS_SUCCESS_BOOL.booleanValue())))
            .andExpect(jsonPath("$.[*].jobStatusCode").value(hasItem(DEFAULT_JOB_STATUS_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getJobData() throws Exception {
        // Initialize the database
        jobDataRepository.saveAndFlush(jobData);

        // Get the jobData
        restJobDataMockMvc.perform(get("/api/job-data/{id}", jobData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jobData.getId().intValue()))
            .andExpect(jsonPath("$.jobCode").value(DEFAULT_JOB_CODE))
            .andExpect(jsonPath("$.jobName").value(DEFAULT_JOB_NAME))
            .andExpect(jsonPath("$.jobUser").value(DEFAULT_JOB_USER))
            .andExpect(jsonPath("$.jobParameterJson").value(DEFAULT_JOB_PARAMETER_JSON))
            .andExpect(jsonPath("$.jobCreateDttm").value(DEFAULT_JOB_CREATE_DTTM.toString()))
            .andExpect(jsonPath("$.jobIntervalStartDttm").value(DEFAULT_JOB_INTERVAL_START_DTTM.toString()))
            .andExpect(jsonPath("$.jobIntervalEndDttm").value(DEFAULT_JOB_INTERVAL_END_DTTM.toString()))
            .andExpect(jsonPath("$.jobNextDttm").value(DEFAULT_JOB_NEXT_DTTM.toString()))
            .andExpect(jsonPath("$.jobScheduleExpression").value(DEFAULT_JOB_SCHEDULE_EXPRESSION))
            .andExpect(jsonPath("$.jobLastIsSuccessBool").value(DEFAULT_JOB_LAST_IS_SUCCESS_BOOL.booleanValue()))
            .andExpect(jsonPath("$.jobStatusCode").value(DEFAULT_JOB_STATUS_CODE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingJobData() throws Exception {
        // Get the jobData
        restJobDataMockMvc.perform(get("/api/job-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJobData() throws Exception {
        // Initialize the database
        jobDataRepository.saveAndFlush(jobData);

        int databaseSizeBeforeUpdate = jobDataRepository.findAll().size();

        // Update the jobData
        JobData updatedJobData = jobDataRepository.findById(jobData.getId()).get();
        // Disconnect from session so that the updates on updatedJobData are not directly saved in db
        em.detach(updatedJobData);
        updatedJobData
            .jobCode(UPDATED_JOB_CODE)
            .jobName(UPDATED_JOB_NAME)
            .jobUser(UPDATED_JOB_USER)
            .jobParameterJson(UPDATED_JOB_PARAMETER_JSON)
            .jobCreateDttm(UPDATED_JOB_CREATE_DTTM)
            .jobIntervalStartDttm(UPDATED_JOB_INTERVAL_START_DTTM)
            .jobIntervalEndDttm(UPDATED_JOB_INTERVAL_END_DTTM)
            .jobNextDttm(UPDATED_JOB_NEXT_DTTM)
            .jobScheduleExpression(UPDATED_JOB_SCHEDULE_EXPRESSION)
            .jobLastIsSuccessBool(UPDATED_JOB_LAST_IS_SUCCESS_BOOL)
            .jobStatusCode(UPDATED_JOB_STATUS_CODE);
        JobDataDTO jobDataDTO = jobDataMapper.toDto(updatedJobData);

        restJobDataMockMvc.perform(put("/api/job-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jobDataDTO)))
            .andExpect(status().isOk());

        // Validate the JobData in the database
        List<JobData> jobDataList = jobDataRepository.findAll();
        assertThat(jobDataList).hasSize(databaseSizeBeforeUpdate);
        JobData testJobData = jobDataList.get(jobDataList.size() - 1);
        assertThat(testJobData.getJobCode()).isEqualTo(UPDATED_JOB_CODE);
        assertThat(testJobData.getJobName()).isEqualTo(UPDATED_JOB_NAME);
        assertThat(testJobData.getJobUser()).isEqualTo(UPDATED_JOB_USER);
        assertThat(testJobData.getJobParameterJson()).isEqualTo(UPDATED_JOB_PARAMETER_JSON);
        assertThat(testJobData.getJobCreateDttm()).isEqualTo(UPDATED_JOB_CREATE_DTTM);
        assertThat(testJobData.getJobIntervalStartDttm()).isEqualTo(UPDATED_JOB_INTERVAL_START_DTTM);
        assertThat(testJobData.getJobIntervalEndDttm()).isEqualTo(UPDATED_JOB_INTERVAL_END_DTTM);
        assertThat(testJobData.getJobNextDttm()).isEqualTo(UPDATED_JOB_NEXT_DTTM);
        assertThat(testJobData.getJobScheduleExpression()).isEqualTo(UPDATED_JOB_SCHEDULE_EXPRESSION);
        assertThat(testJobData.isJobLastIsSuccessBool()).isEqualTo(UPDATED_JOB_LAST_IS_SUCCESS_BOOL);
        assertThat(testJobData.getJobStatusCode()).isEqualTo(UPDATED_JOB_STATUS_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingJobData() throws Exception {
        int databaseSizeBeforeUpdate = jobDataRepository.findAll().size();

        // Create the JobData
        JobDataDTO jobDataDTO = jobDataMapper.toDto(jobData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJobDataMockMvc.perform(put("/api/job-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jobDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JobData in the database
        List<JobData> jobDataList = jobDataRepository.findAll();
        assertThat(jobDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJobData() throws Exception {
        // Initialize the database
        jobDataRepository.saveAndFlush(jobData);

        int databaseSizeBeforeDelete = jobDataRepository.findAll().size();

        // Delete the jobData
        restJobDataMockMvc.perform(delete("/api/job-data/{id}", jobData.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JobData> jobDataList = jobDataRepository.findAll();
        assertThat(jobDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
