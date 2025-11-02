package doa;

import db.DBConnection;
import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
	  // ✅ Insert patient and decrement stock
    public boolean insertPatient(Patient p) {
        String sql = "INSERT INTO patients (fullname, age, gender, blood_group_requires, phone, address) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, p.getFullname());
            pst.setInt(2, p.getAge());
            pst.setString(3, p.getGender());
            pst.setString(4, p.getBlood_group_requires());
            pst.setString(5, p.getPhone());
            pst.setString(6, p.getAddress());

            int rows = pst.executeUpdate();

            if (rows > 0) {
                // 🔹 Also decrement stock after issuing blood
                StockDAO stockDAO = new StockDAO();
                return stockDAO.decrementStock(p.getBlood_group_requires(), 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    // 🔹 Get all patients
    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Patient p = new Patient();
                p.setPatient_id(rs.getInt("patient_id"));
                p.setFullname(rs.getString("fullname"));
                p.setAge(rs.getInt("age"));
                p.setGender(rs.getString("gender"));
                p.setBlood_group_requires(rs.getString("blood_group_requires"));
                p.setPhone(rs.getString("phone"));
                p.setAddress(rs.getString("address"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔹 Search patients by ID or Name
    public List<Patient> searchPatients(String keyword) {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE patient_id LIKE ? OR fullname LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setPatient_id(rs.getInt("patient_id"));
                p.setFullname(rs.getString("fullname"));
                p.setAge(rs.getInt("age"));
                p.setGender(rs.getString("gender"));
                p.setBlood_group_requires(rs.getString("blood_group_requires"));
                p.setPhone(rs.getString("phone"));
                p.setAddress(rs.getString("address"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔹 Delete patient
    public boolean deletePatient(int patientId) {
        String sql = "DELETE FROM patients WHERE patient_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, patientId);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}