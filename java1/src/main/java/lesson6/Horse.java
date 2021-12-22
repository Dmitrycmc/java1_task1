package lesson6;

class Horse extends Animal {
    private static int counter = 0;

    Horse(String name) {
        super(name, 1500, 100, 3);

        counter++;
    }

    @Override
    void info() {
        System.out.print("Лошадь ");
        super.info();
    }

    static void printAmount() {
        System.out.println("Создано лошадей: " + counter);
    }
}
