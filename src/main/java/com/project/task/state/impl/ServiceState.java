package com.project.task.state.impl;

import com.project.task.entity.Station;
import com.project.task.entity.StationPath;
import com.project.task.entity.Train;
import com.project.task.entity.WagonsState;
import com.project.task.exception.TrainException;
import com.project.task.state.TrainState;

import java.util.concurrent.TimeUnit;


public class ServiceState implements TrainState {
    private final Train train;
    private final StationPath path;
    private final Station station;

    public ServiceState(Train train, StationPath path, Station station) {
        this.train = train;
        this.path = path;
        this.station = station;
    }

    @Override
    public Boolean process() throws TrainException {
        path.get();
        try {
            if(train.getWagonsState() == WagonsState.UNLOADING) {
                station.addWagons(train.getWagons());
            } else {
                station.removeWagons(train.getWagons());
            }
            TimeUnit.SECONDS.sleep(2);
            train.setState(new FinishedState(train));
            return train.getState().process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new TrainException("Interrupted while servicing train", e);
        } catch (IllegalStateException e) {
            throw new TrainException("Illegal state", e);
        } finally {
            path.give();
        }

    }
}
