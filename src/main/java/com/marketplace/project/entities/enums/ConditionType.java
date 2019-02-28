package com.marketplace.project.entities.enums;


public enum ConditionType {

    NEW("NEW"),
    USED("USED");

    private String condition;

    ConditionType(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
