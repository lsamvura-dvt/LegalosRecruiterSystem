package com.legolas.cvpasrser.repository;

import com.legolas.cvpasrser.domain.WorkExperience;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Hidden
@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience,Long> {

}
