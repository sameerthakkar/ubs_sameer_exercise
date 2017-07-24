package tdd.services;

import beans.Event;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import dao.EventsDao;
import services.EventsFinderService;
import services.EventsFinderServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * EventsFinderServiceImpl TestClass
 */
public class EventsFinderServiceTest {

    @Mock
    private EventsDao dao;

    private EventsFinderService target;

    private List<Event> events;

    @Before
    public void setUp() throws Exception {

        // initialize mocks
        MockitoAnnotations.initMocks(this);

        // initialize target
        target = new EventsFinderServiceImpl(dao);

        // Prepare dummy data
        events = new ArrayList<Event>();

        events.add(new Event(5L, "EventA",
                LocalDateTime.parse("07/19/2017 23:00:00", EventsDao.formatter)));
        events.add(new Event(4L, "EventB",
                LocalDateTime.parse("07/19/2017 23:00:00", EventsDao.formatter)));
        events.add(new Event(3L, "EventA",
                LocalDateTime.parse("07/19/2017 21:00:00", EventsDao.formatter)));
        events.add(new Event(2L, "EventA",
                LocalDateTime.parse("07/18/2017 21:00:00", EventsDao.formatter)));
        events.add(new Event(1L, "EventB",
                LocalDateTime.parse("07/18/2017 21:00:00", EventsDao.formatter)));

        // Method stubbing
        when(dao.getEventsData()).thenReturn(events);
    }


    @Test
    public void testFindAllLatestEventsWithNullList() {
        when(dao.getEventsData()).thenReturn(null);
        Map<String, LocalDateTime> allLatestEvents = target.findAllLatestEvents();
        assertNotNull(allLatestEvents);
        assertTrue(allLatestEvents.size() == 0);
        verify(dao, times(1)).getEventsData();
    }

    @Test
    public void testFindAllLatestEventsWithEmptyNotNullList() {
        when(dao.getEventsData()).thenReturn(new ArrayList<Event>());
        Map<String, LocalDateTime> allLatestEvents = target.findAllLatestEvents();
        assertNotNull(allLatestEvents);
        assertTrue(allLatestEvents.size() == 0);
        verify(dao, times(1)).getEventsData();
    }

    @Test
    public void testFetchLatestEvents() {
        Map<String, LocalDateTime> allLatestEvents = target.findAllLatestEvents();
        assertNotNull(allLatestEvents);
        assertTrue(allLatestEvents.size() == 2);

        assertTrue(allLatestEvents.get("EventA").equals(LocalDateTime.parse("07/19/2017 23:00:00", EventsDao.formatter)));
        assertTrue(allLatestEvents.get("EventB").equals(LocalDateTime.parse("07/19/2017 23:00:00", EventsDao.formatter)));

        verify(dao, times(1)).getEventsData();
    }


    @Test
    public void testFetchLatestEventsWithNewEventA() {
        events.add(new Event(2L, "EventA",
                LocalDateTime.parse("07/24/2017 08:00:00", EventsDao.formatter)));

        Map<String, LocalDateTime> allLatestEvents = target.findAllLatestEvents();
        assertNotNull(allLatestEvents);
        assertTrue(allLatestEvents.size() == 2);

        assertTrue(allLatestEvents.get("EventA").equals(LocalDateTime.parse("07/24/2017 08:00:00", EventsDao.formatter)));

        verify(dao, times(1)).getEventsData();
    }


    @Test
    public void testFetchLatestEventsWithNewEventB() {
        events.add(new Event(2L, "EventB",
                LocalDateTime.parse("07/24/2017 08:25:03", EventsDao.formatter)));

        Map<String, LocalDateTime> allLatestEvents = target.findAllLatestEvents();
        assertNotNull(allLatestEvents);
        assertTrue(allLatestEvents.size() == 2);

        assertTrue(allLatestEvents.get("EventB").equals(LocalDateTime.parse("07/24/2017 08:25:03", EventsDao.formatter)));

        verify(dao, times(1)).getEventsData();
    }

    @Test
    public void testFetchLatestEventsWithNewEventsAandB() {
        events.add(new Event(2L, "EventA",
                LocalDateTime.parse("07/24/2017 08:00:00", EventsDao.formatter)));
        events.add(new Event(2L, "EventB",
                LocalDateTime.parse("07/24/2017 08:25:03", EventsDao.formatter)));

        Map<String, LocalDateTime> allLatestEvents = target.findAllLatestEvents();
        assertNotNull(allLatestEvents);
        assertTrue(allLatestEvents.size() == 2);

        assertTrue(allLatestEvents.get("EventA").equals(LocalDateTime.parse("07/24/2017 08:00:00", EventsDao.formatter)));
        assertTrue(allLatestEvents.get("EventB").equals(LocalDateTime.parse("07/24/2017 08:25:03", EventsDao.formatter)));

        verify(dao, times(1)).getEventsData();
    }


}