package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.JobResultDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.JobResult}.
 */
public interface JobResultService {

    /**
     * Save a jobResult.
     *
     * @param jobResultDTO the entity to save.
     * @return the persisted entity.
     */
    JobResultDTO save(JobResultDTO jobResultDTO);

    /**
     * Get all the jobResults.
     *
     * @return the list of entities.
     */
    List<JobResultDTO> findAll();


    /**
     * Get the "id" jobResult.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JobResultDTO> findOne(Long id);

    /**
     * Delete the "id" jobResult.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
