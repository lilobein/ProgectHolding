package com.mainwindow;

import com.data.DatabaseConnection;
import com.data.QueryResultWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetricDAO {
    public static void save(Metric metric) throws SQLException {
        String query = "INSERT INTO metrics (metric_name, value, currency_id, importance_constant, " +
                "period_start, period_end, enterprise_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = DatabaseConnection.getConnection()
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, metric.getMetric_name());
            statement.setDouble(2, metric.getValue());
            statement.setInt(3, metric.getCurrency_id());
            statement.setByte(4, metric.getImportance_constant());
            statement.setDate(5, Date.valueOf(metric.getPeriod_start()));
            statement.setDate(6, Date.valueOf(metric.getPeriod_end()));
            statement.setInt(7, metric.getEnterprise_id());

            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    metric.setId(rs.getInt(1));
                }
            }
        }
    }


    public static void update(Metric metric) throws SQLException {
        String query = "UPDATE metrics SET metric_name=?, value=?, currency_id=?, " +
                "importance_constant=?, period_start=?, period_end=?, enterprise_id=? WHERE id=?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query)) {
            statement.setString(1, metric.getMetric_name());
            statement.setDouble(2, metric.getValue());
            statement.setInt(3, metric.getCurrency_id());
            statement.setByte(4, metric.getImportance_constant());
            statement.setDate(5, Date.valueOf(metric.getPeriod_start()));
            statement.setDate(6, Date.valueOf(metric.getPeriod_end()));
            statement.setInt(7, metric.getEnterprise_id());
            statement.setInt(8, metric.getId());
            statement.executeUpdate();
        }

    }

    public static void delete(Metric metric) throws SQLException {
        String query = "DELETE FROM metrics WHERE id=?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query)) {
            statement.setInt(1, metric.getId());
            statement.executeUpdate();
        }
    }


    public static QueryResultWrapper findAll() throws SQLException {
        String query = "SELECT * FROM metrics";
        QueryResultWrapper wrapper = QueryResultWrapper.getInstance();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            wrapper.wrap(processResultSet(rs));
        } catch (SQLException e) {
            wrapper.wrap(null);
            throw e;
        }
        return wrapper;
    }

    public static QueryResultWrapper findByEnterpriseId(int enterpriseId) throws SQLException {
        String query = "SELECT * FROM metrics WHERE enterprise_id = ?";
        QueryResultWrapper wrapper = QueryResultWrapper.getInstance();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, enterpriseId);

            try (ResultSet rs = stmt.executeQuery()) {
                wrapper.wrap(processResultSet(rs));
            }
        } catch (SQLException e) {
            wrapper.wrap(null);
            throw e;
        }
        return wrapper;
    }

    private static List<Metric> processResultSet(ResultSet rs) throws SQLException {
        List<Metric> metrics = new ArrayList<>();
        while (rs.next()) {
            Metric metric = new Metric(
                    rs.getString("metric_name"),
                    rs.getDouble("value"),
                    rs.getInt("currency_id"),
                    rs.getByte("importance_constant"),
                    rs.getDate("period_start").toLocalDate(),
                    rs.getDate("period_end").toLocalDate(),
                    rs.getInt("enterprise_id")
            );
            metric.setId(rs.getInt("id"));
            metrics.add(metric);
        }
        return metrics;
    }
}