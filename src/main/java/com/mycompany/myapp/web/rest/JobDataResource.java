package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.JobDataService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.JobDataDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.JobData}.
 */
@RestController
@RequestMapping("/api")
public class JobDataResource {

    private final Logger log = LoggerFactory.getLogger(JobDataResource.class);

    private static final String ENTITY_NAME = "jobApplicationJobData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JobDataService jobDataService;

    public JobDataResource(JobDataService jobDataService) {
        this.jobDataService = jobDataService;
    }

    /**
     * {@code POST  /job-data} : Create a new jobData.
     *
     * @param jobDataDTO the jobDataDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobDataDTO, or with status {@code 400 (Bad Request)} if the jobData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/job-data")
    public ResponseEntity<JobDataDTO> createJobData(@RequestBody JobDataDTO jobDataDTO) throws URISyntaxException {
        log.debug("REST request to save JobData : {}", jobDataDTO);
        if (jobDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobDataDTO result = jobDataService.save(jobDataDTO);
        return ResponseEntity.created(new URI("/api/job-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /job-data} : Updates an existing jobData.
     *
     * @param jobDataDTO the jobDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobDataDTO,
     * or with status {@code 400 (Bad Request)} if the jobDataDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jobDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/job-data")
    public ResponseEntity<JobDataDTO> updateJobData(@RequestBody JobDataDTO jobDataDTO) throws URISyntaxException {
        log.debug("REST request to update JobData : {}", jobDataDTO);
        if (jobDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobDataDTO result = jobDataService.save(jobDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jobDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /job-data} : get all the jobData.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobData in body.
     */
    @GetMapping("/job-data")
    public ResponseEntity<List<JobDataDTO>> getAllJobData(Pageable pageable) {
        log.debug("REST request to get a page of JobData");
        Page<JobDataDTO> page = jobDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /job-data/:id} : get the "id" jobData.
     *
     * @param id the id of the jobDataDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jobDataDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/job-data/{id}")
    public ResponseEntity<JobDataDTO> getJobData(@PathVariable Long id) {
        log.debug("REST request to get JobData : {}", id);
        Optional<JobDataDTO> jobDataDTO = jobDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobDataDTO);
    }

    /**
     * {@code DELETE  /job-data/:id} : delete the "id" jobData.
     *
     * @param id the id of the jobDataDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/job-data/{id}")
    public ResponseEntity<Void> deleteJobData(@PathVariable Long id) {
        log.debug("REST request to delete JobData : {}", id);

        jobDataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
