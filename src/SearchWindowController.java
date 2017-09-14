import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SearchWindowController {

    @FXML   private javafx.scene.control.Button submitButton;
    @FXML   private javafx.scene.control.TextField selectedDirName;
    @FXML   private javafx.scene.control.TextArea extTextArea;
    @FXML   private javafx.scene.control.TextArea searchTextArea;
    private Stage stage;

    @FXML
    private void onSubmit(){
        try {
            stage = (Stage)submitButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MainWindow.fxml"));
            Scene scene = new Scene(loader.load());

            MainWindowController controller = loader.getController();
            setMainWindowParams(controller);

            stage.setTitle("Main window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setMainWindowParams(MainWindowController controller ) {
        controller.setInputDirPath(selectedDirName.getText());
        controller.setInputExtentions(extTextArea.getText());
        controller.setInputSearchText(searchTextArea.getText());
    }

    @FXML
    public void openDialogButton(ActionEvent actionEvent) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Выберите директорию");
        File userDir = chooser.showDialog(stage);
        if (userDir != null) {
            selectedDirName.setText(userDir.getAbsolutePath());
        } else  {
            selectedDirName.setText(null);
        }
    }
}
