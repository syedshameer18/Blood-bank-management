package doa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.BloodStock;

public class StockDAO {

    // 🔹 Increment stock by 1
    public boolean incrementStock(String bloodGroup) {
        String sql = "UPDATE blood_stock " +
                     "SET units_available = units_available + 1 " +
                     "WHERE TRIM(UPPER(blood_group)) = TRIM(UPPER(?))";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bloodGroup);
            int rows = stmt.executeUpdate();

            System.out.println("Increment stock for " + bloodGroup + " → Rows affected = " + rows);

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 🔹 Decrement stock by given units
    public boolean decrementStock(String bloodGroup, int units) {
        String sql = "UPDATE blood_stock " +
                     "SET units_available = units_available - ? " +
                     "WHERE TRIM(UPPER(blood_group)) = TRIM(UPPER(?)) " +
                     "AND units_available >= ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, units);
            stmt.setString(2, bloodGroup);
            stmt.setInt(3, units);

            int rows = stmt.executeUpdate();

            System.out.println("Decrement stock for " + bloodGroup + " by " + units + " → Rows affected = " + rows);

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 🔹 Get all stock records
    public List<BloodStock> getAllStock() {
        List<BloodStock> stocks = new ArrayList<>();
        String sql = "SELECT blood_group, units_available FROM blood_stock";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BloodStock stock = new BloodStock();
                stock.setBlood_group(rs.getString("blood_group").trim()); // just in case spaces exist
                stock.setUnits_available(rs.getInt("units_available"));
                stocks.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stocks;
    }
}