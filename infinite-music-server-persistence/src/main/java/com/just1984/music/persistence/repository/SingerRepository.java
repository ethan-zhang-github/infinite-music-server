package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.domain.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerRepository extends JpaRepository<Singer, Long> {
}
