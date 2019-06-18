package com.image.gallery.repository;

import com.image.gallery.model.Image;
import com.image.gallery.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByUserId(Long id);

    Image findByName(String image);
}
