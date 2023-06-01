package com.schedule.service.impl;

import com.schedule.domain.MeetingTime;
import com.schedule.repo.MeetingTimeRepo;
import com.schedule.service.MeetingTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingTimeServiceImpl implements MeetingTimeService {

    private final MeetingTimeRepo meetingTimeRepo;

    @Override
    public List<MeetingTime> findAll() {

        return meetingTimeRepo.findAll();
    }
}
