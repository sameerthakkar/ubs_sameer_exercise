package tdd.dao;

import beans.Event;
import dao.EventsDao;
import dao.EventsDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test Event Dao method should return the list of events with their occurance time
 */
public class EventsDaoImplTest {

    private EventsDao target;

    @Before
    public void setUp() throws Exception {
        target = new EventsDaoImpl();
    }

    @Test
    public void testGetEventsData() throws Exception {
        List<Event> eventList = target.getEventsData();
        assertNotNull(eventList);
        assertTrue(eventList.size() > 0);
    }
}