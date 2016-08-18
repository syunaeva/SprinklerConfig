package sprinklerconfig.ulduzsoft.com.sprinklerconfig.model;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by syunaeva on 8/6/16.
 */

public class Program
    {
        private long id;
        // Program name
        public String name;
        // Which days it runs; 1 (Sun) 2 (Mon) 3 (Tue) 4 (Wed) 5 (Thurs) 6 (Fri) 7 (Sat)
        // So "124" means Sun,Mon,Wed. If null, this means all days
        public String days = null;

        // Interval between runs starting from 1st day, i.e. if 2, it will run every 2nd day
        public int dayInterval = 1;

        // Watering start time(24hr time)
        public int startHour = 0;
        public int startMinute = 0;

        // +
        public int  zoneDelaySec = 5;

        // When the program last ran; null if never
        public Calendar lastRan = null;

        // Zone entries
        // zone_id: zone_id2: zone_id3 and etc
        public String zones;

        public void setRan(Calendar in)
        {
            this.lastRan = Calendar.getInstance();
        }

        public void setId(long id){
            this.id = id;
        }

        public long getId(){
            return this.id;
        }

        // Returns start time as today's calendar time in current time zone
        public Calendar getStartTime()
        {
            Calendar cal = Calendar.getInstance();

            cal.set( Calendar.HOUR_OF_DAY, startHour );
            cal.set( Calendar.MINUTE, startMinute );
            cal.set( Calendar.SECOND, 0 );
            return cal;
        }
    }
