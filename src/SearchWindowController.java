import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SearchWindowController {
    
    @FXML   private javafx.scene.control.TextField selectedDirName;
    @FXML   private javafx.scene.control.TextArea extTextArea;
    @FXML   private javafx.scene.control.TextArea searchTextArea;
    private Stage stage;

    @FXML
    private void onSubmit(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MainWindow.fxml"));
            Scene scene = new Scene(loader.load());

            MainWindowController controller = loader.getController();
            setMainWindowParams(controller);
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
    public void onOpenDialog(ActionEvent actionEvent) {
        Node source = (Node)actionEvent.getSource();
        this.stage = (Stage)source.getScene().getWindow();

        DirectoryChooser chooser = new DirectoryChooser();
        File userDir = chooser.showDialog(stage);
        if (userDir != null) {
            selectedDirName.setText(userDir.getAbsolutePath());
        }
    }
}
