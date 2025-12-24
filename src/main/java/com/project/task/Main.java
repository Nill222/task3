package com.project.task;

import com.project.task.entity.Station;
import com.project.task.entity.StationPath;
import com.project.task.entity.Train;
import com.project.task.entity.WagonsState;
import com.project.task.service.RailwayManager;
import com.project.task.service.impl.RailwayManagerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Station station = new Station(10, 0);
        List<StationPath> paths = List.of(new StationPath(1),
                new StationPath(2));
        List<Train> trains = List.of(
                new Train("Train-A", 5, WagonsState.UNLOADING),
                new Train("Train-B", 3, WagonsState.LOADING),
                new Train("Train-C", 10, WagonsState.UNLOADING)
        );
        RailwayManager manager = RailwayManagerImpl.getInstance();
        manager.init(paths, station);
        var results = manager.processAll(trains);
        for (var f : results) {
            log.info("Result: {}", f.get());
        }
        manager.shutdown();
    }
}
