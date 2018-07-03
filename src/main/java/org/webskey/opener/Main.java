package org.webskey.opener;

import java.io.File;

import org.webskey.opener.dao.FileCrud;
import org.webskey.opener.gui.ProjectOptionsWindow;
import org.webskey.opener.gui.ChangeProgramWindow;
import org.webskey.opener.gui.AddProgramWindow;
import org.webskey.opener.gui.TableRunners;
import org.webskey.opener.services.MajorClass;
import org.webskey.opener.services.RuntimeRunner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			MajorClass mc = new MajorClass();
			RuntimeRunner runtime = new RuntimeRunner();
			FileCrud fileCrud = new FileCrud();	
			//WarningDialog
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Uwaga");
			alert.setHeaderText("Nie zaznaczono programu!");
			alert.setContentText("Wybierz program, ktory chcesz zmienic/usunac.");

			//ButtonRun
			Button buttonRun = new Button("Uruchom");
			buttonRun.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
			buttonRun.setMinSize(100, 50);
			buttonRun.setOnAction((event) -> {				
				for (File fileEntry : mc.getFolder().listFiles()) 
					runtime.run(fileEntry);	
				Platform.exit();
			});					
			//TableRunners
			TableRunners tableRunners = new TableRunners(mc);
			//Projects ComboBox		
			Label projectsInfoLabel = new Label("Wybierz projekt: ");
			File projectsFolder = fileCrud.dataFile();
			ComboBox<String> projectsCombo = new ComboBox<String>();
			for (File fileEntry : projectsFolder.listFiles()) {
				projectsCombo.getItems().add(fileEntry.getName());
			}
			projectsCombo.valueProperty().addListener((ObservableValue<?> ov, Object prevValue, Object currValue)-> {				
				mc.setFolder(fileCrud.getPath() + currValue);
				tableRunners.create();
			});
			projectsCombo.setValue(projectsCombo.getItems().get(0));
			// === PROJECT OPTIONS === //
			ProjectOptionsWindow addProjectWindow = new ProjectOptionsWindow(projectsCombo);
			//ButtonAddProject
			Button buttonAddProject = new Button("Projects Options");
			buttonAddProject.setOnAction((event) -> {				
				addProjectWindow.show();
			});	
			//ProgramOptions
			Label programsInfoLabel = new Label("Opcje programow:");
			//AddWindow
			AddProgramWindow addWindow = new AddProgramWindow(tableRunners);   
			//ButtonAdd
			Button buttonAdd = new Button("Dodaj");
			buttonAdd.setOnAction((event) -> {				
				addWindow.show();
				addWindow.setPath(mc.getFolder().toString() + "\\");
			});	
			//ButtonRemove
			Button buttonRemove = new Button("Usun");
			buttonRemove.setOnAction((event) -> {
				if(tableRunners.getSelectionModel().getSelectedItem() == null)
					alert.showAndWait();
				else {
				fileCrud.delete(mc.getFolder().toString() + "\\" + tableRunners.getSelectionModel().getSelectedItem().getName() + ".txt");
				tableRunners.remowe(tableRunners.getSelectionModel().getSelectedItem());
				}
			});		
			//ChangeWindow
			ChangeProgramWindow changeWindow = new ChangeProgramWindow(tableRunners);
			//ButtonChange
			Button buttonChange = new Button("Zmien");
			buttonChange.setOnAction((event) -> {
				if(tableRunners.getSelectionModel().getSelectedItem() == null)
					alert.showAndWait();
				else {
				changeWindow.setWindow(mc.getFolder().toString() + "\\", tableRunners.getSelectionModel().getSelectedItem().getName() + ".txt");
				}
			});	
			//LOOK
			HBox projectOptions = new HBox(projectsCombo, buttonAddProject);
			projectOptions.setSpacing(15);
			
			HBox programOptions = new HBox(buttonAdd, buttonRemove, buttonChange);
			programOptions.setSpacing(10);
			
			VBox root = new VBox(projectsInfoLabel, projectOptions, buttonRun, tableRunners, programsInfoLabel, programOptions);
			root.setSpacing(15);
			root.setPadding(new Insets(25, 50, 50, 50));

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
