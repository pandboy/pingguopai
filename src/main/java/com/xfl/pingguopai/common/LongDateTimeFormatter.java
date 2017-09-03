package com.xfl.pingguopai.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class LongDateTimeFormatter implements Formatter<Date> {
    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isBlank(text))
            return null;

        long milliseconds = Long.valueOf(text);
        Date date = new Date(milliseconds);
        return date;
    }

    @Override
    public String print(Date object, Locale locale) {
        if (null == object)
            return null;

        return object.getTime() + "";
    }

    public static void main(String[] args) {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addFormatter(new LongDateTimeFormatter());

        long milliseconds = System.currentTimeMillis();
        Object obj = conversionService.convert(milliseconds, Date.class);
        System.out.println(obj);
    }

}
