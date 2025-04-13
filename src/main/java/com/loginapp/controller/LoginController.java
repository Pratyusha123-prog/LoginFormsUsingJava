package com.loginapp.controller;

import com.loginapp.dao.UserDAO;
import com.loginapp.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginController {
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private Label messageLabel;
    
    private UserDAO userDAO = new UserDAO();
    
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please enter both username and password");
            return;
        }
        
        User user = userDAO.loginUser(username, password);
        
        if (user != null) {
            messageLabel.setText("Login successful! Welcome " + user.getUsername());
            messageLabel.setStyle("-fx-text-fill: green;");
        } else {
            messageLabel.setText("Invalid username or password");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            messageLabel.setText("Please fill in all fields");
            return;
        }
        
        User newUser = new User(username, password, email);
        
        if (userDAO.registerUser(newUser)) {
            messageLabel.setText("Registration successful!");
            messageLabel.setStyle("-fx-text-fill: green;");
        } else {
            messageLabel.setText("Registration failed. Please try again.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
} 