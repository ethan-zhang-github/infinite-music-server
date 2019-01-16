package com.just1984.music.web.service;

import com.google.common.collect.Lists;
import com.just1984.music.model.enums.ResourceOriginEnum;
import com.just1984.music.model.enums.ResourceTypeEnum;
import com.just1984.music.model.vo.RecommendVo;
import com.just1984.music.persistence.entity.Recommend;
import com.just1984.music.persistence.entity.Resource;
import com.just1984.music.persistence.repository.RecommendRepository;
import com.just1984.music.persistence.repository.ResourceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
        List<RecommendVo> recommendVoList = Lists.newArrayList();
        recommendList.stream().forEach(recommend -> {
            if (StringUtils.isNotBlank(recommend.getResourceIds())) {
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
                recommendVoList.add(recommendVo);
            }
        });
        return recommendVoList;
    }

    @Transactional
    public void saveRecommendList(List<RecommendVo> recommendVoList) {
        List<RecommendVo> filteredRecommendVoList = recommendVoList.stream().filter(recommendVo -> {
            List<Recommend> recommendList = recommendRepository.findByOriginId(recommendVo.getId());
            if (CollectionUtils.isEmpty(recommendList)) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        for (RecommendVo recommendVo : filteredRecommendVoList) {
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
            recommendRepository.save(recommend);
        }
    }
}
