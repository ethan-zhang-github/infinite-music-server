package com.just1984.music.web.service.local;

import com.just1984.music.model.enums.ResourceOriginEnum;
import com.just1984.music.model.enums.ResourceTypeEnum;
import com.just1984.music.model.vo.DiscVo;
import com.just1984.music.persistence.entity.Disc;
import com.just1984.music.persistence.entity.Resource;
import com.just1984.music.persistence.entity.Singer;
import com.just1984.music.persistence.repository.DiscRepository;
import com.just1984.music.persistence.repository.ResourceRepository;
import com.just1984.music.persistence.repository.SingerRepository;
import com.just1984.music.web.component.converter.Singer2SingerVoConverter;
import com.just1984.music.web.service.DiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("localDiscService")
public class LocalDiscService implements DiscService {

    @Autowired
    private DiscRepository discRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public List<DiscVo> getDiscList(int size) {
        List<Disc> discList = discRepository.getLatestDiscList(size);
        return discList.stream().map(disc -> {
            DiscVo discVo = new DiscVo();
            discVo.setId(disc.getId());
            discVo.setName(disc.getName());
            Optional<Resource> resource = resourceRepository.findById(Long.valueOf(disc.getResourceIds()));
            if (resource.isPresent()) {
                discVo.setImgUrl(resource.get().getUrl());
            }
            discVo.setSinger(Singer2SingerVoConverter.INSTANCE.convert(disc.getSinger()));
            return discVo;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void saveDiscList(List<DiscVo> discVoList) {
        List<DiscVo> filteredDiscVoList = discVoList.stream().filter(discVo -> {
            List<Disc> discList = discRepository.findByOriginId(discVo.getId());
            return CollectionUtils.isEmpty(discList) ? true : false;
        }).collect(Collectors.toList());
        for (DiscVo discVo : filteredDiscVoList) {
            Resource resource = new Resource(ResourceTypeEnum.IMAGE, ResourceOriginEnum.QQ, discVo.getImgUrl());
            resourceRepository.save(resource);
            List<Singer> singerList = singerRepository.findByName(discVo.getSinger().getName());
            Singer singer = null;
            if (CollectionUtils.isEmpty(singerList)) {
                singer = new Singer();
                singer.setName(discVo.getSinger().getName());
                singerRepository.save(singer);
            } else {
                singer = singerList.get(0);
            }
            Disc disc = new Disc();
            disc.setOriginId(discVo.getId());
            disc.setName(discVo.getName());
            disc.setResourceIds(resource.getId().toString());
            disc.setSinger(singer);
            discRepository.save(disc);
        }
    }
}
