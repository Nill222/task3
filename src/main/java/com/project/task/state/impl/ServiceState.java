package com.project.task.state.impl;

import com.project.task.entity.Station;
import com.project.task.entity.Train;
import com.project.task.exception.TrainException;
import com.project.task.state.TrainState;

public class ServiceState implements TrainState {
    private final Train train;
    private final Station station;
    private final Warehouse wh;

    @Override
    public Boolean process() throws TrainException {
        return null;
    }
}
