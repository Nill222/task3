package com.project.task.entity;

import com.project.task.exception.TrainException;
import com.project.task.state.TrainState;
import com.project.task.state.impl.WaitingState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

import java.util.concurrent.Callable;

public class Train implements Callable<Boolean> {
    private static final Logger log = LogManager.getLogger();
    private final String name;
    private final int wagons;
    private final WagonsState wagonsState;
    private TrainState state;

    public Train(String name, int wagons, WagonsState wagonsState) {
        this.name = name;
        this.wagons = wagons;
        this.wagonsState = wagonsState;
        this.state = new WaitingState(this);
    }

    public int getWagons() {
        return wagons;
    }

    public WagonsState getWagonsState() {
        return wagonsState;
    }

    public String getName() {
        return name;
    }

    public TrainState getState() {
        return state;
    }

    public void setState(TrainState state) {
        this.state = state;
    }

    @Override
    public Boolean call() throws TrainException {
        return state.process();
    }
}
