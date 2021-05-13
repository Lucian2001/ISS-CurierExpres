package models;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Curier")
public class Curier extends MyUser {


    StatusAngajat status;

    private Set<ZiDeLivrare> ziDeLivrare = new HashSet<>();


    @OneToMany(mappedBy = "curier")
    public Set<ZiDeLivrare> getZiDeLivrare() {
        return ziDeLivrare;
    }

    public void setZiDeLivrare(Set<ZiDeLivrare> ziDeLivrare) {
        this.ziDeLivrare = ziDeLivrare;
    }



    public Curier(String nume, String parola) {
        super(nume, parola);
        this.status = StatusAngajat.neconectat;
    }

    @Enumerated(EnumType.STRING)
    public StatusAngajat getStatus() {
        return status;
    }

    public void setStatus(StatusAngajat status) {
        this.status = status;
    }

    public Curier() {

    }
}
