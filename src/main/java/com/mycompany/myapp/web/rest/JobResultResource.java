package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.JobResultService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.JobResultDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.JobResult}.
 */
@RestController
@RequestMapping("/api")
public class JobResultResource {

    private final Logger log = LoggerFactory.getLogger(JobResultResource.class);

    private static final String ENTITY_NAME = "jobApplicationJobResult";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JobResultService jobResultService;

    public JobResultResource(JobResultService jobResultService) {
        this.jobResultService = jobResultService;
    }

    /**
     * {@code POST  /job-results} : Create a new jobResult.
     *
     * @param jobResultDTO the jobResultDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobResultDTO, or with status {@code 400 (Bad Request)} if the jobResult has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/job-results")
    public ResponseEntity<JobResultDTO> createJobResult(@RequestBody JobResultDTO jobResultDTO) throws URISyntaxException {
        log.debug("REST request to save JobResult : {}", jobResultDTO);
        if (jobResultDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobResult cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobResultDTO result = jobResultService.save(jobResultDTO);
        return ResponseEntity.created(new URI("/api/job-results/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /job-results} : Updates an existing jobResult.
     *
     * @param jobResultDTO the jobResultDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobResultDTO,
     * or with status {@code 400 (Bad Request)} if the jobResultDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jobResultDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/job-results")
    public ResponseEntity<JobResultDTO> updateJobResult(@RequestBody JobResultDTO jobResultDTO) throws URISyntaxException {
        log.debug("REST request to update JobResult : {}", jobResultDTO);
        if (jobResultDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobResultDTO result = jobResultService.save(jobResultDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jobResultDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /job-results} : get all the jobResults.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobResults in body.
     */
    @GetMapping("/job-results")
    public List<JobResultDTO> getAllJobResults() {
        log.debug("REST request to get all JobResults");
        return jobResultService.findAll();
    }

    /**
     * {@code GET  /job-results/:id} : get the "id" jobResult.
     *
     * @param id the id of the jobResultDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jobResultDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/job-results/{id}")
    public ResponseEntity<JobResultDTO> getJobResult(@PathVariable Long id) {
        log.debug("REST request to get JobResult : {}", id);
        Optional<JobResultDTO> jobResultDTO = jobResultService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobResultDTO);
    }

    /**
     * {@code DELETE  /job-results/:id} : delete the "id" jobResult.
     *
     * @param id the id of the jobResultDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/job-results/{id}")
    public ResponseEntity<Void> deleteJobResult(@PathVariable Long id) {
        log.debug("REST request to delete JobResult : {}", id);

        jobResultService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
