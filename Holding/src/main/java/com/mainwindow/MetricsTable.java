package com.mainwindow;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.data.QueryResultWrapper;
import com.login.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MetricsTable {
    private ObservableList<Metric> tableData;
    private User user;

    public MetricsTable(User manager) {
        this.user = manager;
        this.tableData = FXCollections.observableArrayList();
        refreshData();
    }

    public void save(String name, double value, byte importance, int currency, LocalDate start, LocalDate end) throws SQLException {
        Metric metric = new Metric(name, value, currency, importance, start, end, user.getEnterpriseId());
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
            if (user.isManager()){
                QueryResultWrapper wrapper = MetricDAO.findByEnterpriseId(user.getEnterpriseId());
                List<Metric> metrics = (List<Metric>) wrapper.unwrap();
                tableData.setAll(metrics);
            } else if (user.isAnalyst()){
                QueryResultWrapper wrapper = MetricDAO.findAll();
                List<Metric> metrics = (List<Metric>) wrapper.unwrap();
                tableData.setAll(metrics);
            }
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

    public User getUser() {
        return user;
    }
}