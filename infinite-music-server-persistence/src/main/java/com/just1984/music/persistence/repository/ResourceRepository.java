package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
