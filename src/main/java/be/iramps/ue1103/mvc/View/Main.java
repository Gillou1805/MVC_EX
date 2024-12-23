package be.iramps.ue1103.mvc.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;

import be.iramps.ue1103.mvc.Controller.Controller;


public class Main extends Application implements PropertyChangeListener, IView {
    private static Scene scene;
    private static Stage stage;
    private Pane actualParent; 
    private Controller control;
    private Section sectionWindow;
    private Status statusWindows;

    public void setController(Controller control) {
        this.control = control;
        this.sectionWindow = new Section(this);
        this.statusWindows = new Status(this);
    }

    public Controller getController(){
        return this.control;
    }
// J'ai laissé mes tests dans le switch case
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "listeSection":
                if (evt.getNewValue().getClass().isAssignableFrom(ArrayList.class)) {
                    this.showAllSections((ArrayList<String>) evt.getNewValue());
                    System.out.println("Mise à jour de la vue avec les sections : " + evt.getNewValue());
                }
                break;

            case "sectionSelected":
                if (evt.getNewValue().getClass().isAssignableFrom(ArrayList.class)) {
                    this.showSection((ArrayList<String>) evt.getNewValue());
                    System.out.println("Affichage de la section sélectionnée : " + evt.getNewValue());
                }
                break;

            case "listeStatus":
                if (evt.getNewValue().getClass().isAssignableFrom(ArrayList.class)) {
                    this.showAllStatus((ArrayList<String>) evt.getNewValue());
                    System.out.println("Mise à jour de la vue avec les statuts : " + evt.getNewValue());
                }
                break;

            case "statusSelected":
                if (evt.getNewValue().getClass().isAssignableFrom(ArrayList.class)) {
                    this.showStatus((ArrayList<String>) evt.getNewValue());
                    System.out.println("Affichage du statut sélectionné : " + evt.getNewValue());
                }
                break;

            default:
                System.out.println("Événement non pris en charge : " + evt.getPropertyName());
                break;
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        actualParent = new VBox(10); 
        scene = new Scene(actualParent, 640, 480); 

        Main.stage.setOnCloseRequest(this.control.generateCloseEvent());

        showPrincipalWindow(); 
        Main.stage.setScene(scene);
        Main.stage.show();
    }


    public void showPrincipalWindow() {
        actualParent = new VBox(10);

        // Boutons pour les sections
        HBox boutonSection = new HBox(10);
        Button afficherSection = new Button("Afficher les sections");
        Button ajouterSection = new Button("Ajouter une section");
        afficherSection.setOnAction(control.generateEventHandlerAction("show-sections", () -> new String[] {""}));
        ajouterSection.setOnAction(control.generateEventHandlerAction("add-section", () -> new String[] {""}));
        boutonSection.getChildren().addAll(afficherSection, ajouterSection);

        // Boutons pour les statuts
        HBox boutonStatus = new HBox(10);
        Button afficherStatus = new Button("Afficher les statuts");
        Button ajouterStatus = new Button("Ajouter un statut");
        afficherStatus.setOnAction(control.generateEventHandlerAction("show-statuses", () -> new String[] {""}));
        ajouterStatus.setOnAction(control.generateEventHandlerAction("add-status", () -> new String[] {""}));
        boutonStatus.getChildren().addAll(afficherStatus, ajouterStatus);

        actualParent.getChildren().addAll(boutonSection, boutonStatus);

        if (scene == null) {
            scene = new Scene(actualParent, 640, 480);
            stage.setScene(scene);
        } else {
            scene.setRoot(actualParent);
        }
    }



    @Override
    public void launchApp() {
        Platform.startup(() -> {
            Stage stage = new Stage();
            try {
                this.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void stopApp() {        
        Platform.exit();
    }

    public void showAllSections(ArrayList<String> listeSection) {
        System.out.println("Affichage des sections dans la vue : " + listeSection);

        showPrincipalWindow();

        ListView<String> listView = this.sectionWindow.showAllSections(listeSection);

        if (actualParent.getChildren().size() > 2) {
            actualParent.getChildren().remove(2, actualParent.getChildren().size());
        }

        actualParent.getChildren().add(listView);
    }



    public void showSection(ArrayList<String> infoSection){
        scene.setRoot(this.sectionWindow.showSection(infoSection));
    }

    public void addNewSection(){
        scene.setRoot(this.sectionWindow.addNewSection());
    }

    public void addNewStatus() {
        scene.setRoot(this.statusWindows.addNewStatus());
    }


    public void showAllStatus(ArrayList<String> listeStatus) {
        System.out.println("Affichage des statuts dans la vue : " + listeStatus);

        showPrincipalWindow();

        ListView<String> listView = this.statusWindows.showAllStatus(listeStatus);

        if (actualParent.getChildren().size() > 2) {
            actualParent.getChildren().remove(2, actualParent.getChildren().size());
        }

        actualParent.getChildren().add(listView);
    }



    
   
    public void showStatus(ArrayList<String> infoStatus) {
        scene.setRoot(this.statusWindows.showStatus(infoStatus));
    }

}
