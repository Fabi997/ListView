package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
        	String fabiansPath = "C:\\Users\\fabia\\PIC_GUI\\PIC_UI\\src\\main\\TestProg_PicSim_20210420\\TPicSim1.LST";
        	List<String> input = readLinesFromFile(fabiansPath);
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);

            ListView<CodeLine> listView = new ListView<>();
            listView.setCellFactory(param -> new ListCell<CodeLine>() {
                private ToggleButton breakpointButton;

                {
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    breakpointButton = new ToggleButton("B");
                    breakpointButton.setOnAction(event -> {
                        CodeLine codeLine = getItem();
                        codeLine.setBreakpoint(breakpointButton.isSelected());
                        
                        if (breakpointButton.isSelected()) {
                            // Highlight the line when the button is pressed
                            setStyle("-fx-background-color: yellow;");
                        } else {
                            // Remove the highlighting when the button is released
                            if (codeLine.getContent().startsWith("0000")) {
                                setStyle("-fx-background-color: yellow;");
                            } else {
                                setStyle("");
                            }
                        }
                        
                        // Update the button text or perform any other action
                    });
                }

                @Override
                protected void updateItem(CodeLine item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Label contentLabel = new Label(item.getContent());

                        HBox hbox = new HBox(breakpointButton, contentLabel);
                        hbox.setSpacing(10);
                        setGraphic(hbox);

                        // Highlight the line with "0000" or when the button is pressed
                        if (item.getContent().startsWith("0000") || item.isBreakpoint()) {
                            setStyle("-fx-background-color: yellow;");
                        } else {
                            setStyle("");
                        }
                    }
                }
            });


            for(String k : input) {
   			 
   			listView.getItems().add(new CodeLine(1, k));
            }

            // Add sample code lines to the ListView
            
            		
            root.setCenter(listView);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static List<String> readLinesFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        reader.close();
        return lines;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
