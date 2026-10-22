module renathyu0015.proyectojavafx {
    // Módulos que este programa usa
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    // Permisos para que otros lean nuestras clases por dentro
    opens renathyu0015.proyectojavafx to javafx.fxml;
    opens renathyu0015.proyectojavafx.controladores to javafx.fxml;
    opens renathyu0015.proyectojavafx.entidades to org.hibernate.orm.core;

    // Paquete visible desde afuera (ahí está App)
    exports renathyu0015.proyectojavafx;
}