package com.meanmachines.MeanStreamMachine.repositories;

import com.meanmachines.MeanStreamMachine.model.dbentities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media, UUID> {
    Media findByMediaId(UUID mediaId);

    @NonNull
    Media save(Media media);
}