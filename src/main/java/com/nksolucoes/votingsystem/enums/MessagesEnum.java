package com.nksolucoes.votingsystem.enums;

public enum MessagesEnum {
    VOTING_SESSION_MESSAGES_THREAD("Voting Session Thread"),
    OPENED_VOTING_SESSION("Voting Session is Opened"),
    SUCCESS_VOTING_SESSION_OPEN("Successfully opened voting session"),
    CLOSED_VOTING_SESSION("Voting Session Closed"),
    NOT_FOUND_VOTING_SESSION("Voting Session Not Found"),
    ERROR_WITH_UPDATE_VOTING_SESSION("Error when trying to update Voting Session");

    private String message;

    MessagesEnum(String message) {
        this.message = message;
    }

    public String getMessageEnum() {
        return message;
    }
}
