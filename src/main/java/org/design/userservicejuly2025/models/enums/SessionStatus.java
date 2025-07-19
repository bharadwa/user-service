package org.design.userservicejuly2025.models.enums;

public enum SessionStatus {
    active,inactive,expired,completed,failed;
    public static SessionStatus fromString(String status) {
        for (SessionStatus sessionStatus : SessionStatus.values()) {
            if (sessionStatus.name().equalsIgnoreCase(status)) {
                return sessionStatus;
            }
        }
        throw new IllegalArgumentException("Unknown session status: " + status);
    }
}
