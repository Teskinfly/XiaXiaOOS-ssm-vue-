package com.teskinfly.utils.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
//        System.out.println("I am in");
        if (source == null) {
//            System.out.println("empty");
            throw new NullPointerException("参数为空");
        }
        try {
//            System.out.println("begin");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(source);
//            System.out.println("end");
            return date;
        } catch (ParseException e) {
            throw new RuntimeException("转换错误");
        }
    }
}
