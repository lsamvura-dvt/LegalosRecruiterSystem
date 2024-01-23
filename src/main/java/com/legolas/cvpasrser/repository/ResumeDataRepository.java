package com.legolas.cvpasrser.repository;

import com.legolas.cvpasrser.domain.ResumeDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeDataRepository extends JpaRepository<ResumeDataEntity, Long> {
}
