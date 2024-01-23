package com.legolas.cvpasrser.repository;

import com.legolas.cvpasrser.domain.SkillEntity;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
}
