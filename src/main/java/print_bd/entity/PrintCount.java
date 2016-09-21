package print_bd.entity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by sereo_000 on 16.09.2016.
 */
@Entity
public class PrintCount {
    private Integer count;
    private Double hoursInterval;
    private Date interval;

    protected PrintCount() {
    }

    public PrintCount(Integer count, Double hoursInterval) {
        this.count = count;
        this.hoursInterval = hoursInterval;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getHoursInterval() {
        return hoursInterval;
    }

    public void setHoursInterval(Double hoursInterval) {
        this.hoursInterval = hoursInterval;
    }

    public Date getInterval() {
        return interval;
    }

    public void setInterval(Date interval) {
        this.interval = interval;
    }
}
