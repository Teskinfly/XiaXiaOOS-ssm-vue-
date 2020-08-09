package com.teskinfly.pojo.charts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ChartsOption {
    Title title;
    Legend legend;
    @JsonProperty
    Axis xAxis;
    @JsonProperty
    Axis yAxis;
    List<Series> series;

    @Setter
    @Getter
    public class Title {
        String text;

        public Title(String text) {
            this.text = text;
        }
    }

    @Setter
    @Getter
    public class Legend {
        List<String> data;

        public Legend(List<String> data) {
            this.data = data;
        }
    }

    @Setter
    @Getter
    public class Axis {
        List<String> data;

        public Axis(List<String> data) {
            this.data = data;
        }
    }

    @Setter
    @Getter
    public class Series {
        String name;
        String type;
        List<String> data;

        public Series(String name, String type, List<String> data) {
            this.name = name;
            this.type = type;
            this.data = data;
        }
    }

    @Override
    public String toString() {
        return "ChartsOption{" +
                "title=" + title +
                ", legend=" + legend +
                ", xAxis=" + xAxis +
                ", yAxis=" + yAxis +
                ", series=" + series +
                '}';
    }

    public Title getTitle() {
        return title;
    }

    public Legend getLegend() {
        return legend;
    }

    @JsonIgnore
    public Axis getXAxis() {
        return xAxis;
    }

    @JsonIgnore
    public Axis getYAxis() {
        return yAxis;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    @JsonIgnore
    public void setXAxis(Axis xAxis) {
        this.xAxis = xAxis;
    }

    @JsonIgnore
    public void setYAxis(Axis yAxis) {
        this.yAxis = yAxis;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }
}
