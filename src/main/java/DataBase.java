import java.util.ArrayList;
import java.util.List;

public class DataBase {


    private final ArrayList<SuperHero> superHeroes = new ArrayList<>();


    private String filename = "superHeroes.csv";

    public void setFileName(String filename) {
        if( "".equals(filename.trim()) ) {
            filename = "superHeroes.csv";
        }

        this.filename = filename;
    }


    public void loadFile()  {

        CSVFile file = new CSVFile(filename);

        file.openForRead();

        while (file.hasNext()) {
            String[] strings = file.next();
            addSuperHero(SuperHero.fromStrings(strings));
        }
        file.close();

    }

    public void saveFile() {
        CSVFile file = new CSVFile(filename);
        file.openForWrite();

        file.writeHeading(SuperHero.getStringNames());

        for(SuperHero superHero : superHeroes) {
            file.writeLine(superHero.toStrings());
        }
    }


    public SuperHero createSuperHero(String name, String superPower, int creationYear, boolean isHuman, double strenght) {
        SuperHero superHero = new SuperHero(name, superPower, creationYear, isHuman, strenght );
        return addSuperHero(superHero);
    }

    private SuperHero addSuperHero(SuperHero superHero) {
        superHeroes.add(superHero);
        return superHero;
    }

    public Iterable<SuperHero> getAllSuperHeroes() {
        return superHeroes;
    }

    public int size() {
        return superHeroes.size();
    }
    public void removeSuperHero(SuperHero superHero) {
        superHeroes.remove(superHero);
    }

    public SuperHero[] findSuperHero(String search) {
        ArrayList<SuperHero> foundSuperHeroes = new ArrayList<>();
        for(SuperHero superHero : superHeroes) {
            if(superHero.matches(search)) {
                foundSuperHeroes.add(superHero);
            }
        }

        return foundSuperHeroes.toArray(new SuperHero[0]); // Note: size 0 is better than size foundStudents.size(), since the array is never used!
    }






}

