package print_bd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by sereo_000 on 16.09.2016.
 */
@Entity
public class PrintCount {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer permissiblePrints;
    private long hoursInterval;
    private Date timeOfLastPrint;
    private Date timeOfPrint;
    private Integer printCounter=0;
    protected PrintCount() {
    }

    public PrintCount(Integer permissiblePrints, long hoursInterval) {
        this.permissiblePrints = permissiblePrints;
        this.hoursInterval = hoursInterval;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeOfPrint() {
        return timeOfPrint;
    }

    public void setTimeOfPrint(Date timeOfPrint) {
        this.timeOfPrint = timeOfPrint;
    }

    public Integer getPrintCounter() {
        return printCounter;
    }

    public void setPrintCounter(Integer printCounter) {
        this.printCounter = printCounter;
    }

    public Integer getPermissiblePrints() {
        return permissiblePrints;
    }

    public void setPermissiblePrints(Integer permissiblePrints) {
        this.permissiblePrints = permissiblePrints;
    }

    public long getHoursInterval() {
        return hoursInterval;
    }

    public void setHoursInterval(long hoursInterval) {
        this.hoursInterval = hoursInterval;
    }

    public Date getTimeOfLastPrint() {
        return timeOfLastPrint;
    }

    public void setTimeOfLastPrint(Date timeOfLastPrint) {
        this.timeOfLastPrint = timeOfLastPrint;
    }
}
