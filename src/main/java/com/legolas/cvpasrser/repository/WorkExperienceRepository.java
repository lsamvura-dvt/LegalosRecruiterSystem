package com.legolas.cvpasrser.repository;

import com.legolas.cvpasrser.domain.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

}
