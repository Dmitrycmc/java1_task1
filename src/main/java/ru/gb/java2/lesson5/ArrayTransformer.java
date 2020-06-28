package ru.gb.java2.lesson5;

class ArrayTransformer implements Runnable {
    private float[] arr;
    private ValueTransformer transformer;

    ArrayTransformer(float[] arr, ValueTransformer transformer) {
        this.arr = arr;
        this.transformer = transformer;
    }

    @Override
    synchronized public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = transformer.transform(arr[i], i);
        }
    }
}
