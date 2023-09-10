package com.meanmachines.MeanStreamMachine;

import com.meanmachines.MeanStreamMachine.model.dbentities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer> {

    @Query("SELECT t.note FROM test t WHERE t.id = :id")
    String findNoteById(@Param("id") Integer id);
}