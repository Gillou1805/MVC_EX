package be.iramps.ue1103.mvc;

import be.iramps.ue1103.mvc.Controller.Controller;

public class App {
    
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.initialize();
        controller.start();
    }
}
