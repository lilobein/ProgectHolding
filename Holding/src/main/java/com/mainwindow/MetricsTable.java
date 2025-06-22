package com.mainwindow;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.login.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MetricsTable {
    private ObservableList<Metric> tableData;
    private User manager;

    public MetricsTable(User manager) {
        this.manager = manager;
        this.tableData = FXCollections.observableArrayList();
        refreshData();
    }

    public void save(String name, double value, byte importance, int currency, LocalDate start, LocalDate end) throws SQLException {
        Metric metric = new Metric(name, value, currency, importance, start, end, manager.getEnterpriseId());
        MetricDAO.save(metric);

    }

    public void update(Metric metric) throws SQLException {
        MetricDAO.update(metric);
        refreshData();
    }

    public void delete(Metric metric) throws SQLException {
        MetricDAO.delete(metric);
    }

    public void refreshData() {
        try {
            List<Metric> metrics = MetricDAO.findByEnterpriseId(manager.getEnterpriseId());
            tableData.setAll(metrics);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void letsEditMetric(Metric metric, String name, double value, byte importance, int currency, LocalDate start, LocalDate end) throws SQLException {
        metric.setMetric_name(name);
        metric.setValue(value);
        metric.setCurrency_id(currency);
        metric.setImportance_constant(importance);
        metric.setPeriod_start(start);
        metric.setPeriod_end(end);
        update(metric);
    }


    public ObservableList<Metric> getTableData() {
        return tableData;
    }

    public User getManager() {
        return manager;
    }
}