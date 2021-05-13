package models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ZiDeLivrare {
    private Long id;
    private Date data;
    private Pachet pachet;
    private Curier curier;

    public ZiDeLivrare() {
    }

    public ZiDeLivrare(Date data, Pachet pachet, Curier curier) {
        this.data = data;
        this.pachet = pachet;
        this.curier = curier;
    }

    @Id
    @GeneratedValue
    @Column(name = "DELIVERYDAY_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    public Curier getCurier() {
        return curier;
    }

    public void setCurier(Curier curier) {
        this.curier = curier;
    }
}
