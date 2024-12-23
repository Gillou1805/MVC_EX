package be.iramps.ue1103.mvc.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.function.Supplier;

public class Section {
    private Pane actualParent; 
    private IView app;

    public Section(IView app){
        this.app = app;
    }

    public ListView<String> showAllSections(ArrayList<String> listeSection){
         ObservableList<String> sections = FXCollections.observableArrayList(listeSection);
         ListView<String> listView = new ListView<String>(sections);
         Supplier<String[]> supplier = () -> new String[] {listView.getSelectionModel().getSelectedItem()};
         listView.setOnMouseClicked(app.getController().generateEventHandlerMouse("show-section", supplier));
         return listView;
    }

    public Pane showSection(ArrayList<String> infoSection){
        String id = infoSection.get(0);
        String nom = infoSection.get(1);
        actualParent = new VBox(10);

        // 1ère ligne
        HBox buttonParent = new HBox(5);
        Button afficher = new Button("Afficher les sections");
        Button sauver = new Button("Sauver les changements");
        Button supprimer = new Button("Supprimer la section");

        // 2ème ligne
        HBox infoParent = new HBox(10);
        Label idTexte = new Label("ID:");
        Label nomTexte = new Label("Nom: ");
        Label idL = new Label(id);
        TextField nomT = new TextField(nom);
        
        Supplier<String[]> supplier = () -> new String[] {id, nomT.getText()};
        sauver.setOnAction(app.getController().generateEventHandlerAction(
                "update-section",supplier
                ));

        supplier = () -> new String[] {""};
        afficher.setOnAction(app.getController().generateEventHandlerAction("show-sections", supplier ));      
        
        supplier = () -> new String[] {id};
        supprimer.setOnAction(app.getController().generateEventHandlerAction("delete-section", supplier));
        buttonParent.getChildren().add(afficher); 
        buttonParent.getChildren().add(sauver);
        buttonParent.getChildren().add(supprimer);
       
        infoParent.getChildren().addAll(idTexte,idL,nomTexte,nomT);

        actualParent.getChildren().addAll(buttonParent, infoParent);
        return actualParent;

    }

    public Pane addNewSection(){
        actualParent = new VBox(10);

        // 1ère ligne
        HBox buttonParent = new HBox(5);
        Button afficher = new Button("Afficher les sections");
        Button sauver = new Button("Sauver les changements");

        // 2ème ligne
        HBox infoParent = new HBox(10);
        Label idTexte = new Label("ID:");
        Label nomTexte = new Label("Nom: ");
        Label idL = new Label("N/A");
        TextField nomT = new TextField("");

        
        Supplier<String[]> supplier = () -> new String[] {""};
        afficher.setOnAction(app.getController().generateEventHandlerAction("show-sections", supplier ));
        supplier = () -> new String[] {nomT.getText()};
        sauver.setOnAction(app.getController().generateEventHandlerAction("insert-section",supplier));

        buttonParent.getChildren().add(afficher); 
        buttonParent.getChildren().add(sauver);
       
        infoParent.getChildren().addAll(idTexte,idL,nomTexte,nomT);

        actualParent.getChildren().addAll(buttonParent, infoParent);
        return actualParent;
    }
    
}
