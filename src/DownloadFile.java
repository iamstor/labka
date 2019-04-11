import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;



public class DownloadFile {

    private DownloadFile() {
    }

    /**
     * метод,считающий количество символов в исходной строке
     *
     * @param str исходная строка
     * @param ch  искомый символ
     * @return количество символов в строке
     */
    public static int charCounter(String str, char ch) {
        int kol = 0;
        for (char current : str.toCharArray())
            if (current == ch)
                kol++;
        return kol;
    }

    /**
     * метод,читающий файл
     *
     * @param path путь до файла
     * @return массив строк, который содержит все символы файла
     */
    public static String readStrings(String path) throws FileNotFoundException {
        String in = "";

        File file = new File(path);
        if (file.exists()) {
            try (BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(file))) {
                String currentLine = "";
                int i;
                while ((i = fileInput.read()) != -1) {
                    char c = (char) i;
                    if ((c != '\n') && (c != ' '))
                        in += c;
                }
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException();
            } catch (IOException e) {
                System.err.println("Ой-ой-ой");
            }
        } else {
            throw new FileNotFoundException();
        }

        return in.trim();
    }


    /**
     * Возвращает hashset по строке json
     *
     * @param json строчка json
     */

    public static void load(HashSet<Character> storage, String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<LinkedTreeMap<String, Object>>>() {
        }.getType();
        ArrayList<LinkedTreeMap<String, Object>> inputs = gson.fromJson(json, type);
        try {
            for (int i = 0; i < inputs.size(); i++) {
                HashMap<String, Object> params = new HashMap<>();
                LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) inputs.get(i);

                String cName = map.get("name").toString();
                String cAge = map.get("age").toString();

                params.put("name", cName);
                params.put("age", cAge);


                /*

                 */

                if (map.containsKey("place")) {
                    Map<String, String> placeParams = new HashMap<>();
                    LinkedTreeMap<String, String> place = (LinkedTreeMap<String, String>) map.get("place");

                    String pName = place.get("name");
                    String pX = place.get("x");
                    String pY = place.get("y");

                    placeParams.put("name", pName);
                    placeParams.put("x",pX);
                    placeParams.put("y",pY);

                    params.put("place", PlaceFactory.newInstance(placeParams));
                }


                storage.add(CharacterFactory.newInstance(params));

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Возникла проблема при добавлении объекта, проверьте вашу json строку");
        }
    }
}
