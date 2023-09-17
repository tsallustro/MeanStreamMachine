package com.meanmachines.MeanStreamMachine.repositories;

import com.meanmachines.MeanStreamMachine.model.dbentities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Integer> {


    @Override
    Optional<Test> findById(Integer integer);
}