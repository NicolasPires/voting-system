package com.nksolucoes.votingsystem.resource;

import com.nksolucoes.votingsystem.dto.VotingSessionDTO;
import com.nksolucoes.votingsystem.dto.VotingSessionInfoDTO;
import com.nksolucoes.votingsystem.enums.MessagesEnum;
import com.nksolucoes.votingsystem.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/votingSession")
@Validated
public class VotingSessionResource {

    private final VotingSessionService votingSessionService;

    @PostMapping("/create")
    public ResponseEntity<?> createVotingSession(@Valid @RequestBody VotingSessionDTO votingSessionDTO) {
        return ResponseEntity.ok(votingSessionService.createVotingSession(votingSessionDTO));
    }
    @PostMapping("/openVotingSession")
    public ResponseEntity<?> openVotingSession(@Valid @RequestParam Long votingSessionId) {
        VotingSessionInfoDTO votingSessionInfoDTO = votingSessionService.openVotingSession(votingSessionId);
        if (votingSessionInfoDTO.getMessage().equals(MessagesEnum.NOT_FOUND_VOTING_SESSION.getMessageEnum()))
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(votingSessionInfoDTO);
    }

}

