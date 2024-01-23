package com.legolas.cvpasrser.repository;

import com.legolas.cvpasrser.domain.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity,Long> {
}
