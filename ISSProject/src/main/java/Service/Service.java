package Service;

import models.*;
import repositories.RepoDeliveryDay;
import repositories.RepoPackage;
import repositories.RepoUser;
import repositories.RepoWorkDay;

import java.util.Date;
import java.util.List;

public class Service {
    RepoUser repoUser;
    RepoWorkDay repoWorkDay;
    RepoPackage repoPackage;
    RepoDeliveryDay repoDeliveryDay;

    public Service(RepoUser repoUser, RepoWorkDay repoWorkDay, RepoPackage repoPackage, RepoDeliveryDay repoDeliveryDay) {
        this.repoUser = repoUser;
        this.repoWorkDay = repoWorkDay;
        this.repoPackage = repoPackage;
        this.repoDeliveryDay = repoDeliveryDay;
    }

    public MyUser login(String username, String password){
        MyUser myUser = repoUser.getUserByUsername(username).get();
        if (myUser.getParola().equals(password)){
            return myUser;
        } else {
            return null;
        }
    }

    public Curier markPresent(Curier user){
        return repoUser.markPresent(user);
    }

    public List<Pachet> getAllPackages(){
        return repoPackage.getAllPackagesFromDeposit();
    }

    public void addPackageForTodayDelivery(Sef sef, Pachet pachet){
        ZiDeLucru x = new ZiDeLucru(pachet, sef, new Date());
        repoWorkDay.addPackageWorkDay(x);
        repoPackage.modifyStatus(pachet, StatusPachet.livrareAziDisponibil);
    }

    public List<Pachet> getAllAvaiblePackagesForCurrentDay(){
        return repoPackage.getAllAvailablePackagesForCurrentDay();
    }

    public void choosePackageForDelivery(Curier curier, Pachet pachet){
        ZiDeLivrare x = new ZiDeLivrare(new Date(), pachet, curier);
        repoDeliveryDay.addPackageDeliveryDay(x);
        repoPackage.modifyStatus(pachet, StatusPachet.livrareAziIndisponibil);
    }

}
