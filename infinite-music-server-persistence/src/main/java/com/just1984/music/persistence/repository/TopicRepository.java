package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
