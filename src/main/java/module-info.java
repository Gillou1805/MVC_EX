module be.iramps.ue1103.mvc {
    requires transitive java.desktop;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens be.iramps.ue1103.mvc to javafx.fxml;
    exports be.iramps.ue1103.mvc.View;    
    exports be.iramps.ue1103.mvc.Controller; 
    exports be.iramps.ue1103.mvc.Model;
}
