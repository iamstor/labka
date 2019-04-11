
public class NumberException extends IllegalArgumentException{
 int num;
    public NumberException(int num) {
        this.num=num;
    }

    @Override
    public String getMessage(){
        return "Неверное количество  " + num;
    }
}



