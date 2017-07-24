package services;

import beans.Event;
import dao.EventsDao;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * This class provides the concrete implementation of EventsFinderService
 */
public class EventsFinderServiceImpl implements EventsFinderService {

    public EventsFinderServiceImpl(EventsDao eventsDao){
        this.eventsDao = eventsDao;
    }

    private EventsDao eventsDao;

    @Override
    public Map<String, LocalDateTime> findAllLatestEvents() {
        Map<String, LocalDateTime> latestUniqueEventsUnsorted;
        Map<String, LocalDateTime> latestUniqueEventsSorted;

        // get Events Data
        List<Event> events = eventsDao.getEventsData();
        events = (events == null)? new ArrayList<Event>(): events;

        // process events data and find latest unique events
        latestUniqueEventsUnsorted = events.stream().sorted(Comparator.comparing(Event::getEventName).reversed()).
                collect(
                groupingBy(Event::getEventName,
                        mapping(Event::getEventTime,
                                collectingAndThen(maxBy(LocalDateTime::compareTo),
                                        Optional::get))));

        // Sort the map
        latestUniqueEventsSorted = latestUniqueEventsUnsorted.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return latestUniqueEventsSorted;
    }

}
