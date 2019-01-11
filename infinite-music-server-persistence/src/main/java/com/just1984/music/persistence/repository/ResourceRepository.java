package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
