package renathyu0015.proyectojavafx.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuariossistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer Id;

    @Column(name = "Nombre")
    private String Nombre;

    @Column(name = "Apellido")
    private String Apellido;

    @Column(name = "Usuario")
    private String Usuario;

    @Column(name = "Clave")
    private String Clave;

    @Column(name = "Correo")
    private String Correo;

    @Column(name = "Roll")
    private String Roll;

    @Column(name = "Estado")
    private String Estado;

    //-------- Constructor
    public Usuario() {
    }

    //-------- Setters
    public void setId(int Id) { this.Id = Id; }
    public void setNombre(String Nombre) { this.Nombre = Nombre; }
    public void setApellido(String Apellido) { this.Apellido = Apellido; }
    public void setUsuario(String Usuario) { this.Usuario = Usuario; }
    public void setClave(String Clave) { this.Clave = Clave; }
    public void setCorreo(String Correo) { this.Correo = Correo; }
    public void setRoll(String Roll) { this.Roll = Roll; }
    public void setEstado(String Estado) { this.Estado = Estado; }

    //-------- Getters
    public Integer getId() { return Id; }
    public String getNombre() { return Nombre; }
    public String getApellido() { return Apellido; }
    public String getUsuario() { return Usuario; }
    public String getClave() { return Clave; }
    public String getCorreo() { return Correo; }
    public String getRoll() { return Roll; }
    public String getEstado() { return Estado; }
}