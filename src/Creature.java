import java.util.Objects;

public abstract class Creature {
    String name;
    String age;


    Creature(){

    }


    Creature(String name) {
        this.name = name;
    }

    public Creature(String name, String age) {

        this.name = name;
        this.age = age;
    }

    public String getName() {

        return name;
    }
    public void setName(String name) {
        this.name = name;

    }

    public String getAge(){
        return age;
    }

    public void setAge(String age){
        this.age= age;
    }

}
