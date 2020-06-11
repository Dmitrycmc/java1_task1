package ru.gb.java2.lesson1;

class Robot implements Unit, Running, Jumping {
    private double runLimit = 10000;
    private double jumpLimit = 4;

    public void run(double size, boolean success) {
        System.out.println("Робот" + (success ? "" : " не") + " пробежал " + size + " метров");
    }

    public void jump(double size, boolean success) {
        System.out.println("Робот" + (success ? "" : " не") + " прыгнул на " + size + " метров");
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
