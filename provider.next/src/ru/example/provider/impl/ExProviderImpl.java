package ru.example.provider.impl;

import ru.example.provider.ExProvider;
import ru.example.annotation.Order;

@Order(5)
public class ExProviderImpl implements ExProvider {
    private final String s;

    public ExProviderImpl(String s) {
        this.s = s;
    }
    //you have to provide public no-arg constructor
    public ExProviderImpl() {
        this.s = "";
    }

    @Override
    public PublicInnerClass apply() {
        return new PublicInnerClass();
    }


    public class PublicInnerClass {
        @Override
        public String toString() {
            return "PublicInnerClass{}";
        }
    }

}
