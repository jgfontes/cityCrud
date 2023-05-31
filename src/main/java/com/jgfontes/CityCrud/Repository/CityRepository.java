package com.jgfontes.CityCrud.Repository;

import com.jgfontes.CityCrud.Entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    public Optional<CityEntity> findByNameAndState(String name, String state);
}
