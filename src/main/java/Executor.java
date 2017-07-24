import dao.EventsDao;
import dao.EventsDaoImpl;
import services.EventsFinderService;
import services.EventsFinderServiceImpl;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Run the Application
 */
public class Executor {
    public static void main (String args []){
        try{
            EventsFinderService service = new EventsFinderServiceImpl(new EventsDaoImpl());
            Map<String, LocalDateTime> events = service.findAllLatestEvents();
            events.forEach((eventName, eventTime)-> System.out.println(eventName + " " + eventTime.format(EventsDao.formatter)));
        } catch(Exception e) {
            System.out.println("Exception occurred while running the Application");
            System.out.println("e = " + e.getMessage());
            e.printStackTrace();
        }
    }
}
