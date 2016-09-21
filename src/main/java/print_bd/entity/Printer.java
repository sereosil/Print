package print_bd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sereo_000 on 16.09.2016.
 */
@Entity
public class Printer {
    @Id
    @GeneratedValue
    private Integer id;
    private String printerName;

    public Printer(String printerName) {
        this.printerName = printerName;
    }

    protected Printer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }
}
