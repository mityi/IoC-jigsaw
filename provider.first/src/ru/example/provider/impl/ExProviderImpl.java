package ru.example.provider.impl;

import ru.example.provider.ExProvider;
import ru.example.annotation.Order;

@Order(3)
public class ExProviderImpl implements ExProvider {
    @Override
    public PublicClass apply() {
        return new PublicClass();
    }
}
