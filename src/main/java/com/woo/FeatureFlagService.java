package com.woo;

import org.springframework.stereotype.Component;

@Component
public class FeatureFlagService {

    public AppVersion getAppVersion() {
        return AppVersion.from("1.1");
    }
}
