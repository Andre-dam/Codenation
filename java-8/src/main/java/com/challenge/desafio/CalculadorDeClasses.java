package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object object) {
        return this.somarAtributos((Class) Somar.class, object);
    }

    @Override
    public BigDecimal subtrair(Object object) {
        return this.somarAtributos((Class) Subtrair.class, object);
    }

    @Override
    public BigDecimal totalizar(Object object) {
        return this.somar(object).subtract(this.subtrair(object));
    }

    private BigDecimal somarAtributos(Class<Annotation> annotationClass, Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotationClass))
                .filter(field -> field.getType().equals(BigDecimal.class))
                .map(field -> this.extrairAtributo(field, object))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal extrairAtributo(Field field, Object object) {
        try {
            field.setAccessible(true);
            return (BigDecimal) field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Wrapped checked exception", e);
        }
    }
}
