package com.legolas.cvpasrser.repository;

import com.legolas.cvpasrser.domain.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity,Long> {
}
