package com.woo.persistence;

import com.woo.persistence.model.FeatureFlagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeatureFlagRepository extends JpaRepository<FeatureFlagEntity, Long> {
    @Query("SELECT f FROM FeatureFlagEntity f WHERE f.isArchived = false AND f.isOn = true")
    List<FeatureFlagEntity> findEnabledFeatureFlags();
}
