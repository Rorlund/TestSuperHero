public class Main {

    public void start() {
        Controller controller = new Controller();
        UserInterface ui = new UserInterface(controller);
        ui.start();
    }

    public static void main(String[] args) {
        Main application = new Main();
        application.start();



    }
}
