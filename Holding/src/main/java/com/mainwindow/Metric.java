package com.mainwindow;

import java.time.LocalDate;

public class Metric {
    private int id;  // int(11)
    private String metric_name;  // varchar(255)
    private double value;  // decimal(19,4) - представлено как double для Java
    private int currency_id;  // int(11)
    private byte importance_constant;  // tinyint(4)
    private LocalDate period_start;  // date
    private LocalDate period_end;  // date
    private int enterprise_id;  // int(11)

    public Metric(String metric_name, double value, int currency_id,
                     byte importance_constant, LocalDate period_start,
                     LocalDate period_end, int enterprise_id) {
        this.metric_name = metric_name;
        this.value = value;
        this.currency_id = currency_id;
        this.importance_constant = importance_constant;
        this.period_start = period_start;
        this.period_end = period_end;
        this.enterprise_id = enterprise_id;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getMetric_name() {
        return metric_name;
    }

    public double getValue() {
        return value;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public byte getImportance_constant() {
        return importance_constant;
    }

    public LocalDate getPeriod_start() {
        return period_start;
    }

    public LocalDate getPeriod_end() {
        return period_end;
    }

    public int getEnterprise_id() {
        return enterprise_id;
    }

    // Сеттеры
    public void setId(int id) {
        this.id = id;
    }

    public void setMetric_name(String metric_name) {
        this.metric_name = metric_name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }

    public void setImportance_constant(byte importance_constant) {
        this.importance_constant = importance_constant;
    }

    public void setPeriod_start(LocalDate period_start) {
        this.period_start = period_start;
    }

    public void setPeriod_end(LocalDate period_end) {
        this.period_end = period_end;
    }

    public void setEnterprise_id(int enterprise_id) {
        this.enterprise_id = enterprise_id;
    }
}