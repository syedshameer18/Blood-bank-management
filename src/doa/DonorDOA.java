package doa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Patient;
import db.DBConnection;
import model.Donor;

public class DonorDOA {   // ✅ fixed class name

    // 🔹 Add donor
    public boolean addDonor(Donor donor) {
        boolean result = false;
        String sql = "INSERT INTO donors (fullname, age, gender, blood_group, phone, address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, donor.getFullname());
            pst.setInt(2, donor.getAge());
            pst.setString(3, donor.getGender());
            pst.setString(4, donor.getBlood_group());
            pst.setString(5, donor.getPhone());
            pst.setString(6, donor.getAddress());

            int rows = pst.executeUpdate();
            if (rows > 0) {
            	StockDAO stockDAO=new StockDAO();
            	stockDAO.incrementStock(donor.getBlood_group());
            	result = true;
                System.out.println("✅ Donor added successfully: " + donor.getFullname());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean insertPatient(Patient p) {
        try (Connection conn = DBConnection.getConnection()) {

            String insert = "INSERT INTO patients(fullname, age, gender, blood_group_requires, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setString(1, p.getFullname());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getBlood_group_requires());
            ps.setString(5, p.getPhone());
            ps.setString(6, p.getAddress());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                // ✅ decrease stock
                StockDAO stockDAO = new StockDAO();
                boolean updated = stockDAO.decrementStock(p.getBlood_group_requires(), 1);

                if (updated) {
                    System.out.println("✅ Patient added & stock reduced: " + p.getBlood_group_requires());
                    return true;
                } else {
                    System.out.println("⚠️ Patient added but stock not updated!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 🔹 Get all donors
    public List<Donor> getAllDonors() {
        List<Donor> donors = new ArrayList<>();
        String sql = "SELECT * FROM donors";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Donor d = new Donor();
                d.setdonor_id(rs.getInt("donor_id")); // ✅ correct setter
                d.setFullname(rs.getString("fullname"));
                d.setBlood_group(rs.getString("blood_group"));
                d.setAge(rs.getInt("age"));
                d.setGender(rs.getString("gender"));
                d.setPhone(rs.getString("phone"));
                d.setAddress(rs.getString("address"));
                donors.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donors;
    }

    // 🔹 Search donors
    public List<Donor> searchDonors(String keyword) {
        List<Donor> donors = new ArrayList<>();
        String sql = "SELECT * FROM donors WHERE fullname LIKE ? OR blood_group LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Donor d = new Donor();
                    d.setdonor_id(rs.getInt("donor_id"));
                    d.setFullname(rs.getString("fullname"));
                    d.setBlood_group(rs.getString("blood_group"));
                    d.setAge(rs.getInt("age"));
                    d.setGender(rs.getString("gender"));
                    d.setPhone(rs.getString("phone"));
                    d.setAddress(rs.getString("address"));
                    donors.add(d);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return donors;
    }

    // 🔹 Delete donor by Phone
    public boolean deleteDonorByPhone(String phone) {
        String sql = "DELETE FROM donors WHERE phone = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, phone.trim());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Delete donor by ID
    public boolean deleteDonorById(int donorId) {
        String sql = "DELETE FROM donors WHERE donor_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, donorId);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}