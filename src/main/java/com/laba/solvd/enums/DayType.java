package com.laba.solvd.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum DayType {
    WORKDAY(true),
    DAY_OFF(false);

    private static final Logger LOGGER = LogManager.getLogger(DayType.class);

    private boolean presenceAtWork;

    static {
        LOGGER.info("Don't forget to mark your workdays and days off in your schedule");
    }

    DayType(boolean presenceAtWork) {
        this.presenceAtWork = presenceAtWork;
    }

    public boolean isPresenceAtWork() {
        return presenceAtWork;
    }
}
