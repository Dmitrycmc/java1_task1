package ru.gb.java2.lesson5;

class ArrayTransformer implements Runnable {
    private float[] arr;
    private int startIndex;
    private int length;
    private ValueTransformer transformer;

    ArrayTransformer(float[] arr, int startIndex, int length, ValueTransformer transformer) {
        this.arr = arr;
        this.startIndex = startIndex;
        this.length = length;
        this.transformer = transformer;
    }

    @Override
    synchronized public void run() {
        for (int i = startIndex; i < startIndex + length; i++) {
            arr[i] = transformer.transform(arr[i], i);
        }
    }
}
