package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByName(String name);

    @Query(nativeQuery = true, value = "select t.* from topic t order by t.create_time desc limit ?")
    List<Topic> getLatestTopicList(int size);
}
