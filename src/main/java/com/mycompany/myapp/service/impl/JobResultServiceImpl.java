package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.JobResultService;
import com.mycompany.myapp.domain.JobResult;
import com.mycompany.myapp.repository.JobResultRepository;
import com.mycompany.myapp.service.dto.JobResultDTO;
import com.mycompany.myapp.service.mapper.JobResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link JobResult}.
 */
@Service
@Transactional
public class JobResultServiceImpl implements JobResultService {

    private final Logger log = LoggerFactory.getLogger(JobResultServiceImpl.class);

    private final JobResultRepository jobResultRepository;

    private final JobResultMapper jobResultMapper;

    public JobResultServiceImpl(JobResultRepository jobResultRepository, JobResultMapper jobResultMapper) {
        this.jobResultRepository = jobResultRepository;
        this.jobResultMapper = jobResultMapper;
    }

    /**
     * Save a jobResult.
     *
     * @param jobResultDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public JobResultDTO save(JobResultDTO jobResultDTO) {
        log.debug("Request to save JobResult : {}", jobResultDTO);
        JobResult jobResult = jobResultMapper.toEntity(jobResultDTO);
        jobResult = jobResultRepository.save(jobResult);
        return jobResultMapper.toDto(jobResult);
    }

    /**
     * Get all the jobResults.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<JobResultDTO> findAll() {
        log.debug("Request to get all JobResults");
        return jobResultRepository.findAll().stream()
            .map(jobResultMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one jobResult by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobResultDTO> findOne(Long id) {
        log.debug("Request to get JobResult : {}", id);
        return jobResultRepository.findById(id)
            .map(jobResultMapper::toDto);
    }

    /**
     * Delete the jobResult by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobResult : {}", id);

        jobResultRepository.deleteById(id);
    }
}
