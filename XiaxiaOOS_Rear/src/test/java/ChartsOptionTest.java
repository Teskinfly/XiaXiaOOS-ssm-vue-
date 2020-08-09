import com.teskinfly.pojo.DataReturn;
import com.teskinfly.pojo.ReturnCode;
import com.teskinfly.pojo.charts.ChartsOption;
import com.teskinfly.service.impl.FoodService;
import com.teskinfly.utils.GenerateChartsOption;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import com.teskinfly.pojo.charts.ChartsOption.*;
public class ChartsOptionTest {
    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        System.out.println(format);
//        method1();
        method2();
    }

    private static void method2() {
    }

    private static void method1() {
        ChartsOption chartsOption = new ChartsOption();
        chartsOption.setTitle(chartsOption.new Title("echarts 入门案例"));
        List<String> list = new ArrayList<>();
        list.add("销量");
        chartsOption.setLegend(chartsOption.new Legend(list));
        List<String> list1 = new ArrayList<>();
        list1.add("衬衫");
        list1.add("羊毛衫");
        list1.add("雪纺衫");
        list1.add("裤子");
        list1.add("高跟鞋");
        list1.add("袜子");
        chartsOption.setXAxis(chartsOption.new Axis(list1));
        chartsOption.setYAxis(chartsOption.new Axis(null));
        List<String> list2 = new ArrayList<>();
        list2.add("5");
        list2.add("10");
        list2.add("15");
        list2.add("10");
        list2.add("30");
        list2.add("100");
        List<ChartsOption.Series> list4 = new ArrayList<>();
        list4.add(chartsOption.new Series("销量", "bar", list2));
        chartsOption.setSeries(list4);
        ArrayList<Object> list3 = new ArrayList<>();
        list3.add(chartsOption);
        DataReturn dataReturn = new DataReturn(list3, ReturnCode.SUCCESS, null);
    }
}
