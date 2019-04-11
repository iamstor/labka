public class Penguin extends Character {




    public Penguin(String name) {
        super(name);
        this.name = name;
    }

    String state = "Напуганный";

    public void behavior() {
        System.out.println(state + " " + this.name + " " + "ведет себя мирно");
    }

    public void beak() {
        System.out.println(state + " " + this.name + " " + "разъяренно щелкает клювом");
    }

    static class Number {
        static int number = 5;
        ;

        static void much() {
            System.out.println("Пингвинов " + number);
        }
    }

}


