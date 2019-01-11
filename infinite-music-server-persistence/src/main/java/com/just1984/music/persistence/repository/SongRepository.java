package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
