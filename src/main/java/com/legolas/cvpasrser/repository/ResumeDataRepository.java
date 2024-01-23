package com.legolas.cvpasrser.repository;

import com.legolas.cvpasrser.domain.ResumeDataEntity;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface ResumeDataRepository extends JpaRepository<ResumeDataEntity, Long> {
}
