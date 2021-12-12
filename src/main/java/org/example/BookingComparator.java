package org.example;

import java.util.Comparator;

public class BookingComparator implements Comparator<Booking>
{
    @Override
    public int compare(Booking o1, Booking o2) {
        if(o1.getBookingDateTime().isAfter(o2.getBookingDateTime())){
            return 1;
        }else if(o2.getBookingDateTime().isAfter(o1.getBookingDateTime())){
            return -1;
        }else{
            return 0;
        }
    }
}
