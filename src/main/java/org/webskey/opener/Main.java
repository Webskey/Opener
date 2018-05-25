package org.webskey.opener;
	
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.webskey.opener.gui.SaveWindow;
import org.webskey.opener.gui.TableRunners;
import org.webskey.opener.model.Program;
import org.webskey.opener.services.MajorClass;
import org.webskey.opener.services.RuntimeRunner;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MajorClass mc = new MajorClass();
			RuntimeRunner runtime = new RuntimeRunner();
			//File folder = new File("data/");
			//File folder = new File("src/main/resources/first");
			
			//ButtonRun
			Button buttonRun = new Button("RUN");
			buttonRun.setOnAction((event) -> {				
				for (File fileEntry : mc.getFoldero().listFiles()) 
					runtime.run(fileEntry);	
			});			
			//AddWindow
			Stage addWindow = new SaveWindow();   
			//ButtonAdd
			Button buttonAdd = new Button("ADD");
			buttonAdd.setOnAction((event) -> {				
				addWindow.show();
			});			
			//TableRunners
			TableRunners tableRunners = new TableRunners(mc);
			//Projects ComboBox
			//File ProjectsFolder = new File("src/main/resources/Projects");
			File ProjectsFolder = new File("data/Projects");
			ComboBox<Object> projectsCombo = new ComboBox<Object>();
			for (File fileEntry : ProjectsFolder.listFiles()) {
				projectsCombo.getItems().add(fileEntry.getName());
			}
			projectsCombo.valueProperty().addListener((ObservableValue<?> ov, Object prevValue, Object currValue)-> {
				//mc.setFolder("src/main/resources/Projects/" + currValue);
				mc.setFolder("data/Projects/" + currValue);
				tableRunners.create();
			    });
			      
			//ButtonRemove
			Button buttonRemove = new Button("Remove");
			buttonRemove.setOnAction((event) -> {				
				tableRunners.remowe(tableRunners.getSelectionModel().getSelectedItem());
			});
			//RunSelectedButton
			Button btnR = new Button("RUN ONE");
			btnR.setOnAction((event) -> {	
				Program program = tableRunners.getSelectionModel().getSelectedItem();
				List<String> list = new ArrayList<>();
				list.add(program.getPath());
				list.addAll(Arrays.asList(program.getOptions()));		
				try {
					String[] command = list.toArray(new String[0]);
					Runtime.getRuntime().exec(command);
				} catch (IOException e) {			
					e.printStackTrace();
				}
			});
			//LOOK
			HBox root = new HBox(buttonRun, buttonAdd, buttonRemove, tableRunners, btnR, projectsCombo);
			root.setSpacing(15);
			
			//HBox.setHgrow(tableRunners, Priority.ALWAYS);
			
			Scene scene = new Scene(root, 1000, 300);
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
