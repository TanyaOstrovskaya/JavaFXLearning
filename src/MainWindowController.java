import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.CheckBoxTreeCell;
import java.io.File;
import java.util.Arrays;

public class MainWindowController {

    @FXML   private javafx.scene.control.Label dirNameLabel;
    @FXML   private javafx.scene.control.TreeView treeView;
    @FXML   private javafx.scene.control.TextArea fileContentArea;
    @FXML   private javafx.scene.layout.GridPane mainPane;

    private String inputDirPath;
    private String[] inputExtensions;
    private String inputSearchText;

    public  MainWindowController ( ) {}

    public void initialize() {
        treeView.prefHeightProperty().bind(mainPane.heightProperty());
        fileContentArea.prefHeightProperty().bind(mainPane.heightProperty());
    }

    public MainWindowController(String inputPath, String[] inputExtensions, String inputSearchText) {
        this.inputDirPath = inputPath;
        this.inputExtensions = inputExtensions;
        this.inputSearchText = inputSearchText;

    }

    public void setInputDirPath(String inputDirPath) {
        this.inputDirPath = inputDirPath;
        dirNameLabel.setText(inputDirPath);
    }
    public void setInputExtensions(String[] inputExtensions) {
        this.inputExtensions = inputExtensions;
    }
    public void setInputSearchText(String inputSearchText) {
        this.inputSearchText = inputSearchText;
    }
    public void setTreeView (String inputDirPath) {
        if (inputDirPath != null) {
            displayTreeView(inputDirPath);
        } else {
            System.out.println("fuck its null");
        }
    }

    public String getInputDirPath() {
        return inputDirPath;
    }
    public String[] getInputExtensions() { return inputExtensions; }
    public String getInputSearchText() {
        return inputSearchText;
    }

    public void onClick(ActionEvent actionEvent) {
    }


    public void displayTreeView(String inputDirectoryLocation) {
        File fileInputDirectoryLocation = new File(inputDirectoryLocation);
        File fileList[] = fileInputDirectoryLocation.listFiles();
        TreeItem<String> rootItem = new TreeItem<>(fileInputDirectoryLocation.getName());
        rootItem.setExpanded(true);
        // create tree
        for (File file : fileList) {
            createTree(file, rootItem);
        }

        treeView.setRoot(rootItem);
    }

    public void createTree(File file, TreeItem<String> parent) {
        if (file.isDirectory() && file.listFiles().length > 0) {
            TreeItem<String> treeItem = new TreeItem<>(file.getName());
            parent.getChildren().add(treeItem);
            if (file != null) {
                for (File f : file.listFiles()) {
                    createTree(f, treeItem);
                }
            }
        } else if (Arrays.asList(inputExtensions).contains(getFileExtension(file.getAbsolutePath()))) {
            parent.getChildren().addAll(new TreeItem<>(file.getName()));
        }
    }
    private String getFileExtension (String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }
}
