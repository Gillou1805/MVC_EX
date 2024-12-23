package be.iramps.ue1103.mvc.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import be.iramps.ue1103.mvc.Controller.Controller;

public class TestingView implements PropertyChangeListener, IView {
    Controller control;

    @Override
    public void setController(Controller control) {
        this.control = control;
    }

    @Override
    public void launchApp() {
    }

    @Override
    public void stopApp() {
    }

    @Override
    public void showPrincipalWindow() {
    }

    @Override
    public void addNewSection() {
    }

    @Override
    public void showAllSections(ArrayList<String> listeSection) {
        for (String string : listeSection) {
            System.out.print(string+"-");
        }
    }

    @Override
    public void showSection(ArrayList<String> infoSection) {
        System.out.println(infoSection.get(0) + "-" +infoSection.get(1));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "listeSection":
                if (evt.getNewValue().getClass().isAssignableFrom(ArrayList.class))
                    this.showAllSections((ArrayList<String>) evt.getNewValue());
                break;

            case "sectionSelected":
                if (evt.getNewValue().getClass().isAssignableFrom(ArrayList.class))
                    this.showSection((ArrayList<String>) evt.getNewValue());
            default:
                break;
        }
    
    }

    @Override
    public void addNewStatus() {
    }

    @Override
    public void showAllStatus(ArrayList<String> listeStatus) {
        for (String string : listeStatus) {
            System.out.print(string+"-");
        }
    }

    @Override
    public void showStatus(ArrayList<String> infoStatus) {
        System.out.println(infoStatus.get(0) + "-" +infoStatus.get(1));
    }

    @Override
    public Controller getController() {
        return this.control;
    }
    
}
