package dao;

import beans.Event;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Dao class to manage all database interactions corresponding to Events
 * */
public interface EventsDao {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    List<Event> getEventsData();

}
