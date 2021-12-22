package lesson1;

public class MainClass {
    public static void main(String[] args) {
        Unit[] units = new Unit[] {
                new Cat(),
                new Human(),
                new Robot()
        };

        Obstacle[] obstacles = new Obstacle[] {
                new Sprint(200),
                new Wall(0.5),
                new Wall(1.5),
                new Sprint(1000),
                new Wall(0.1),
                new Sprint(2000),
                new Wall(3),
                new Sprint(7000),
                new Wall(1),
                new Sprint(200),
        };

        for (Unit unit: units) {
            for (Obstacle obstacle: obstacles) {
                if (!unit.overcome(obstacle)) {
                    break;
                }
            }
            System.out.println();
        }
    }

}


