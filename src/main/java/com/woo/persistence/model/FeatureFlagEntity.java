package com.woo.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "feature_flag")
public class FeatureFlagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_on", nullable = false)
    private Boolean isOn;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    public FeatureFlagEntity() {}

    public static FeatureFlagEntity createNew() {
        return new FeatureFlagEntity(false, false);
    }

    private FeatureFlagEntity(Boolean isOn, Boolean isArchived) {
        this.isOn = isOn;
        this.isArchived = isArchived;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Boolean getIsOn() {
        return isOn;
    }

    public Boolean getIsArchived() {
        return isArchived;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getArchivedAt() {
        return archivedAt;
    }

    public FeatureFlagEntity turnOn() {
        this.isOn = true;
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    public FeatureFlagEntity turnOff() {
        this.isOn = false;
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    public FeatureFlagEntity archive() {
        this.isOn = false;
        this.updatedAt = LocalDateTime.now();
        this.archivedAt = LocalDateTime.now();
        return this;
    }

    @Override
    public String toString(){
        return "id: " + id  + ", isOn: " + isOn;
    }
}
