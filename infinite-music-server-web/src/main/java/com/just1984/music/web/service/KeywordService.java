package com.just1984.music.web.service;

import com.just1984.music.persistence.entity.Keyword;
import com.just1984.music.persistence.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    public Keyword save(Keyword keyword) {
        return keywordRepository.save(keyword);
    }
}
