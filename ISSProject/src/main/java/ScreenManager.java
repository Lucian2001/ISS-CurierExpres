import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class ScreenManager {
    private HashMap<String, URL> screenMap = new HashMap<>();
    private Scene main = null;
    private static ScreenController instance = new ScreenController();
    private FXMLLoader loader;
    private ApplicationContext context = null;

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private Stage mainStage = null;
    private ScreenController() {

    }

    public Scene getMainScene(){
        return main;
    }

    ApplicationContext getContext(){
        if (context == null)
            context = new AnnotationConfigApplicationContext(ConfigProject.class);
        return context;
    }


    public void addScreen(String name, URL location)  {

        screenMap.put(name, location);

    }


    public  void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name, Class<?> controllerClass) throws IOException {
        loader = new FXMLLoader();
        loader.setLocation(screenMap.get(name));
        loader.setController(getContext().getBean(controllerClass));
        Pane pane = loader.load();
        if(main == null){
            main = new Scene(pane, 1200, 900);
        } else {
            main.setRoot(pane);
        }
    }

    public Controller getCurrentController(){
        return loader.getController();
    }

    public Scene getCurrentScene(){
        return main;
    }


    public static ScreenController get_instance(){
        return instance;
    }
}
