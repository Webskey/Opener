package org.webskey.opener;
	
import java.io.File;

import org.webskey.opener.gui.SaveWindow;
import org.webskey.opener.gui.TableRunners;
import org.webskey.opener.services.FileTextWriter;
import org.webskey.opener.services.MajorClass;
import org.webskey.opener.services.RuntimeRunner;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MajorClass mc = new MajorClass();
			RuntimeRunner runtime = new RuntimeRunner();
			FileTextWriter fileTextWriter = new FileTextWriter();
			
			//File folder = new File("data/");
			//File folder = new File("src/main/resources/first");
			
			//ButtonRun
			Button buttonRun = new Button("RUN");
			buttonRun.setOnAction((event) -> {				
				for (File fileEntry : mc.getFoldero().listFiles()) 
					runtime.run(fileEntry);	
			});					
			//TableRunners
			TableRunners tableRunners = new TableRunners(mc);
			//Projects ComboBox
			File ProjectsFolder = new File("src/main/resources/Projects");
			//File ProjectsFolder = new File("data/Projects");
			ComboBox<Object> projectsCombo = new ComboBox<Object>();
			for (File fileEntry : ProjectsFolder.listFiles()) {
				projectsCombo.getItems().add(fileEntry.getName());
			}
			projectsCombo.valueProperty().addListener((ObservableValue<?> ov, Object prevValue, Object currValue)-> {
				//mc.setFolder("src/main/resources/Projects/" + currValue);
				mc.setFolder("data/Projects/" + currValue);
				tableRunners.create();
			    });
			projectsCombo.setValue(projectsCombo.getItems().get(0));
			//AddWindow
			SaveWindow addWindow = new SaveWindow(tableRunners);   
			//ButtonAdd
			Button buttonAdd = new Button("ADD");
			buttonAdd.setOnAction((event) -> {				
				addWindow.show();
				addWindow.setPath(mc.getFoldero().toString() + "\\");
			});	
			//ButtonRemove
			Button buttonRemove = new Button("Remove");
			buttonRemove.setOnAction((event) -> {		
				fileTextWriter.delete(mc.getFoldero().toString() , tableRunners.getSelectionModel().getSelectedItem().getName());
				tableRunners.remowe(tableRunners.getSelectionModel().getSelectedItem());				
			});			
			//LOOK
			HBox hBox = new HBox(buttonAdd, buttonRemove);
			hBox.setSpacing(5);
			
			VBox root = new VBox(projectsCombo, buttonRun, tableRunners, hBox);
			root.setSpacing(15);
			root.setPadding(new Insets(50, 50, 50, 50));
			
			Scene scene = new Scene(root, 600, 300);			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Opener");
			primaryStage.getIcons().add(new Image("/icon.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
