module com.assignment.tictactoe.service {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.assignment.tictactoe.service to javafx.fxml;
    exports com.assignment.tictactoe.service;
    exports com.assignment.tictactoe.controller;
    opens com.assignment.tictactoe.controller to javafx.fxml;
    exports com.assignment.tictactoe;
    opens com.assignment.tictactoe to javafx.fxml;
}