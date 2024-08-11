package vkapi.pageobject.enums;

import framework.utils.ReadValuesFromConfig;

public enum SideMenuLabels {
    MY_PROFILE(ReadValuesFromConfig.readVocabularyValues("myProfile"));
    private String label;

    SideMenuLabels(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
