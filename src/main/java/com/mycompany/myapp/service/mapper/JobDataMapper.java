package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.JobDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link JobData} and its DTO {@link JobDataDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JobDataMapper extends EntityMapper<JobDataDTO, JobData> {



    default JobData fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobData jobData = new JobData();
        jobData.setId(id);
        return jobData;
    }
}
