package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pachet {
    private Long id;

    @OneToMany(mappedBy = "pachet")
    public Set<ZiDeLucru> getZiDeLucru() {
        return ziDeLucru;
    }

    public void setZiDeLucru(Set<ZiDeLucru> ziDeLucru) {
        this.ziDeLucru = ziDeLucru;
    }


    private Set<ZiDeLucru> ziDeLucru = new HashSet<>();

    public Pachet() {

    }

    @Id
    @GeneratedValue
    @Column(name = "PACKAGE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getDestinatar() {
        return destinatar;
    }

    public void setDestinatar(String destinatar) {
        this.destinatar = destinatar;
    }


    @Enumerated(EnumType.STRING)
    public StatusPachet getStatusPachet() {
        return statusPachet;
    }

    public void setStatusPachet(StatusPachet statusPachet) {
        this.statusPachet = statusPachet;
    }

    String adresa, destinatar;
    StatusPachet statusPachet;

    public Pachet(String adresa, String destinatar) {
        this.adresa = adresa;
        this.destinatar = destinatar;
        this.statusPachet = StatusPachet.inDepozit;
    }

    @Override
    public String toString() {
        return "Pachet{" +
                "id=" + id +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
