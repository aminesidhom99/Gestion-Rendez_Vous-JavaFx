package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;

public class RPController {
    
    @FXML
    private PasswordField usernameField;
    
    private PasswordField passwordField;

    public void resetPassword(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        // Check if username and password are valid
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a username and a new password");
            alert.showAndWait();
            return;
        }
        
       
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Password reset successfully");
        alert.showAndWait();
        
        // Clear the fields
        usernameField.setText("");
        passwordField.setText("");
    }
}
