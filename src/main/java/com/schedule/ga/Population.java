package com.schedule.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Population {
    private List<Schedule> schedules;

    public Population(int size, DataForSchedule dataForSchedule) {
        schedules = new ArrayList<>(size);
        IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(dataForSchedule).initialize()));
    }

    public List<Schedule> getSchedules() {
        return this.schedules;
    }

    public Population sortByFitness() {
        schedules.sort((schedule1, schedule2) -> {
            int returnValue = 0;

            if (schedule1.getFitness() > schedule2.getFitness()) returnValue = -1;
            else if (schedule1.getFitness() < schedule2.getFitness()) returnValue = 1;
            return returnValue;
        });

        return this;
    }
}
