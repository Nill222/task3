package com.project.task.service;

import com.project.task.entity.Station;
import com.project.task.entity.StationPath;
import com.project.task.entity.Train;
import com.project.task.exception.TrainException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public interface RailwayManager {
    void init(List<StationPath> stationPath, Station station);
    List<Future<Boolean>> processAll(List<Train> trains);
    Boolean handleTrain(Train train) throws TrainException;
    void shutdown();
}
