package com.just1984.music.web.service.local;

import com.google.common.collect.Lists;
import com.just1984.music.model.enums.ResourceOriginEnum;
import com.just1984.music.model.enums.ResourceTypeEnum;
import com.just1984.music.model.vo.RecommendVo;
import com.just1984.music.persistence.entity.Recommend;
import com.just1984.music.persistence.entity.Resource;
import com.just1984.music.persistence.repository.RecommendRepository;
import com.just1984.music.persistence.repository.ResourceRepository;
import com.just1984.music.web.service.RecommendService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("localRecommendService")
public class LocalRecommendService implements RecommendService {

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public List<RecommendVo> getRecommendList(int size) {
        List<Recommend> recommendList = recommendRepository.getLatestRecommendList(size);
        return recommendList.stream().map(recommend -> {
            RecommendVo recommendVo = new RecommendVo();
            recommendVo.setId(recommend.getId());
            Arrays.stream(recommend.getResourceIds().split(",")).forEach(resourceId -> {
                Optional<Resource> resource = resourceRepository.findById(Long.valueOf(resourceId));
                if (resource.isPresent()) {
                    if (resource.get().getType() == ResourceTypeEnum.IMAGE) {
                        recommendVo.setPicUrl(resource.get().getUrl());
                    }
                    if (resource.get().getType() == ResourceTypeEnum.WEB) {
                        recommendVo.setLinkUrl(resource.get().getUrl());
                    }
                }
            });
            return recommendVo;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void saveRecommendList(List<RecommendVo> recommendVoList) {
        List<Recommend> recommendListToSave = recommendVoList.stream().filter(recommendVo -> {
            List<Recommend> recommendList = recommendRepository.findByOriginId(recommendVo.getId());
            return CollectionUtils.isEmpty(recommendList) ? true : false;
        }).map(recommendVo -> {
            List<Resource> resourceList = Lists.newArrayList();
            resourceList.add(new Resource(ResourceTypeEnum.IMAGE,
                    ResourceOriginEnum.QQ, recommendVo.getPicUrl()));
            resourceList.add(new Resource(ResourceTypeEnum.WEB,
                    ResourceOriginEnum.QQ, recommendVo.getLinkUrl()));
            resourceRepository.saveAll(resourceList);
            Recommend recommend = new Recommend();
            recommend.setOriginId(recommendVo.getId());
            recommend.setResourceIds(resourceList.stream()
                    .map(resource -> resource.getId().toString())
                    .collect(Collectors.joining(",")));
            return recommend;
        }).collect(Collectors.toList());
        recommendRepository.saveAll(recommendListToSave);
    }
}
