package com.project.task.entity;

import com.project.task.exception.TrainException;
import com.project.task.state.TrainState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

import java.util.concurrent.Callable;

public class Train implements Callable<Boolean> {
    private static final Logger log = LogManager.getLogger();
    private final String name;
    private final Map<String, Integer> cargo;
    private final TrainState state;
    @Override
    public Boolean call() throws TrainException {
        return null;
    }
}
