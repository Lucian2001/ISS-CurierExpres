import Service.Service;
import controllers.BossMainController;
import controllers.LoginController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigProject {


    @Bean
    Service service(){
        return new Service();
    }


    @Bean
    BossMainController mainController() throws Exception{
        return new BossMainController(service());
    }

    @Bean
    LoginController loginController() throws Exception{
        return new LoginController(service());
    }

}
