package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {

    @Query(nativeQuery = true, value = "select r.* from recommend r order by r.create_time desc limit ?")
    List<Recommend> getLatestRecommendList(int size);

    List<Recommend> findByOriginId(Long originId);
}
