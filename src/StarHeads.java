import java.util.Scanner;

public class StarHeads extends Creature {





    public StarHeads(String name) {
        super(name);
        this.name = name;
    }

    public static int Num(Scanner in)  throws NumberException {
        System.out.print("Введите количество здездоголовых - ");
        int num = 7;
        String n = in.next();
        num = Integer.parseInt(n);

        if (num < 8) throw new NumberException(num);

        System.out.printf("Звездоголовых %d   \n", num);
        return num;

    }
    public StarHeads(String name, Scanner in) {
      super(name);
      this.name =name;
        boolean flag = true;
        while(flag){

            try {
                Num(in);
                flag = false;
            } catch (NumberException e) {
                e.printStackTrace();

            }
        }
    }



    public void skill() {

        System.out.println(this.name + " " + "почил в ледяной мгле");
    }


    public void reason(Situation reason) {
        String s = " ";
        switch (reason) {
            case ALARM:
                s = "потревожив птиц, навлек на себя их ярость";
                break;
            case RUN:
                s = " бежал от преследователей, хотел поскорее добраться до оставленных саней,но тут убийцы нагнали  и прикончили его ";
            case FIGHT:
                s = "был убит во время ссоры с Выжившим Звездоголовым";
                break;
        }


        System.out.println(this.name  + s);

    }


}


