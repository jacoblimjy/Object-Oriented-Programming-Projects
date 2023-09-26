import java.util.Comparator;

class EventComparator implements Comparator<Event> {

    public int compare(Event a, Event b) {
        int compareEventTime = Double.compare(a.getEventTime(), b.getEventTime());
        if (compareEventTime != 0) {
            return compareEventTime;
        } else {
            int aCustomerNum = a.getCustomer().getCustomerNum();
            int bCustomerNum = b.getCustomer().getCustomerNum();
            return aCustomerNum - bCustomerNum;
        }
    }
}
