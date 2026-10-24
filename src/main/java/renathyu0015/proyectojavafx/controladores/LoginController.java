package renathyu0015.proyectojavafx.controladores;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import renathyu0015.proyectojavafx.App;
import renathyu0015.proyectojavafx.dao.UsuarioDAO;
import renathyu0015.proyectojavafx.entidades.Usuario;
import renathyu0015.proyectojavafx.util.HashUtil;
import renathyu0015.proyectojavafx.util.SesionUsuario;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtClave;
    @FXML private Label lblMensaje;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void iniciar() {
        lblMensaje.setText("");
    }

    @FXML
    private void ingresar() {
        String correo = txtCorreo.getText().trim();
        String clave = txtClave.getText();

        if (correo.isEmpty() || clave.isEmpty()) {
            mostrarError("Complete todos los campos.");
            return;
        }

        try {
            // La base de datos almacena las contraseñas mediante MD5.
            Usuario usuario = usuarioDAO.autenticar(correo, HashUtil.md5(clave));

            if (usuario == null) {
                mostrarError("Correo o contraseña incorrectos.");
                txtClave.clear();
                return;
            }

            SesionUsuario.iniciar(usuario);
            App.setRoot("home");
        } catch (Exception e) {
            mostrarError("No se pudo conectar con la base de datos.");
            e.printStackTrace();
        }
    }

    @FXML
    private void irARegistro() throws IOException {
        App.setRoot("registro");
        Stage stage = (Stage) lblMensaje.getScene().getWindow();
        stage.setHeight(560);
    }

    private void mostrarError(String mensaje) {
        lblMensaje.setStyle("-fx-text-fill: red;");
        lblMensaje.setText(mensaje);
    }
}
