public class Controller {

    private final DataBase db;
    private SuperHero selectedSuperHero = null;

    public Controller() {
        db = new DataBase();
    }
    public void start() {
        db.loadFile();
    }

    public void end() {
        db.saveFile();
    }

    public void setFileName(String filename) {
        db.setFileName(filename);
    }

    public int getNumberOfSuperHeroes() {
        return db.size();
    }

    public Iterable<SuperHero> getAllSuperHeroes() {
        return db.getAllSuperHeroes();
    }


    public SuperHero createSuperHero(String name, String superPower, int creationYear, boolean isHuman, double strenght) {
        return db.createSuperHero(name, superPower, creationYear, isHuman, strenght);
    }

    public SuperHero[] findSuperHero(String search) {
        return db.findSuperHero(search);
    }

    public void selectSuperHero(SuperHero superHero){
        selectedSuperHero = superHero;
    }

    public SuperHero getSelectedSuperHero(){
        return selectedSuperHero;
    }
    public boolean hasSelectedSuperHero() {
        return selectedSuperHero != null;
    }
    public SuperHero deleteSelectedSuperHero() {
        SuperHero superHero = selectedSuperHero;

        db.removeSuperHero(selectedSuperHero);
        selectedSuperHero = null;

        return superHero;
    }





}
