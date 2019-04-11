import java.util.Objects;
import java.util.Scanner;



public class Character extends Creature implements Skills, Comparable<Character> {

    Place place;




    public Character(String name){
        super(name);
        this.name = name;
    }

    public Character(String name, String age) {
        super(name,age);

        this.name = name;
        this.age=age;


    }

    @Override
    public int compareTo(Character o) {
        return this.name.compareTo(o.name);
    }


    @Override
    public boolean equals(Object o) {
        Character ch = (Character)o;
        if (this.name ==ch.name){
            return true;
        }else{
            return false;
        }

    }


    @Override
    public int hashCode() {
        return Objects.hash(place);
    }

    public void used_to() {
        System.out.println(this.name + " " + "должен привыкнуть спокойнее реагировать, но");
    }

    @Override
    public String toString() {
        return "Character{" +
                "place=" + place +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }


    public void feel(Feelings Fl) {
        String s = " ";
        switch (Fl) {
            case WORRY:
                s = "переживания, которые  ранят его и без того покалеченное сердце ";
                break;
            case PAIN:
                s = "боль";
            case HORROR:
                s = "ужааас";
                break;
        }



        System.out.println( " " + this.name + " " + "испытывает" + " " + s);

    }

    public void walk() {
        System.out.println( " " + this.name + " " + "подошел поближе");
        class Timeless {
            public void go() {                                                     //локальный класс
                System.out.println("Из-за недостатка времени продолжил путь");

            }

        }
        Timeless timeless = new Timeless();
        timeless.go();

    }

    public void watch() {

        System.out.println(this.name + " " + "увидел препятствие");

    }




    public Place getPlace() {

        return this.place;
    }



    public void setPlace(Place p) {
        this.place = p;
        p.add(this);
    }



    public static int Num(Scanner in) throws AgeException {
        System.out.print("Введите возраст героя - ");
        int num = 9;
        String n = in.next();
        num = Integer.parseInt(n);

        if (num <=10) throw new AgeException(num);

        System.out.printf("Герою %d   \n", num);
        return num;

    }




}



