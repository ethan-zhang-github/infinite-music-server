package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByName(String name);
}
