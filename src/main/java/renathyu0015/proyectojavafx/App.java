package renathyu0015.proyectojavafx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import renathyu0015.proyectojavafx.util.HibernateUtil;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 480, 360);
        stage.setTitle("Sistema de acceso");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // La "/" al inicio busca el FXML desde la raíz de resources,
        // sin importar cómo se llame el paquete del proyecto
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void stop() {
        HibernateUtil.cerrar();   // libera las conexiones al salir
    }

    public static void main(String[] args) {
        launch();
    }
}