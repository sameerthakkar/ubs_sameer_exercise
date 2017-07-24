package dao;

import beans.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao class implementation to manage all database interactions corresponding to Events
 */
public class EventsDaoImpl implements EventsDao {

    @Override
    public List<Event> getEventsData() {
        List<Event> events = new ArrayList<Event>();
        events.add(new Event(5L, "EventA", LocalDateTime.parse("07/19/2017 23:00:00", formatter)));
        events.add(new Event(4L, "EventB", LocalDateTime.parse("07/19/2017 23:00:00", formatter)));
        events.add(new Event(3L, "EventA", LocalDateTime.parse("07/19/2017 21:00:00", formatter)));
        events.add(new Event(2L, "EventA", LocalDateTime.parse("07/18/2017 21:00:00", formatter)));
        events.add(new Event(1L, "EventB", LocalDateTime.parse("07/18/2017 21:00:00", formatter)));
        return events;
    }
}
