package com.project.task.service.impl;

import com.project.task.entity.Station;
import com.project.task.entity.StationPath;
import com.project.task.entity.Train;
import com.project.task.exception.TrainException;
import com.project.task.service.RailwayManager;
import com.project.task.state.impl.ServiceState;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RailwayManagerImpl implements RailwayManager {
    private static class Holder {
        private static final RailwayManager INSTANCE = new RailwayManagerImpl();
    }

    public static RailwayManager getInstance() {
        return Holder.INSTANCE;
    }

    private List<StationPath> stationPath;
    private Station station;
    private ExecutorService executor;

    @Override
    public void init(List<StationPath> stationPath, Station station) {
        this.stationPath = stationPath;
        this.station = station;
        this.executor = Executors.newFixedThreadPool(stationPath.size());
    }

    @Override
    public List<Future<Boolean>> processAll(List<Train> trains) {
        return trains
                .stream()
                .map(executor::submit)
                .toList();
    }

    @Override
    public Boolean handleTrain(Train train) throws TrainException {
        StationPath path = stationPath.get((int)(Math.random() * stationPath.size()));
        train.setState(new ServiceState(train, path, station));
        return train.getState().process();
    }

    @Override
    public void shutdown() {
        executor.shutdown();
    }
}
