package com.project.task.state.impl;

import com.project.task.entity.Train;
import com.project.task.state.TrainState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FinishedState implements TrainState {
    private static final Logger log = LogManager.getLogger();
    private final Train train;

    public FinishedState(final Train train) {
        this.train = train;
    }

    @Override
    public Boolean process() {
        log.info("train {}", train.getName());
        return true;
    }
}
