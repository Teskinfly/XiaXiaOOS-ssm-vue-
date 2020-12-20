package com.teskinfly.pojo.charts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class DateAndIncome {
    String date;
    Integer income;

    public DateAndIncome(String date, Integer income) {
        this.date = date;
        this.income = income;
    }

    @Override
    public String toString() {
        return "DateAndIncome{" +
                "date='" + date + '\'' +
                ", income=" + income +
                '}';
    }
}
