package com.project.task.state;

import com.project.task.exception.TrainException;

public interface TrainState {
    Boolean process() throws TrainException;
}
