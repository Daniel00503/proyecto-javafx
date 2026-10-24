package renathyu0015.proyectojavafx.controladores;

import java.io.IOException;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import renathyu0015.proyectojavafx.App;
import renathyu0015.proyectojavafx.dao.AulaDAO;
import renathyu0015.proyectojavafx.entidades.Aula;
import renathyu0015.proyectojavafx.entidades.Aula.EstadoAula;
import renathyu0015.proyectojavafx.entidades.Aula.TipoAula;
import renathyu0015.proyectojavafx.entidades.Usuario;
import renathyu0015.proyectojavafx.util.SesionUsuario;

public class HomeController {

    @FXML private Label lblBienvenida;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEdificio;
    @FXML private ComboBox<Integer> cmbPlanta;
    @FXML private TextField txtCapacidad;
    @FXML private ComboBox<TipoAula> cmbTipoAula;
    @FXML private TextField txtResponsable;
    @FXML private ComboBox<EstadoAula> cmbEstado;
    @FXML private Button btnLimpiar;
    @FXML private Button btnGuardar;

    private final AulaDAO aulaDAO = new AulaDAO();

    @FXML
    private void iniciar() {
        Usuario usuario = SesionUsuario.getUsuarioActual();
        if (usuario != null) {
            lblBienvenida.setText("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellido());
        }

        cmbPlanta.setItems(FXCollections.observableArrayList(
                IntStream.rangeClosed(1, 10).boxed().toList()));
        cmbTipoAula.setItems(FXCollections.observableArrayList(TipoAula.values()));
        cmbEstado.setItems(FXCollections.observableArrayList(EstadoAula.values()));

        // Estos botones no tienen onAction en el FXML, así que se conectan aquí.
        btnGuardar.setOnAction(evento -> guardarAula());
        btnLimpiar.setOnAction(evento -> limpiarCampos());
    }

    private void guardarAula() {
        String nombre = txtNombre.getText().trim();
        String edificio = txtEdificio.getText().trim();
        String capacidadTexto = txtCapacidad.getText().trim();
        String responsable = txtResponsable.getText().trim();

        if (nombre.isEmpty() || edificio.isEmpty() || capacidadTexto.isEmpty()
                || responsable.isEmpty() || cmbPlanta.getValue() == null
                || cmbTipoAula.getValue() == null || cmbEstado.getValue() == null) {
            mostrarMensaje(Alert.AlertType.WARNING, "Validación", "Complete todos los campos.");
            return;
        }

        final int capacidad;
        try {
            capacidad = Integer.parseInt(capacidadTexto);
        } catch (NumberFormatException e) {
            mostrarMensaje(Alert.AlertType.WARNING, "Validación", "La capacidad debe ser un número entero.");
            return;
        }

        if (capacidad <= 0) {
            mostrarMensaje(Alert.AlertType.WARNING, "Validación", "La capacidad debe ser un número positivo.");
            return;
        }

        try {
            if (aulaDAO.existeAulaEnEdificio(nombre, edificio)) {
                mostrarMensaje(Alert.AlertType.WARNING, "Aula duplicada",
                        "Ya existe un aula con ese nombre en ese edificio.");
                return;
            }

            Aula aula = new Aula(nombre, edificio, cmbPlanta.getValue(), capacidad,
                    cmbTipoAula.getValue(), responsable, cmbEstado.getValue());

            if (aulaDAO.registrar(aula)) {
                mostrarMensaje(Alert.AlertType.INFORMATION, "Éxito", "El aula se registró correctamente.");
                limpiarCampos();
            } else {
                mostrarMensaje(Alert.AlertType.ERROR, "Error", "No se pudo registrar el aula.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje(Alert.AlertType.ERROR, "Error", "Ocurrió un problema al guardar el aula.");
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtEdificio.clear();
        txtCapacidad.clear();
        txtResponsable.clear();
        cmbPlanta.getSelectionModel().clearSelection();
        cmbTipoAula.getSelectionModel().clearSelection();
        cmbEstado.getSelectionModel().clearSelection();
    }

    @FXML
    private void cerrarSesion() throws IOException {
        SesionUsuario.cerrar();
        App.setRoot("login");
    }

    private void mostrarMensaje(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
