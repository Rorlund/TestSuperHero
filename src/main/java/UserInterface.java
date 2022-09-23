import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public final Controller controller;

    public UserInterface(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        System.out.println("velkommen til lav din superhelt program!");
        System.out.println("---------------------------");

        try {
            controller.start();
        } catch (CSVFileReadException exception) {
            System.out.println("Couldn't load file - start with empty database");
        }

        mainMenu();
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                                        
                    Indtast en af nedestående numre:
                    1) lav din helt
                    2) se liste med dine helte
                    3) find en superhelt i databsen ( skal finde en superhelt inden du kan redigere eller slette)
                        4) rediger en superhelt
                        5) Slet en superhelt
                                    
                    0) Luk programmet""");
            int selection = scanner.nextInt();
            scanner.nextLine();
            switch (selection) {
                case 0:
                    boolean notSaved = true;
                    while (notSaved) {
                        try {
                            System.out.println("Gemmer listen af superhelte");
                            controller.end();
                            notSaved = false;
                            System.out.println("programmet lukker");
                            isRunning = false;
                        } catch (CSVFileWriteException exception) {
                            notSaved = true;
                            System.out.println("kunne ikke gemme");
                            System.out.print("indtast et andet filnavn: ");
                            String filename = scanner.nextLine();
                            controller.setFileName(filename);
                        }
                    }
                    break;
                case 1:
                    createSuperHero(scanner);
                    break;
                case 2:
                    listAllSuperHeroes();
                    break;
                case 3:
                    findSuperHero(scanner);
                    break;
                case 4:
                    editSuperHero(scanner);
                    break;
                case 5:
                    deleteSuperHero(scanner);
                    break;
            }
        }

    }

    private void createSuperHero(Scanner scanner) {
        System.out.println("""
                Lav din superhelt
                --------------
                """);
        System.out.print("Navnet på din helt: ");
        String name = scanner.nextLine();
        System.out.print("Superkraft: ");
        String superPower = scanner.nextLine();
        System.out.print("Hvilket år blev din helt lavet: ");
        int creationYear;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("det er ikke et nummer");
                scanner.next();
            }
            creationYear = scanner.nextInt();
        } while (creationYear < 0);

        System.out.println("Er din helt et menneske ja/nej: ");

        boolean isHuman = false;
        boolean jaNej = false;

        do {
            String choice = scanner.nextLine();
            if (choice.equals("ja")) {
                isHuman = true;
                jaNej = true;
            } else if (choice.equals("nej")) {
                isHuman = false;
                jaNej = true;
            } else {
                System.out.print("indtast ja/nej:");
            }
        } while (!jaNej);

        System.out.print("Hvad er din helts styrkepoint: ");
        double strenght = scanner.nextDouble();


        SuperHero superHero = controller.createSuperHero(name, superPower, creationYear, isHuman, strenght);

        System.out.println(superHero + " er tilføjet");


    }

    private void listAllSuperHeroes() {
        System.out.println("""
                alle superhelte
                ------------""");
        int counter = 1;
        for (SuperHero superHero : controller.getAllSuperHeroes()) {
            System.out.println(counter + " - " + superHero);
            counter++;
        }
        System.out.println("-------------------");
        System.out.println("der er  " + controller.getNumberOfSuperHeroes() + " superhelte i databasen");
    }

    private void findSuperHero(Scanner scanner) {
        System.out.println("""
                Find en superhelt
                -----------------
                indtast en del af navnet på den superhelt du vil finde
                """);
        System.out.print(": ");


        String search = scanner.nextLine().trim().toLowerCase();

        SuperHero[] foundSuperHero = controller.findSuperHero(search);


        if (foundSuperHero.length == 1) {
            controller.selectSuperHero(foundSuperHero[0]);
            System.out.println("fandt " + foundSuperHero[0]);
        } else if (foundSuperHero.length > 1) {
            System.out.println("fandt flere helte der indeholdt " + search);
            for (int i = 0; i < foundSuperHero.length; i++) {
                System.out.println("#" + (i + 1) + ": " + foundSuperHero[i]);
                System.out.println(i);
            }
            System.out.println("vælg hvilken helt ved at intdaste det nummer ved siden af #:");

            int select = 0;
            while (select < 1 || select > foundSuperHero.length) {
                select = scanner.nextInt();
                scanner.nextLine();
                if (select < 1 || select > foundSuperHero.length) {
                    System.out.println("intdast et nummer mellem 1 og " + foundSuperHero.length);
                } else {
                    controller.selectSuperHero(foundSuperHero[select - 1]);
                    System.out.println("du valgte " + foundSuperHero[select - 1]);
                }
            }
        } else {
            System.out.println("Der er ikke nogen superhelt i databased der matcher de kriterier");
            controller.selectSuperHero(null);

        }



    }


    private void editSuperHero(Scanner scanner) {


    }

    private void deleteSuperHero(Scanner scanner) {
        if (controller.hasSelectedSuperHero()) {
            System.out.println("Er du sikker på du vil slette: '" + controller.getSelectedSuperHero() + "' (y/N)?\nDet kan ikke gøres om!");
            String answer = scanner.nextLine();
            if ("y".equalsIgnoreCase(answer)) {
                System.out.println("'" + controller.deleteSelectedSuperHero() + "' er blevet slettet.");
            } else {
                System.out.println("ok");
            }

        } else {
            System.out.println("du skal finde en superhelt først");
        }
    }
}

