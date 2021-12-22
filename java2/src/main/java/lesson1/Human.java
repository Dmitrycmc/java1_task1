package lesson1;

class Human implements Unit, Running, Jumping {
    private double runLimit = 5000;
    private double jumpLimit = 2;

    public void run(double size, boolean success) {
        System.out.println("Человек" + (success ? "" : " не") + " пробежал " + size + " метров");
    }

    public void jump(double size, boolean success) {
        System.out.println("Человек" + (success ? "" : " не") + " прыгнул на " + size + " метров");
    }

    public boolean overcome(Obstacle obstacle) {
        boolean success;
        switch (obstacle.type) {
            case SPRINT:
                success = obstacle.size <= runLimit;
                run(obstacle.size, success);
                return success;
            case WALL:
                success = obstacle.size <= jumpLimit;
                jump(obstacle.size, success);
                return success;
        }
        return false;
    }
}
