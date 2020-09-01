package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.JobData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the JobData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobDataRepository extends JpaRepository<JobData, Long> {
}
