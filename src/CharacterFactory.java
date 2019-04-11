import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {

    private CharacterFactory() {
    }

    /**
     * @param things параметр для создания Character
     * @return объект типа Character
     * @throws Exception если параметров не достаточно
     */
    public static Character newInstance(Map<String, Object> things) throws Exception {
        Character character = null;
        String age=null;
        if (things.containsKey("name")) {
            String name = null;
            name = things.get("name").toString();
            if (things.containsKey("age")){
                age = things.get("age").toString();
            }



            character = new Character(name,age);

        }
        if (things.containsKey("place")) {
            character.setPlace((Place) things.get("place"));
        }
        return character;

    }
}


