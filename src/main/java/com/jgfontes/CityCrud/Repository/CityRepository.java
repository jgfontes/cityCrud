package com.jgfontes.CityCrud.Repository;

import com.jgfontes.CityCrud.Entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
}
