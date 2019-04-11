

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class Place extends IPlace {

    public Place() {
    }

    public Place(String name) {
        super(name);
        this.name = name;

    }

    public Place(String name, String x,String y) {
        super(name, x, y);
        this.name = name;
        this.x = x;
        this.y = y;

    }





    public void add(Character c) {
        people.add(c);
    }                                // добавляем человека в место

    ArrayList<Character> people = new ArrayList<Character>();


    public ArrayList<Character> getPeople() {
        return this.people;                     //список людей в этом месте
    }


    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }
}




