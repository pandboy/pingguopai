package com.xfl.pingguopai.common;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bl02656 on 2015-06-25.
 */
public class LongDateTimeFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<LongDateTimeFormat> {
    private final Set<Class<?>> fieldTypes;
    private final LongDateTimeFormatter formatter;

    public LongDateTimeFormatAnnotationFormatterFactory() {
        Set<Class<?>> set = new HashSet<Class<?>>();
        set.add(Date.class);
        this.fieldTypes = set;
        this.formatter = new LongDateTimeFormatter();
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return fieldTypes;
    }

    @Override
    public Printer<?> getPrinter(LongDateTimeFormat annotation, Class<?> fieldType) {
        return formatter;
    }

    @Override
    public Parser<?> getParser(LongDateTimeFormat annotation, Class<?> fieldType) {
        return formatter;
    }
}
