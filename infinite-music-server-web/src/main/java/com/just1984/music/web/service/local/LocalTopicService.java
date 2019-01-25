package com.just1984.music.web.service.local;

import com.just1984.music.core.util.JsonMapper;
import com.just1984.music.model.enums.ResourceOriginEnum;
import com.just1984.music.model.enums.ResourceTypeEnum;
import com.just1984.music.model.vo.SongVo;
import com.just1984.music.model.vo.TopicVo;
import com.just1984.music.persistence.entity.Resource;
import com.just1984.music.persistence.entity.Topic;
import com.just1984.music.persistence.repository.ResourceRepository;
import com.just1984.music.persistence.repository.TopicRepository;
import com.just1984.music.web.service.TopicService;
import org.apache.commons.collections4.CollectionUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("localTopicService")
public class LocalTopicService implements TopicService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<TopicVo> getTopicList(int size) {
        List<Topic> topicList = topicRepository.getLatestTopicList(size);
        return topicList.stream().map(topic -> {
            TopicVo topicVo = new TopicVo();
            topicVo.setId(topic.getId());
            topicVo.setOriginId(topic.getOriginId());
            topicVo.setName(topic.getName());
            topicVo.setSongList(JsonMapper.string2Obj(topic.getTopSongInfos(), new TypeReference<List<SongVo>>() {}));
            Optional<Resource> resource = resourceRepository.findById(Long.valueOf(topic.getResourceIds()));
            if (resource.isPresent()) {
                topicVo.setPicUrl(resource.get().getUrl());
            }
            return topicVo;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void saveTopicList(List<TopicVo> topicVoList) {
        List<Topic> topicListToSave = topicVoList.stream().filter(topicVo -> {
            List<Topic> topicList = topicRepository.findByName(topicVo.getName());
            return CollectionUtils.isEmpty(topicList) ? true : false;
        }).map(topicVo -> {
            Resource resource = new Resource(ResourceTypeEnum.IMAGE, ResourceOriginEnum.QQ, topicVo.getPicUrl());
            resourceRepository.save(resource);
            Topic topic = new Topic();
            topic.setOriginId(topicVo.getId());
            topic.setName(topicVo.getName());
            topic.setResourceIds(resource.getId().toString());
            topic.setTopSongInfos(JsonMapper.obj2String(topicVo.getSongList()));
            return topic;
        }).collect(Collectors.toList());
        topicRepository.saveAll(topicListToSave);
    }
}
