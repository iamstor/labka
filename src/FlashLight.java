public class FlashLight extends Place {

    FlashLight() {


    }

    public void flash(boolean flashlight) {
        if (flashlight) {
            System.out.println("Первый фонарик горит");
        } else {
            System.out.println("Первый фонарик не горят");
        }

    }

    class Danger { //non-static вложенный класс

        public void danger(boolean d) {
            if (d) {
                System.out.println("Нельзя включать второй фонарик, опасно!");
            } else {
                System.out.println("Нет опасности, можно включить второй фонарик!");
            }
        }

    }
}
