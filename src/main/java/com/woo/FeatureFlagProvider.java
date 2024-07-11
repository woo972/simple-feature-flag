package com.woo;

import com.woo.persistence.FeatureFlagRepository;
import com.woo.persistence.model.FeatureFlagEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeatureFlagProvider {

    @Autowired
    private FeatureFlagRepository featureFlagRepository;

    private static final List<FeatureFlagEntity> featureFlags = new ArrayList<>();
    @PostConstruct
    public void getAllEnabledFeatureFlags(){
        System.out.println("test");
        featureFlagRepository.save(FeatureFlagEntity.createNew().turnOn());
        featureFlagRepository.save(FeatureFlagEntity.createNew().turnOn());
        featureFlagRepository.save(FeatureFlagEntity.createNew().turnOn());

        featureFlags.addAll(featureFlagRepository.findEnabledFeatureFlags());
        for(FeatureFlagEntity flag : featureFlags){
            System.out.println(flag);
        }
    }

    public boolean isOn(long id) {
        return featureFlags.stream()
                .filter(flag -> flag.getId().equals(id))
                .anyMatch(FeatureFlagEntity::getIsOn);
    }

    public AppVersion getMinimumTargetVersion() {
        return AppVersion.from("1.1");
    }
}

