package renathyu0015.proyectojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private void ingresar() {
        String correo = txtCorreo.getText().trim();
        String clave = txtClave.getText();

        if (correo.isEmpty() || clave.isEmpty()) {
            lblMensaje.setText("Complete todos los campos.");
            return;
        }

        try {
            // Convertimos la contraseña escrita a MD5 antes de compararla
            String claveHash = HashUtil.md5(clave);
            

            Usuario usuario = usuarioDAO.autenticar(correo, claveHash);

            if (usuario == null) {
                lblMensaje.setText("Correo o contraseña incorrectos.");
                txtClave.clear();
                return;
            }

            SesionUsuario.iniciar(usuario);   // guardamos quién entró
            App.setRoot("home");              // navegamos a Home

        } catch (Exception e) {
            lblMensaje.setText("No se pudo conectar con la base de datos.");
            e.printStackTrace();
        }
    }
}