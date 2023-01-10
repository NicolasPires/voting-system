package com.nksolucoes.votingsystem.repository;

import com.nksolucoes.votingsystem.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
