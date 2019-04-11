import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import java.util.*;

public class Helper {

    private Helper(){}


    public static boolean aborted = false;
    private static boolean canAdd = true;

    /**
     * метод, ищущий нужную команду, чтобы впоследствии ее выполнить
     *
     * @param command   команда,которую мы выбираем

     * @param str       строчка,необходимая команде для работы
     */


    public static void handler(String command,HashSet<Character> characters, String str) {
        switch (command.toLowerCase()) {
            case "show":
                show(characters);
                break;
            case "save":
                save(characters, str);
                break;
            case "add":
                add(characters,str);
                break;
            case "add_if_max":
                add_if_max(characters,str);
                break;

            case "info":
                info(characters);
                break;
            case "remove":
                remove(characters, str);
                break;
            case "remove_greater":
                remove_greater(characters, str);
                break;
            case "help":
                help();
                break;
            case "abort":
                aborted =true;
                System.exit(0);
                break;
            default:
                System.out.println("Такой команды не существует, попробуйте еще раз");
        }
    }
    /**
     * метод, сохранающий  данные работы программы в исходник
     *
     * @param fileName путь до файла
     */
    public static void save(HashSet<Character> characters, String fileName) {
        try (JsonWriter writer = new JsonWriter(new FileWriter(fileName))) {
            writer.beginArray();
            for (Character c :characters) {

                writer.beginObject();
                writer.name("name").value(c.getName());
                writer.name("age").value(c.getAge());
                writer.name("place");
                writer.beginObject();
                if( c.getPlace()!=null) {
                    writer.name("name").value(c.getPlace().getName());
                    writer.name("x").value(c.getPlace().getX());
                    writer.name("y").value(c.getPlace().getY());}
                    writer.endObject();

                writer.endObject();
            }

            writer.endArray();

            writer.flush();



        } catch (IOException e) {

            System.err.println("Ошибка ввода");

        }


        catch(Exception e){
           System.err.println("Произошла ошибочка, ну вот((");
        }


    }


    /**
     * метод, удаляющий из коллекции все элементы, превышающие заданный
     *
     * @param name имя персонажа
     */

    public static void remove_greater(HashSet<Character> characters, String name) {
        Character point = null;
        Iterator<Character> iter = characters.iterator();
        while (iter.hasNext()) {
            point = iter.next();
            if (point.name.toLowerCase().compareTo(name.toLowerCase())<0) {
                iter.remove();
                System.out.println("Элемент удалён");
            }
        } }


    /**
     * удалить элемент из коллекции по его значению
     *
     * @param name строчка, являющаяся именем персонажа
     */

    public static void remove(HashSet<Character> characters, String name) {
        boolean flag = true;

        for (Character ch : characters) {
            if (ch.getName().toLowerCase().equals(name.toLowerCase())) {
                System.out.println("Удален человек по имени \"" + ch.getName() + "\"");
                characters.remove(ch);
                flag = false;
                return;
            }
        }

        if (flag) {
            System.out.println("Такого объекта не было найдено");
        }
    }

    /**
     * метод, добавяющий новый элемент в коллекцию
     *
     * @param json строчка в формате json
     */

    public static void add(HashSet<Character> characters, String json) {
        Gson gson = new Gson();
        Map<String, Object> map;

        try {
            Type type = new TypeToken<LinkedTreeMap<String, Object>>() {
            }.getType();
            map = (LinkedTreeMap<String, Object>) gson.fromJson(json, type);

            Place innerPlace;
            if (map.containsKey("name")) {
                String addingName = map.get("name").toString();
                for (Character check : characters) {
                    if (check.getName().toLowerCase().equals(addingName.toLowerCase()))
                        canAdd = false;
                }


                if (map.containsKey("place")) {
                    LinkedTreeMap<String, String> innerp = (LinkedTreeMap<String, String>) map.get("place");
                    innerPlace = PlaceFactory.newInstance(innerp);
                    map.remove("place");
                    map.put("place", innerPlace);
                }
            }
            if (canAdd) {
                characters.add(CharacterFactory.newInstance(map));
            } else {
                System.out.println("Нельзя добавлять людей с одинаковыми именами ");
            }
        }catch (Exception e) {
            System.err.println("Возникла ошибка при добавлении объекта, возможно вы ввели неверно вашу json строку");
        }
    }




    /**
     *  метод,добавляющий новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
     *
     * @param json
     */

    public static  void add_if_max( HashSet<Character> characters,String json) {
       try{
        Iterator<Character> iter = characters.iterator();
        if (iter.hasNext()) {
            Character max = iter.next();
            for (Character character : characters) {
                if (character.compareTo(max) > 0) {
                    max = character;
                }
            }
            Gson gson = new Gson();
            Map<String, Object> map;
            Type type = new TypeToken<LinkedTreeMap<String, Object>>() {
            }.getType();
            map = (LinkedTreeMap<String, Object>) gson.fromJson(json, type);

            String addingName = null;
            if (map.containsKey("name")) {
                addingName = map.get("name").toString();
            } else {
                System.err.println("Укажите имя в формате json");
                return;
            }

            if (max.compareTo(new Character(addingName)) < 0) {
                add(characters, json);

            } else {
                System.out.println(addingName);
                System.out.println(max.getName());
                System.err.println("Элемент не добавлен, так как он не максимален");

            }
        } else {
            add(characters,json);
        }}catch(Exception e){
           System.out.println(" ");
       }

    }

    public static void show(HashSet<Character> characters) {
        System.out.println("Коллекция:");
        System.out.println(Arrays.toString(characters.toArray()));
    }

    /**
     * метод-шпаргалка по командам
     */



    public static void help() {
        System.out.println("Доступные команды:");
        System.out.println("add {element} -команда, добавляющая  элемент в коллекцию, element - строка в формате json");
        System.out.println("show - выводит список всех элементов коллекции");
        System.out.println("save - сохраняет текущую в исходный файл");
        System.out.println("remove_greater {element} - команда, удаляющая из коллекции все элементы, превышающий заданный,element- строка в формате json ");
        System.out.println("info - выводит информацию о коллекции");
        System.out.println("remove  - удаляет элемент из коллекции, element- имя персонажа");
        System.out.println("add_if_max {element} - добавляет элемент в коллекцию если он максимальный, element - значение элемента");
        System.out.println("help - выводит список доступных команд");

    }

    /**
     * метод,выводящий информацию о коллекции
     *
     *
     */

    public static void info(HashSet<Character> characters) {
        System.out.println("тип коллекции:");
        System.out.println(characters.getClass());
        System.out.println("количество элементов:");
        System.out.println(characters.size());
        Date date = new Date();
        System.out.println("дата инициализации:");
        System.out.println(date.toString());
    }
    }

