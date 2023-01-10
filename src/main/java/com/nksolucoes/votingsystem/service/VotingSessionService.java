package com.nksolucoes.votingsystem.service;

import com.nksolucoes.votingsystem.dto.VotingSessionDTO;
import com.nksolucoes.votingsystem.dto.VotingSessionInfoDTO;
import com.nksolucoes.votingsystem.entity.VotingSession;
import com.nksolucoes.votingsystem.enums.MessagesEnum;
import com.nksolucoes.votingsystem.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.*;

import static com.nksolucoes.votingsystem.enums.MessagesEnum.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class VotingSessionService {
    private final Logger LOGGER = LoggerFactory.getLogger(VotingSessionService.class);
    private final VotingSessionRepository repository;

    public VotingSessionDTO createVotingSession(VotingSessionDTO votingSessionDTO) {
         VotingSession votingSession = repository.save(VotingSession.builder()
                                                  .name(votingSessionDTO.getName())
                                                  .createDate(LocalDateTime.now())
                                                  .timeOfDuration(votingSessionDTO.getTimeOfDuration())
                                                  .sessionOpened(votingSessionDTO.isSessionOpened())
                                                  .schedule(votingSessionDTO.getSchedule())
                                                  .build());
         return convertToDto(votingSession);
    }


    public VotingSessionInfoDTO openVotingSession(Long votingSessionId) {
        Optional<VotingSession> votingSession = repository.findById(votingSessionId);

        if (votingSession.isPresent()) {
            if (votingSession.get().isSessionOpened()) {
                return VotingSessionInfoDTO.builder()
                        .message(MessagesEnum.OPENED_VOTING_SESSION.getMessageEnum() + " { Session Id: " + votingSession.get().getId()
                                                                                     + ", Session Name: " + votingSession.get().getName()
                                                                                     + ", Schedule : [  Id: " + votingSession.get().getSchedule().getId()
                                                                                     + ", Name: " + votingSession.get().getSchedule().getName() + " ] } ")
                        .timeOfDuration(votingSession.get().getTimeOfDuration())
                        .build();
            } else {
                int interval = setInterval(votingSession.get().getTimeOfDuration());
                startTaskOfOpenAVotingSession(interval, votingSession.get());
                return VotingSessionInfoDTO.builder()
                        .message(MessagesEnum.SUCCESS_VOTING_SESSION_OPEN.getMessageEnum() + " { Session Id: " + votingSession.get().getId()
                                                                                           + ", Session Name: " + votingSession.get().getName()
                                                                                           + ", Schedule : [  Id: " + votingSession.get().getSchedule().getId()
                                                                                           + ", Name: " + votingSession.get().getSchedule().getName() + " ] } ")
                        .timeOfDuration(votingSession.get().getTimeOfDuration())
                        .build();
            }
        }

        return VotingSessionInfoDTO.builder()
                .message(MessagesEnum.NOT_FOUND_VOTING_SESSION.getMessageEnum())
                .build();
    }

    private void updateVotingSession(VotingSession votingSession, boolean votingSessionIsOpened) {
        try {
            repository.save(VotingSession.builder()
                    .id(votingSession.getId())
                    .name(votingSession.getName())
                    .createDate(LocalDateTime.now())
                    .timeOfDuration(votingSession.getTimeOfDuration())
                    .sessionOpened( votingSessionIsOpened )
                    .schedule(votingSession.getSchedule())
                    .build());
        } catch (Exception e) {
            LOGGER.error(MessagesEnum.ERROR_WITH_UPDATE_VOTING_SESSION.getMessageEnum() + "Id: " + votingSession.getId(), e);
        }

    }
    public void startTaskOfOpenAVotingSession(Integer interval, VotingSession votingSession) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit((Callable<Object>) () -> {
            LOGGER.info( MessagesEnum.OPENED_VOTING_SESSION.getMessageEnum() + " { Session Id: " + votingSession.getId()
                                                                             + ", Session Name: " + votingSession.getName()
                                                                             + ", Schedule : [  Id: " + votingSession.getSchedule().getId()
                                                                             + ", Name: " + votingSession.getSchedule().getName() + " ] } ");
            updateVotingSession(votingSession, Boolean.TRUE);
            TimeUnit.MILLISECONDS.sleep(interval);
            updateVotingSession(votingSession, Boolean.FALSE);
            LOGGER.info( MessagesEnum.CLOSED_VOTING_SESSION.getMessageEnum() + " { Session Id: " + votingSession.getId()
                                                                             + ", Session Name: " + votingSession.getName()
                                                                             + ", Schedule : [  Id: " + votingSession.getSchedule().getId()
                                                                             + ", Name: " + votingSession.getSchedule().getName() + " ] } ");
            return CLOSED_VOTING_SESSION.getMessageEnum();
        });
    }

    private Integer setInterval(Integer timeOfDuration) {
        int interval = 0;
        int DEFAULT_INTERVAL = 60;
        if (timeOfDuration == null)
            interval = DEFAULT_INTERVAL;
        return interval = timeOfDuration * 1000;
    }


    public VotingSessionDTO convertToDto(VotingSession votingSession) {
        return VotingSessionDTO.builder()
                .id(votingSession.getId())
                .name(votingSession.getName())
                .timeOfDuration(votingSession.getTimeOfDuration())
                .schedule(votingSession.getSchedule())
                .build();
    }
}
