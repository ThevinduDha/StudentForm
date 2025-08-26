module com.example.student_form {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.student_form to javafx.fxml;
    exports com.example.student_form;
}