package renathyu0015.proyectojavafx.controladores;

import java.io.IOException;
import renathyu0015.proyectojavafx.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import renathyu0015.proyectojavafx.dao.UsuarioDAO;
import renathyu0015.proyectojavafx.entidades.Usuario;
import renathyu0015.proyectojavafx.util.HashUtil;

public class RegisterController {

    // Los nombres deben ser IGUALES a los fx:id de registro.fxml
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtClave;
    @FXML private PasswordField txtConfirmarClave;
    @FXML private Label lblMensaje;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Se ejecuta al presionar "Registrarse" (onAction="#registrar")
    @FXML
    private void registrar() {
        // 1. Leer lo que escribió el usuario (trim quita espacios al inicio y al final)
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String correo = txtCorreo.getText().trim();
        String clave = txtClave.getText();
        String confirmar = txtConfirmarClave.getText();

        // 2. Validaciones que NO necesitan la base de datos
        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()
                || clave.isEmpty() || confirmar.isEmpty()) {
            mostrarError("Complete todos los campos.");
            return;
        }
        if (!correo.contains("@") || !correo.contains(".")) {
            mostrarError("Escriba un correo válido.");
            return;
        }
        if (clave.length() < 6) {
            mostrarError("La contraseña debe tener al menos 6 caracteres.");
            return;
        }
        if (!clave.equals(confirmar)) {
            mostrarError("Las contraseñas no coinciden.");
            txtConfirmarClave.clear();
            return;
        }

        try {
            // 3. Validación que SÍ necesita la base de datos (método de Néstor)
            if (usuarioDAO.existeCorreo(correo)) {
                mostrarError("Ese correo ya está registrado.");
                return;
            }

            // 4. Armar el usuario
            Usuario nuevo = new Usuario();
            nuevo.setNombre(nombre);
            nuevo.setApellido(apellido);
            nuevo.setCorreo(correo);
            nuevo.setClave(HashUtil.md5(clave)); // se guarda en MD5, como en el login
            nuevo.setRoll("USUARIO");            // columna obligatoria en la tabla
            nuevo.setEstado("ACTIVO");           // obligatoria; el login exige ACTIVO

            // 5. Guardarlo (método de Néstor)
            if (usuarioDAO.registrar(nuevo)) {
                mostrarExito("¡Registro exitoso! Ya puede iniciar sesión.");
                limpiarCampos();
            } else {
                mostrarError("No se pudo registrar. Intente de nuevo.");
            }

        } catch (Exception e) {
            mostrarError("No se pudo conectar con la base de datos.");
            e.printStackTrace();
        }
    }

    // Se ejecuta al hacer clic en "¿Ya tienes cuenta? Inicia sesión"
    @FXML
    private void irALogin() throws IOException {
        Stage stage = (Stage) lblMensaje.getScene().getWindow();
        App.setRoot("login");
        stage.setHeight(400);   // regresamos la ventana al tamaño del login
    }

    // ---------- Métodos de apoyo ----------

    private void mostrarError(String texto) {
        lblMensaje.setStyle("-fx-text-fill: red;");
        lblMensaje.setText(texto);
    }

    private void mostrarExito(String texto) {
        lblMensaje.setStyle("-fx-text-fill: green;");
        lblMensaje.setText(texto);
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        txtClave.clear();
        txtConfirmarClave.clear();
    }
}