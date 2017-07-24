package beans;

import java.time.LocalDateTime;

/**
 * Java Bean to hold the Event data
 */

public class Event {

    public Event(long eventId, String eventName, LocalDateTime eventTime) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventTime = eventTime;
    }

    private long eventId;

    private String eventName;

    private LocalDateTime eventTime;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getEventTime() {return eventTime;}

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                ", eventName='" + eventName + '\'' +
                ", eventTime=" + eventTime +
                '}';
    }
}
