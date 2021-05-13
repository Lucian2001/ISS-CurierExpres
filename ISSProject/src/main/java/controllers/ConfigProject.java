package controllers;

import Service.Service;
import controllers.BossMainController;
import controllers.LoginController;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositories.RepoDeliveryDay;
import repositories.RepoPackage;
import repositories.RepoUser;
import repositories.RepoWorkDay;


@Configuration
public class ConfigProject {

    @Bean
    SessionFactory initializeSession() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
        return sessionFactory;
    }

    @Bean
    RepoUser repoUser(){
        return new RepoUser(initializeSession());
    }

    @Bean
    RepoPackage repoPackage(){
        return new RepoPackage(initializeSession());
    }

    @Bean
    RepoDeliveryDay repoDeliveryDay(){
        return new RepoDeliveryDay(initializeSession());
    }

    @Bean
    RepoWorkDay repoWorkDay(){
        return new RepoWorkDay(initializeSession());
    }


    @Bean
    Service service(){
        return new Service(repoUser(), repoWorkDay(), repoPackage(), repoDeliveryDay());
    }


    @Bean
    BossMainController bossMainController() throws Exception{
        return new BossMainController(service());
    }

    @Bean
    LoginController loginController() throws Exception{
        return new LoginController(service());
    }

    @Bean
    EmployeeWelcomePageController employeeWelcomePageControllerController() throws Exception{
        return new EmployeeWelcomePageController(service());
    }

    @Bean
    EmployeeMainController employeeMainController() throws Exception{
        return new EmployeeMainController(service());
    }

}
