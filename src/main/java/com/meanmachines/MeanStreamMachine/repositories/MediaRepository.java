package com.meanmachines.MeanStreamMachine.repositories;

import com.meanmachines.MeanStreamMachine.model.dbentities.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media, UUID> {
    Media findByMediaId(UUID mediaId);

}