package com.just1984.music.persistence.repository;

import com.just1984.music.persistence.entity.Disc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscRepository extends JpaRepository<Disc, Long> {

    @Query(nativeQuery = true, value = "select d.* from disc d order by d.create_time desc limit ?")
    List<Disc> getLatestDiscList(int size);

    List<Disc> findByOriginId(Long originId);
}
