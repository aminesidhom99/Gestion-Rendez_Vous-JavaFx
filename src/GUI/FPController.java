package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;

public class FPController {
    
    @FXML
    private PasswordField emailField;
    

    @FXML
    public void forgetPassword(ActionEvent event) {
        String username = emailField.getText();
        
        
        // Check if username and password are valid
        if (username.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a email ");
            alert.showAndWait();
            return;
        }
        
       
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("email sent successfully");
        alert.showAndWait();
        
        // Clear the fields
        emailField.setText("");
        
    }  
    
}
