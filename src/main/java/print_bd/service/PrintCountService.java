package print_bd.service;

import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import print_bd.repository.PrintCountRepository;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sereo_000 on 06.10.2016.
 */
@SpringComponent
public class PrintCountService {
    private final PrintCountRepository printCountRepository;
    private static final long HOURSINMILLIS=60 * 60 * 1000;
    private static final long ONEDAYINMILLIS=24 * 60 * 60 * 1000;
    @Autowired
    public PrintCountService(PrintCountRepository printCountRepository) {
        this.printCountRepository = printCountRepository;
    }
    public boolean checkPrintingPermission(Date timeOfLastPrint,Date timeOfPrint,Integer permissionedPrints,long hoursInterval,Integer printCounter){
        if((timeOfPrint.getTime()-timeOfLastPrint.getTime())<=(hoursInterval*HOURSINMILLIS)){
            if(permissionedPrints<printCounter){
                return true;
            }
        }
        return false;
    }
    public Integer setPrintCounterToZeroWhenNewDayArrives(Date timeOfLastPrint,Integer printCounter){
        Date currentTime;
        currentTime=new Date(System.currentTimeMillis());
        if((timeOfLastPrint.getTime()-currentTime.getTime())>=ONEDAYINMILLIS){
            printCounter=0;
            return printCounter;
        }
        return printCounter;
    }
    public Integer setPrintCounterToZeroWhenHourIntervalEnds(Date timeOfLastPrint,Integer printCounter,long hoursInterval){
        Date currentTime;
        currentTime=new Date(System.currentTimeMillis());
        if((timeOfLastPrint.getTime()-currentTime.getTime())>=(hoursInterval*HOURSINMILLIS)){
            printCounter=0;
            return printCounter;
        }
        return printCounter;
    }
    public Date newTimeOfLastPrint(Date timeOfLastPrint,Date timeOfPrint){
        timeOfLastPrint=timeOfPrint;
        return timeOfLastPrint;
    }
    public Integer setPrintCounterToZeroWhenNewCalendarDayStarts(Date timeOfLastPrint,Integer printCounter){
        Date currentTime;
        currentTime=new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(timeOfLastPrint);
        int dayOfLastPrint = cal.get(Calendar.DAY_OF_MONTH);
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(currentTime);
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        if(dayOfLastPrint>currentDay){
            printCounter=0;
        }
        return printCounter;
    }
}
