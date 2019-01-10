package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
