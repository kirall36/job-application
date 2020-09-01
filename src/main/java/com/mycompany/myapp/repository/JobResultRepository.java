package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.JobResult;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the JobResult entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobResultRepository extends JpaRepository<JobResult, Long> {
}
