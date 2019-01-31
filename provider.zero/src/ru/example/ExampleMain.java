package ru.example;

import ru.example.annotation.Order;
import ru.example.provider.ExProvider;
import ru.iocmodule.Loader;
import ru.iocmodule.PathLayer;

import java.util.Comparator;
import java.util.List;

public class ExampleMain {

    public static void main(String[] args) {

        print();

    }

    private static void print() {
        Class<ExProvider> clazz = ExProvider.class;
        Loader<ExProvider> providerLoader = new Loader<>();

        List<PathLayer> paths = List.of(//todo use you path
                PathLayer.of(
                        "./out/production/provider.next",
                        "ru.example.provider.next"),
                PathLayer.of(
                        "./out/production/provider.first",
                        "ru.example.provider.first"));


        System.out.println("*** With Sort ***");
        List<ExProvider> load = providerLoader.load(paths, clazz,
                Comparator.comparingInt(ExampleMain::getOrder));
        load.forEach(provider ->
                System.out.println(provider.apply()));

        System.out.println("*** Without Sort ***");
        List<ExProvider> load2 = providerLoader.load(paths, clazz);
        load2.forEach(provider ->
                System.out.println(provider.apply()));
    }

    private static int getOrder(ExProvider ep) {
        int orderI = Integer.MIN_VALUE;
        Order annotation = ep.getClass().getAnnotation(Order.class);
        if (annotation != null) {
            orderI = annotation.value();
        }
        return orderI;
    }

}
