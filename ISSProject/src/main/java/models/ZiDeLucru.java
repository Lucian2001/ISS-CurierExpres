package models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ZiDeLucru {
    private Long id;
    private Date data;
    private Pachet pachet;
    private Sef sef;

    @Column(name = "REGISTERED_DATE")
    @Temporal(TemporalType.DATE)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PACKAGE_ID")
    public Pachet getPachet() {
        return pachet;
    }

    public void setPachet(Pachet pachet) {
        this.pachet = pachet;
    }

    public ZiDeLucru() {
    }

    public ZiDeLucru(Pachet pachet, Sef sef, Date data) {
        this.pachet = pachet;
        this.sef = sef;
        this.data = data;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    public Sef getSef() {
        return sef;
    }

    public void setSef(Sef sef) {
        this.sef = sef;
    }

    @Id
    @GeneratedValue
    @Column(name = "WORKDAY_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
