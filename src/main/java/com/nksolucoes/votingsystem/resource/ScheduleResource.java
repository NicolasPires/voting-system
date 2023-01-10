package com.nksolucoes.votingsystem.resource;

import com.nksolucoes.votingsystem.dto.ScheduleDTO;
import com.nksolucoes.votingsystem.entity.Schedule;
import com.nksolucoes.votingsystem.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
@Validated
public class ScheduleResource {

    private final ScheduleService scheduleService;

    @PostMapping("/create")
    public ScheduleDTO createSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        return  scheduleService.createSchedule(scheduleDTO);
    }

}
