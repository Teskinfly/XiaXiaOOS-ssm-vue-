package com.teskinfly.service.impl;

import com.teskinfly.dao.IDataAnalyzeDao;
import com.teskinfly.domain.Food;
import com.teskinfly.domain.Orders;
import com.teskinfly.pojo.charts.ChartsOption;
import com.teskinfly.pojo.charts.DateAndIncome;
import com.teskinfly.pojo.charts.FoodAndAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataAnalyzeService {
    @Autowired
    IDataAnalyzeDao dataAnalyzeDao;
    public ChartsOption getSaleData(String begin, String end) {
        ChartsOption chartsOption = new ChartsOption();
        chartsOption.setTitle(chartsOption.new Title("各菜品的总销售量"));
        chartsOption.setLegend(chartsOption.new Legend(new ArrayList<String>() {
            {
                add("销量");
            }
        }));
//        List<Food> all = foodService.findAll();

        List<FoodAndAmount> foodAndAmounts = null;
        if (begin == null) {
            foodAndAmounts = dataAnalyzeDao.getFoodAndAmounts();
        }
        else {
            foodAndAmounts = dataAnalyzeDao.getFoodAndAmountsByDate(begin,end);
        }
        List<String> axisData = new ArrayList<>();
        List<String> seriesData = new ArrayList<>();
        for (FoodAndAmount faa: foodAndAmounts) {
            axisData.add(faa.getFName());
            seriesData.add(faa.getAmount()+"");
        }
//        for (Food food : all) {
//            axisData.add(food.getFName());
//            seriesData.add(food.getFAmount() + "");
//        }
        chartsOption.setXAxis(chartsOption.new Axis(axisData));
        chartsOption.setYAxis(chartsOption.new Axis(null));
        chartsOption.setSeries(new ArrayList() {{
            add(chartsOption.new Series("销量", "bar", seriesData));
        }});
        return chartsOption;
    }

    public ChartsOption getIncomeData(String begin, String end) {
        List<DateAndIncome> dateAndIncome = dataAnalyzeDao.getDateAndIncome(begin, end);
//        Map<String, Double> dateToMoney = new TreeMap<>();
//        for (Orders orders : allOrders) {
//            String date = orders.getODate();
//            if (dateToMoney.containsKey(date))
//                dateToMoney.put(date, dateToMoney.get(date) + Double.valueOf(orders.getOPrice()));
//            else
//                dateToMoney.put(orders.getODate(), Double.valueOf(orders.getOPrice()));
//        }
        ChartsOption chartsOption = new ChartsOption();
        chartsOption.setTitle(chartsOption.new Title("收入情况"));
        chartsOption.setLegend(chartsOption.new Legend(new ArrayList() {{
            add("收入");
        }}));
        List<String> axisList = new ArrayList<>();
        List<String> seriesList = new ArrayList<>();
//        for (Map.Entry<String, Double> entry : dateToMoney.entrySet()) {
//            axisList.add(entry.getKey() + "");
//            seriesList.add(entry.getValue() + "");
//        }
        for (DateAndIncome dai: dateAndIncome) {
            axisList.add(dai.getDate());
            seriesList.add(dai.getIncome()+"");
        }
        chartsOption.setYAxis(chartsOption.new Axis(null));
        chartsOption.setXAxis(chartsOption.new Axis(axisList));
        ChartsOption.Series series = chartsOption.new Series("收入", "bar", seriesList);
        chartsOption.setSeries(new ArrayList() {{
            add(series);
        }});
        return chartsOption;

    }
}
