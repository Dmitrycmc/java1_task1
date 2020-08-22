package ru.geekbrains.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class TestClass {
    public static void start(String className) {
        try {
            start(Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Unknown className: " + className);
        }
    }

    public static void start(Class c) {
        Method[] methods = c.getDeclaredMethods();
        HashMap<Integer, ArrayList<Method>> testMethods = new HashMap<>();
        ArrayList<Method> beforeSuiteMethods = new ArrayList<>();
        ArrayList<Method> afterSuiteMethods = new ArrayList<>();

        for (Method method: methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeSuiteMethods.add(method);
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                afterSuiteMethods.add(method);
            }
            if (method.getAnnotation(Test.class) != null) {
                int priority = method.getAnnotation(Test.class).priority();
                if (priority < 1 || priority > 10) {
                    throw new RuntimeException(method.getName() + " has invalid priority: " + priority);
                }

                if (testMethods.get(priority) == null) {
                    testMethods.put(priority, new ArrayList<>());
                }
                testMethods.get(priority).add(method);
            }
        }

        if (beforeSuiteMethods.size() > 1) {
            throw new RuntimeException("More than 1 @BeforeSuite method");
        }

        if (afterSuiteMethods.size() > 1) {
            throw new RuntimeException("More than 1 @AfterSuite method");
        }

        if (beforeSuiteMethods.size() == 1) {
            beforeSuiteMethods.get(0).setAccessible(true);
            try {
                beforeSuiteMethods.get(0).invoke(c.newInstance());
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        for (int priority = 10; priority >= 1; priority--) {
            ArrayList<Method> currentMethods = testMethods.get(priority);
            if (currentMethods != null) {
                for (Method method: currentMethods) {
                    method.setAccessible(true);
                    try {
                        method.invoke(c.newInstance());
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (afterSuiteMethods.size() == 1) {
            afterSuiteMethods.get(0).setAccessible(true);
            try {
                afterSuiteMethods.get(0).invoke(c.newInstance());
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
