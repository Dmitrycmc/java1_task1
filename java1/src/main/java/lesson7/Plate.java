package lesson7;

class Plate {
    private int food;

    Plate(int food) {
        this.food = food;
    }

    boolean decreaseFood(int n) {
        if (n <= food) {
            food -= n;
            return true;
        }
        return false;
    }

    void increaseFood(int n) {
        food += n;
    }

    void printInfo() {
        System.out.println("Plate: " + food);
    }
}
