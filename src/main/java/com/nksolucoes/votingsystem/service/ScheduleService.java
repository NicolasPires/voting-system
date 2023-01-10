package com.nksolucoes.votingsystem.service;

import com.nksolucoes.votingsystem.dto.ScheduleDTO;
import com.nksolucoes.votingsystem.entity.Schedule;
import com.nksolucoes.votingsystem.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
         Schedule schedule = scheduleRepository.save(Schedule.builder()
                                                             .name(scheduleDTO.getName())
                                                             .description(scheduleDTO.getDescription())
                                                             .build());
         return convertToDto(schedule);
    }
    public ScheduleDTO convertToDto(Schedule schedule) {
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .description(schedule.getDescription())
                .build();
    }
}
