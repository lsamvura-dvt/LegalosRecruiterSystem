package com.legolas.cvpasrser.repository;

import com.legolas.cvpasrser.domain.PersonalInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformationEntity,Long> {
}
