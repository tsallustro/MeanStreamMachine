package com.meanmachines.MeanStreamMachine.repositories;

import com.meanmachines.MeanStreamMachine.model.dbentities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media, UUID> {
    Media findByMediaId(String mediaId);

    @NonNull
    Media save(Media media);
    @Query(value = "SELECT * FROM media", nativeQuery = true)
    List<Media> selectAll();
}