import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainWindowController {

    @FXML private javafx.scene.control.Label dirNameLabel;

    private String inputDirPath;
    private String inputExtentions;
    private String inputSearchText;

    public MainWindowController() {
    }

    public MainWindowController(String inputPath, String inputExtentions, String inputSearchText) {
        this.inputDirPath = inputPath;
        this.inputExtentions = inputExtentions;
        this.inputSearchText = inputSearchText;
    }

    public String getInputDirPath() {
        return inputDirPath;
    }

    public void setInputDirPath(String inputDirPath) {
        this.inputDirPath = inputDirPath;
        dirNameLabel.setText("Найденные файлы в директории" + inputDirPath);
    }

    public String getInputExtentions() {
        return inputExtentions;
    }

    public void setInputExtentions(String inputExtentions) {
        this.inputExtentions = inputExtentions;
    }

    public String getInputSearchText() {
        return inputSearchText;
    }

    public void setInputSearchText(String inputSearchText) {
        this.inputSearchText = inputSearchText;
    }

    public void onClick(ActionEvent actionEvent) {
    }
}
