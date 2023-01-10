package com.nksolucoes.votingsystem.repository;

import com.nksolucoes.votingsystem.entity.Schedule;
import com.nksolucoes.votingsystem.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {

}
