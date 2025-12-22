package com.project.task.state.impl;

import com.project.task.entity.Train;
import com.project.task.exception.TrainException;
import com.project.task.state.TrainState;

public class WaitingState implements TrainState {
    private final Train train;

    public WaitingState(Train train) {
        this.train = train;
    }

    @Override
    public Boolean process() throws TrainException {
        return RailwayManager.getInstance().handleTrain(train);
    }
}
