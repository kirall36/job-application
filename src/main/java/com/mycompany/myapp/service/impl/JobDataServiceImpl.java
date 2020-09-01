package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.JobDataService;
import com.mycompany.myapp.domain.JobData;
import com.mycompany.myapp.repository.JobDataRepository;
import com.mycompany.myapp.service.dto.JobDataDTO;
import com.mycompany.myapp.service.mapper.JobDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link JobData}.
 */
@Service
@Transactional
public class JobDataServiceImpl implements JobDataService {

    private final Logger log = LoggerFactory.getLogger(JobDataServiceImpl.class);

    private final JobDataRepository jobDataRepository;

    private final JobDataMapper jobDataMapper;

    public JobDataServiceImpl(JobDataRepository jobDataRepository, JobDataMapper jobDataMapper) {
        this.jobDataRepository = jobDataRepository;
        this.jobDataMapper = jobDataMapper;
    }

    /**
     * Save a jobData.
     *
     * @param jobDataDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public JobDataDTO save(JobDataDTO jobDataDTO) {
        log.debug("Request to save JobData : {}", jobDataDTO);
        JobData jobData = jobDataMapper.toEntity(jobDataDTO);
        jobData = jobDataRepository.save(jobData);
        return jobDataMapper.toDto(jobData);
    }

    /**
     * Get all the jobData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JobDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JobData");
        return jobDataRepository.findAll(pageable)
            .map(jobDataMapper::toDto);
    }


    /**
     * Get one jobData by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobDataDTO> findOne(Long id) {
        log.debug("Request to get JobData : {}", id);
        return jobDataRepository.findById(id)
            .map(jobDataMapper::toDto);
    }

    /**
     * Delete the jobData by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobData : {}", id);

        jobDataRepository.deleteById(id);
    }
}
