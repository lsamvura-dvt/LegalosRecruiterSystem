package com.legolas.cvpasrser.repository;

import com.legolas.cvpasrser.domain.ResumeData;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeDataRepository extends JpaRepository<ResumeData, Long> {
}
