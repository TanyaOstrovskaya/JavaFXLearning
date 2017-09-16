import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SearchWindowController {
    
    @FXML   private javafx.scene.control.TextField selectedDirName;
    @FXML   private javafx.scene.control.TextArea extensionsTextArea;
    @FXML   private javafx.scene.control.TextArea searchTextArea;
    @FXML   private javafx.scene.layout.GridPane panelId;
    @FXML   private javafx.scene.control.Button dialogWindowButton;
    @FXML   private javafx.scene.control.Button submitButton;
    private Stage stage;


    public void initialize() {
        searchTextArea.prefHeightProperty().bind(panelId.heightProperty());
        extensionsTextArea.prefHeightProperty().bind(panelId.heightProperty());
        selectedDirName.prefWidthProperty().bind(panelId.widthProperty());
        dialogWindowButton.prefWidthProperty().bind(panelId.widthProperty());
        submitButton.prefWidthProperty().bind(panelId.widthProperty());
    }


    @FXML
    private void onSubmit(){
        try {
            if (!validateUserInput())
                return;

            /* else - change scene to main window */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MainWindow.fxml"));
            Scene scene = new Scene(loader.load());
            MainWindowController controller = loader.getController();
            setMainWindowControllerParams(controller);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean validateUserInput() {
        return validateSelectedDirName() &&  validateExtentionText() &&  validateSearchText() ;
    }
    private boolean validateSearchText() {
        return true;
    }
    private boolean validateExtentionText() {
        char[] chars = this.extensionsTextArea.getText().toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c) || !Character.isSpaceChar(c)) {
                handleValidationError("Расширения файлов указаны неверно.\nВведите расширения еще раз");
                return false;
            }
        }
        return true;
    }
    private boolean validateSelectedDirName() {
        String dirName = this.selectedDirName.getText();
        File file = new File(dirName);
        if (file.exists() && file.isDirectory()) {
            return true;
        } else {
            handleValidationError("Директория указана неверно.\nУкажите путь еще раз.");
            return false;
        }
    }

    private void setMainWindowControllerParams(MainWindowController controller) {
        controller.setInputDirPath(selectedDirName.getText());
        controller.setInputExtentions(extensionsTextArea.getText().split("\\s+"));
        controller.setInputSearchText(searchTextArea.getText());
    }

    @FXML
    public void onOpenDialog(ActionEvent actionEvent) {
        Node source = (Node)actionEvent.getSource();
        this.stage = (Stage)source.getScene().getWindow();

        DirectoryChooser chooser = new DirectoryChooser();
        File userDir = chooser.showDialog(stage);
        if (userDir != null) {
            selectedDirName.setText(userDir.getAbsolutePath());
        }
    }

    private void handleValidationError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Ошибка:");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
