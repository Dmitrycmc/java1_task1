package ru.gb.java2.lesson5;

class ArrayTransformer implements Runnable {
    private float[] arr;
    int indexShift;

    ArrayTransformer(float[] arr, int indexShift) {
        this.arr = arr;
        this.indexShift = indexShift;
    }

    @Override
    synchronized public void run() {
        for (int i = 0; i < arr.length; i++) {
            int shiftedI = indexShift + i;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + shiftedI / 5) * Math.cos(0.2f + shiftedI / 5) * Math.cos(0.4f + shiftedI / 2));
        }
    }
}
