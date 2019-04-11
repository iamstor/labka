import java.io.*;
import java.util.*;

public class Main {

    private static  HashSet<Character> characters = new HashSet<Character>();
    private static String inputFile;





    public static void main(String[] args) {




        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                if (!Helper.aborted) {
                    Helper.handler("save", characters, inputFile);
                }
            }
            catch(Exception e){
                System.err.println("Unsave!!!");

            }


        }));
        WeakHashMap<String, String> exit = new WeakHashMap<>();
        exit.put("a", "a");
        exit.put("c", "c");
        exit.put("z", "z");
        exit.put("f", "f");
        exit.put("b", "b");


        System.out.println("Идет загрузка данных из файла сохранения");
        try {

            if (args.length >= 1)
                inputFile = args[0];
            else
                inputFile = new File(".").getCanonicalPath()+"/resourses/character_info.json";

            String input = DownloadFile.readStrings(inputFile);

            DownloadFile.load(characters,input);
            Helper.handler("show", characters, null);




            System.out.println("Данные успешно загрузились");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите команду из списка доступных, если не знаете ни одну из команд введите help или посмотрите документацию");
            System.out.print("Введите команду > ");

            input = "";
            String lastCommand = "";
            String addStr = "";
            boolean commandEnd = true;
            int nestingJSON = 0;

            while (!(input = scanner.nextLine().trim()).toLowerCase().equals("exit")) {
                if (!input.equals("")) {
                    String command = input.split(" ")[0].toLowerCase();
                    if (nestingJSON < 0) {
                        System.err.println("Вы совершили ошибку при введении json строки. Повторите еще раз");
                        nestingJSON = 0;
                        lastCommand = "";
                        addStr = "";
                        commandEnd = true;
                    }

                    if (!commandEnd && (lastCommand.equals("add") || lastCommand.equals("add_if_max"))) {

                        nestingJSON += DownloadFile.charCounter(input, '{');
                        nestingJSON -= DownloadFile.charCounter(input, '}');
                        addStr += input;
                        if (nestingJSON == 0)
                            commandEnd = true;

                    } else if (command.equals("add_if_max") && commandEnd) {

                        lastCommand = "add_if_max";
                        commandEnd = false;
                        addStr = input.substring(10).trim();
                        nestingJSON += DownloadFile.charCounter(addStr, '{');
                        nestingJSON -= DownloadFile.charCounter(addStr, '}');
                        if (nestingJSON == 0)
                            commandEnd = true;

                    } else if (command.equals("add") && commandEnd) {

                        lastCommand = "add";
                        commandEnd = false;
                        addStr = input.substring(3).trim();
                        nestingJSON += DownloadFile.charCounter(addStr, '{');
                        nestingJSON -= DownloadFile.charCounter(addStr, '}');
                        if (nestingJSON == 0)
                            commandEnd = true;

                    }else if (command.equals("show") && commandEnd) {
                        lastCommand = "show";
                        Helper.handler("show", characters,null);
                    } else if (command.equals("save") && commandEnd) {
                        lastCommand = "save";
                        Helper.handler("save", characters,inputFile);
                    } else if (command.equals("remove_greater") && commandEnd) {
                        lastCommand = "remove_greater";
                        Helper.handler("remove_greater", characters, input.substring(14).trim());
                    } else if (command.equals("info") && commandEnd) {
                        lastCommand = "info";
                        Helper.handler("info", characters, null);
                    } else if (command.equals("remove") && commandEnd) {
                        lastCommand = "remove";
                        Helper.handler("remove",characters,  input.substring(6).trim());
                    } else if (command.equals("help") && commandEnd) {
                        lastCommand = "help";
                        Helper.handler("help", characters,null);
                    }

                    if (lastCommand.equals("add") && commandEnd) {
                        Helper.handler("add",characters, addStr);
                        addStr = "";
                    } else if (lastCommand.equals("add_if_max") && commandEnd) {
                        Helper.handler("add_if_max", characters,addStr);
                        addStr = "";
                    }
                }

                if (commandEnd)
                    System.out.print("> ");
            }


        }catch (FileNotFoundException e) {
            System.err.println("Файл сохранения не найден!");

        } catch (Exception e) {

            System.err.println("Ошибка");
        }


    }
}
