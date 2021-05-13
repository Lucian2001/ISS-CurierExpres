package models;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Sef")
public class Sef extends MyUser {

    @OneToMany(mappedBy = "sef")
    public Set<ZiDeLucru> getZiDeLucru() {
        return ziDeLucru;
    }

    public void setZiDeLucru(Set<ZiDeLucru> ziDeLucru) {
        this.ziDeLucru = ziDeLucru;
    }


    private Set<ZiDeLucru> ziDeLucru = new HashSet<>();


    public Sef(String nume, String parola) {
        super(nume, parola);
    }

    public Sef() {

    }


}
