package com.image.gallery.repository;

import com.image.gallery.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Long> {
    boolean existsByName(String name);
}
