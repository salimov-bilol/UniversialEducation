package com.example.demo.entity;

import lombok.Data;

import java.util.IntSummaryStatistics;

@Data
public class Statistic {

    private long sum;
    private long count;
    private double average;
    private int min;
    private int max;

    public Statistic(IntSummaryStatistics intSummaryStatistics){
        this.sum = intSummaryStatistics.getSum();
        this.count = intSummaryStatistics.getCount();
        this.min = intSummaryStatistics.getMin();
        this.max = intSummaryStatistics.getMax();
        this.average = intSummaryStatistics.getAverage();
    }
}
