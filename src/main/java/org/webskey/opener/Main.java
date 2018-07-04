package org.webskey.opener;

import java.io.File;

import org.webskey.opener.dao.FileCrud;
import org.webskey.opener.gui.AddProgramWindow;
import org.webskey.opener.gui.ChangeProgramWindow;
import org.webskey.opener.gui.ProjectOptionsWindow;
import org.webskey.opener.gui.RunnersTable;
import org.webskey.opener.model.FilesPaths;
import org.webskey.opener.services.ProgramsRunner;

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

	@Override
	public void start(Stage primaryStage) {
		try {
			ProgramsRunner programsRunner = new ProgramsRunner();
			FileCrud fileCrud = new FileCrud();	
			//WarningDialog
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Uwaga");
			alert.setHeaderText("Nie zaznaczono programu!");
			alert.setContentText("Wybierz program, ktory chcesz zmienic/usunac.");
			//TableRunners
			RunnersTable runnersTable = new RunnersTable();
			//Projects ComboBox		
			Label projectsInfoLabel = new Label("Wybierz projekt: ");
			File projectsFolder = new File(FilesPaths.getPath());
			ComboBox<String> projectsCombo = new ComboBox<String>();
			for (File fileEntry : projectsFolder.listFiles()) {
				projectsCombo.getItems().add(fileEntry.getName());
			}
			projectsCombo.valueProperty().addListener((ObservableValue<?> ov, Object prevValue, Object currValue)-> {
				FilesPaths.setProjectPath(currValue.toString());
				runnersTable.create();
			});
			projectsCombo.setValue(projectsCombo.getItems().get(0));
			//ButtonRun
			Button buttonRun = new Button("Uruchom");
			buttonRun.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
			buttonRun.setMinSize(100, 50);
			buttonRun.setOnAction((event) -> {				
				for (File fileEntry : new File(FilesPaths.getProjectPath()).listFiles()) 
					programsRunner.run(fileCrud.read(fileEntry));	
				Platform.exit();
			});		
			//ProjectOptionsWindow
			Label programsInfoLabel = new Label("Opcje programow:");
			ProjectOptionsWindow projectOptionsWindow = new ProjectOptionsWindow(projectsCombo);
			//ButtonAddProject
			Button buttonProjectOptions = new Button("Projects Options");
			buttonProjectOptions.setOnAction((event) -> {				
				projectOptionsWindow.show();
			});	
			//AddProgramWindow
			AddProgramWindow addProgramWindow = new AddProgramWindow(runnersTable);   
			//ButtonAddProgram
			Button buttonAddProgram = new Button("Dodaj");
			buttonAddProgram.setOnAction((event) -> {				
				addProgramWindow.show();
			});	
			//ButtonRemoveProgram
			Button buttonRemoveProgram = new Button("Usun");
			buttonRemoveProgram.setOnAction((event) -> {
				if(runnersTable.getSelectionModel().getSelectedItem() == null)
					alert.showAndWait();
				else {
					fileCrud.delete(FilesPaths.getProgramPath());
					runnersTable.remove(runnersTable.getSelectionModel().getSelectedItem());
				}
			});		
			//ChangeProgramWindow
			ChangeProgramWindow changeProgramWindow = new ChangeProgramWindow(runnersTable);
			//ButtonChange
			Button buttonChangeProgram = new Button("Zmien");
			buttonChangeProgram.setOnAction((event) -> {
				if(runnersTable.getSelectionModel().getSelectedItem() == null)
					alert.showAndWait();
				else {
					changeProgramWindow.setWindow();
				}
			});	
			//Look
			HBox projectOptions = new HBox(projectsCombo, buttonProjectOptions);
			projectOptions.setSpacing(15);

			HBox programOptions = new HBox(buttonAddProgram, buttonRemoveProgram, buttonChangeProgram);
			programOptions.setSpacing(10);

			VBox root = new VBox(projectsInfoLabel, projectOptions, buttonRun, runnersTable, programsInfoLabel, programOptions);
			root.setSpacing(15);
			root.setPadding(new Insets(25, 50, 50, 50));

			Scene scene = new Scene(root, 600, 600);			
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
