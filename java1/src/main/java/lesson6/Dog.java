package lesson6;

class Dog extends Animal {
    private static int counter = 0;

    Dog(String name) {
        super(name, 500, 10, 0.4);

        counter++;
    }

    @Override
    void info() {
        System.out.print("Пес ");
        super.info();
    }

    static void printAmount() {
        System.out.println("Создано собак: " + counter);
    }
}
