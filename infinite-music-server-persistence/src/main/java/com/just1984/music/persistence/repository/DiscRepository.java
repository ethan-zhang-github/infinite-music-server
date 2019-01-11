package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.entity.Disc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscRepository extends JpaRepository<Disc, Long> {
}
