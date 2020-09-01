package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.JobDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.JobData}.
 */
public interface JobDataService {

    /**
     * Save a jobData.
     *
     * @param jobDataDTO the entity to save.
     * @return the persisted entity.
     */
    JobDataDTO save(JobDataDTO jobDataDTO);

    /**
     * Get all the jobData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<JobDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" jobData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JobDataDTO> findOne(Long id);

    /**
     * Delete the "id" jobData.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
