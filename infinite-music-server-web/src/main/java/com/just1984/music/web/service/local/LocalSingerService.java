package com.just1984.music.web.service.local;

import com.google.common.collect.Lists;
import com.just1984.music.model.enums.ResourceOriginEnum;
import com.just1984.music.model.enums.ResourceTypeEnum;
import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.persistence.entity.Resource;
import com.just1984.music.persistence.entity.Singer;
import com.just1984.music.persistence.repository.ResourceRepository;
import com.just1984.music.persistence.repository.SingerRepository;
import com.just1984.music.web.component.converter.Singer2SingerVoConverter;
import com.just1984.music.web.component.converter.SingerVo2SingerConverter;
import com.just1984.music.web.config.property.MusicProperties;
import com.just1984.music.web.service.SingerService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("localSingerService")
public class LocalSingerService implements SingerService {

    @Autowired
    private MusicProperties musicProperties;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public List<SingerVo> getSingerList(int size) {
        List<Singer> singerList = singerRepository.getLatestSingerList(size);
        return singerList.stream().map(singer -> {
            SingerVo singerVo = Singer2SingerVoConverter.INSTANCE.convert(singer);
            Optional<Resource> resource = resourceRepository.findById(Long.valueOf(singer.getResourceIds()));
            if (resource.isPresent()) {
                singerVo.setAvatar(resource.get().getUrl());
            }
            return singerVo;
        }).collect(Collectors.toList());
    }

    @Override
    public SingerVo getById(String id) {
        return null;
    }

    @Transactional
    public void saveSingerList(List<SingerVo> singerVoList) {
        List<Singer> singerListToSave = Lists.newArrayList();
        singerVoList.stream().forEach(singerVo -> {
            Resource resource = new Resource(ResourceTypeEnum.IMAGE, ResourceOriginEnum.QQ,
                    String.format(musicProperties.getQq().getSingerAvatarFormatter(), singerVo.getMid()));
            resourceRepository.save(resource);
            List<Singer> singerList = singerRepository.findByName(singerVo.getName());
            if (CollectionUtils.isEmpty(singerList)) {
                Singer singer = SingerVo2SingerConverter.INSTANCE.convert(singerVo);
                singer.setOriginId(singerVo.getId());
                singer.setResourceIds(resource.getId().toString());
                singerListToSave.add(singer);
            } else {
                Singer singer = singerList.get(0);
                singer.setOriginId(singerVo.getId());
                singer.setMid(singerVo.getMid());
                singer.setResourceIds(resource.getId().toString());
                singerListToSave.add(singer);
            }
        });
        singerRepository.saveAll(singerListToSave);
    }
}
