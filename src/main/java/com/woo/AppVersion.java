package com.woo;

public class AppVersion {
    private final int major;
    private final int minor;

    public AppVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    public static AppVersion from(String appVersion) {
        String[] appVersionArray = appVersion.split("\\.");
        int major = Integer.parseInt(appVersionArray[0]);
        int minor = Integer.parseInt(appVersionArray[1]);
        return new AppVersion(major, minor);
    }

    public boolean isTargetVersion(AppVersion featureFlagMinimumTargetVersion) {
        if (this.major > featureFlagMinimumTargetVersion.major) {
            return true;
        }

        if (this.major == featureFlagMinimumTargetVersion.major) {
            return this.minor >= featureFlagMinimumTargetVersion.minor;
        }

        return false;
    }
}
