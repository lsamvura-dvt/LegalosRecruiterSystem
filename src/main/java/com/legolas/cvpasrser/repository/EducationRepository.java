package com.legolas.cvpasrser.repository;

import com.legolas.cvpasrser.domain.Education;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education,Long> {
}
