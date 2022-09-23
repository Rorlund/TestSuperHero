public class SuperHero {


        private String name;
        private String superPower;
        private int creationYear;
        private boolean isHuman;
        private double strenght;

        public SuperHero(String name, String superPower, int creationYear, boolean isHuman, double
                strenght) {
            this.name = name;
            this.superPower = superPower;
            this.creationYear = creationYear;
            this.isHuman = isHuman;
            this.strenght = strenght;
        }


    private SuperHero() {

        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSuperPower() {
            return superPower;
        }

        public void setSuperPower(String superPower) {
            this.superPower = superPower;
        }

        public int getCreationYear() {
            return creationYear;
        }

        public void setCreationYear(int creationYear) {
            this.creationYear = creationYear;
        }


        public boolean getIsHuman() {
            return isHuman;
        }

        public void setIsHuman(boolean isHuman) {
            this.isHuman = isHuman;
        }

        public double getStrenght() {
            return strenght;
        }

        public void setStrenght(double strenght) {
            this.strenght = strenght;
        }

    @Override
    public String toString() {
            return "Navn: " + name + " SuperKraft: " + superPower + " Oprindelsesår: " + creationYear + " Er menneske: " + isHuman + " Styrkepoint: " + strenght;

    }

    public String[] toStrings() {
        return new String[]{name, superPower, String.valueOf(creationYear), String.valueOf(isHuman), String.valueOf(strenght)};
    }

    public static String[] getStringNames() {
        return new String[]{"navn", "superkraft", "oprindelsesår", "er menneske", "styrkepoint"};
    }

    public static SuperHero fromStrings(String[] strings) {
        SuperHero newSuperHero = new SuperHero();
        newSuperHero.name = strings[0];
        newSuperHero.superPower = strings[1];
        newSuperHero.creationYear = Integer.parseInt(strings[2]);
        newSuperHero.isHuman = Boolean.parseBoolean(strings[3]);
        newSuperHero.strenght = Double.parseDouble(strings[4]);


        return newSuperHero;
    }

    public boolean matches(String search) {
        return name.toLowerCase().contains(search);
    }




}


