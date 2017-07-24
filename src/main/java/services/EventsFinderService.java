package services;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * This interface is used to find the latest occurrence of all events.
 */

public interface EventsFinderService {

    /**
     * @return Map of unique events with their latest occurance evemtTime
     */
    Map<String, LocalDateTime> findAllLatestEvents();
}
