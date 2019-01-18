package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SingerRepository extends JpaRepository<Singer, Long> {

    @Query(nativeQuery = true, value = "select s.* from singer s where s.origin_id is not null " +
            "and s.alphabet is not null and s.resource_ids is not null order by s.create_time desc limit ?")
    List<Singer> getLatestSingerList(int size);

    List<Singer> findByName(String name);
}
