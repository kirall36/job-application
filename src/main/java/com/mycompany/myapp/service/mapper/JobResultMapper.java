package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.JobResultDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link JobResult} and its DTO {@link JobResultDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JobResultMapper extends EntityMapper<JobResultDTO, JobResult> {



    default JobResult fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobResult jobResult = new JobResult();
        jobResult.setId(id);
        return jobResult;
    }
}
