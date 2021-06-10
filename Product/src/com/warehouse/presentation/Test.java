package com.warehouse.presentation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        Date endDate = simpleDateFormat.parse("11/6/2021");
        Date startDate = new Date();
        String start = simpleDateFormat.format(startDate);
        Date start1 = simpleDateFormat.parse(start);

        long endValue = endDate.getTime();
        long startValue = start1.getTime();
        long tmp = Math.abs(endValue - startValue);
        long soNgayConLai = tmp / (24 * 60 * 60 * 1000);
        System.out.println(soNgayConLai);
    }
}
