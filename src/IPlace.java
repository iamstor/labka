public abstract class IPlace {

    String name;
    String x;
    String y;

    public String getX() {
        return x;

    }

    public String getY() {

        return y;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    IPlace() {
    }

    public IPlace(String name) {
        this.name = name;

    }

    public IPlace(String name, String x, String y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

