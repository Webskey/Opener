package org.webskey.opener;

import java.io.File;

import org.webskey.opener.dao.FileCrud;
import org.webskey.opener.gui.ChangeWindow;
import org.webskey.opener.gui.SaveWindow;
import org.webskey.opener.gui.TableRunners;
import org.webskey.opener.services.MajorClass;
import org.webskey.opener.services.RuntimeRunner;

import javafx.application.Application;
import javafx.application.Platform;
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
			FileCrud fileCrud = new FileCrud();	
			//ButtonRun
			Button buttonRun = new Button("Uruchom");
			buttonRun.setOnAction((event) -> {				
				for (File fileEntry : mc.getFolder().listFiles()) 
					runtime.run(fileEntry);	
				Platform.exit();
			});					
			//TableRunners
			TableRunners tableRunners = new TableRunners(mc);
			//Projects ComboBox			
			File ProjectsFolder = fileCrud.dataFile();
			ComboBox<Object> projectsCombo = new ComboBox<Object>();
			for (File fileEntry : ProjectsFolder.listFiles()) {
				projectsCombo.getItems().add(fileEntry.getName());
			}
			projectsCombo.valueProperty().addListener((ObservableValue<?> ov, Object prevValue, Object currValue)-> {				
				mc.setFolder(fileCrud.getPath() + currValue);
				tableRunners.create();
			});
			projectsCombo.setValue(projectsCombo.getItems().get(0));
			//AddWindow
			SaveWindow addWindow = new SaveWindow(tableRunners);   
			//ButtonAdd
			Button buttonAdd = new Button("Dodaj");
			buttonAdd.setOnAction((event) -> {				
				addWindow.show();
				addWindow.setPath(mc.getFolder().toString() + "\\");
			});	
			//ButtonRemove
			Button buttonRemove = new Button("Usun");
			buttonRemove.setOnAction((event) -> {
				fileCrud.delete(mc.getFolder().toString() + "\\" + tableRunners.getSelectionModel().getSelectedItem().getName() + ".txt");
				tableRunners.remowe(tableRunners.getSelectionModel().getSelectedItem());				
			});		
			//ChangeWindow
			ChangeWindow changeWindow = new ChangeWindow(tableRunners);
			//ButtonChange
			Button buttonChange = new Button("Zmien");
			buttonChange.setOnAction((event) -> {
				changeWindow.setWindow(mc.getFolder().toString() + "\\", tableRunners.getSelectionModel().getSelectedItem().getName() + ".txt");
			});	
			//LOOK
			HBox hBox = new HBox(buttonAdd, buttonRemove, buttonChange);
			hBox.setSpacing(5);

			VBox root = new VBox(projectsCombo, buttonRun, tableRunners, hBox);
			root.setSpacing(15);
			root.setPadding(new Insets(50, 50, 50, 50));

			Scene scene = new Scene(root, 600, 600);			
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
