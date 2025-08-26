package com.example.student_form;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InsertController {


    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField addressField;
    @FXML private Label statusLabel;

    @FXML
    private void handleInsert() {
        String sql = "INSERT INTO Student (ID, Name, Age, Adress) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(idField.getText()));
            stmt.setString(2, nameField.getText());
            stmt.setInt(3, Integer.parseInt(ageField.getText()));
            stmt.setString(4, addressField.getText());

            int rows = stmt.executeUpdate();
            statusLabel.setText("✅ Inserted " + rows + " row(s)");
            clearFields();

        } catch (SQLException | NumberFormatException e) {
            statusLabel.setText("❌ Insert failed: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        String sql = "DELETE FROM Student WHERE ID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(idField.getText()));

            int rows = stmt.executeUpdate();
            statusLabel.setText("🗑️ Deleted " + rows + " row(s)");
            clearFields();

        } catch (SQLException | NumberFormatException e) {
            statusLabel.setText("❌ Delete failed: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearch() {
        String idText = idField.getText();
        if (idText == null || idText.trim().isEmpty()) {
            statusLabel.setText("⚠️ Please enter a valid Student ID to search.");
            clearFields();
            return;
        }

        String sql = "SELECT * FROM Student WHERE ID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(idText));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("Name"));
                ageField.setText(String.valueOf(rs.getInt("Age")));
                addressField.setText(rs.getString("Adress"));
                statusLabel.setText("🔍 Student found.");
            } else {
                statusLabel.setText("⚠️ No student found with that ID.");
                clearFields();
            }

        } catch (SQLException | NumberFormatException e) {
            statusLabel.setText("❌ Search failed: " + e.getMessage());
        }
    }


    private void clearFields() {
        nameField.clear();
        ageField.clear();
        addressField.clear();
    }
}