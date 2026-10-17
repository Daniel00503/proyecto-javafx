package renathyu0015.proyectojavafx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import renathyu0015.proyectojavafx.entidades.Usuario;
import renathyu0015.proyectojavafx.util.SesionUsuario;

public class HomeController {

    @FXML private Label lblBienvenida;

    @FXML
    private void initialize() {
        Usuario usuario = SesionUsuario.getUsuarioActual();
        if (usuario != null) {
            lblBienvenida.setText("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellido());
        }
    }

    @FXML
    private void cerrarSesion() throws IOException {
        SesionUsuario.cerrar();     // eliminamos la sesión
        App.setRoot("login");       // volvemos al login
    }
}