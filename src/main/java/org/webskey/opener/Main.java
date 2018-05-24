package org.webskey.opener;
	
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.webskey.opener.gui.SaveWindow;
import org.webskey.opener.gui.TableRunners;
import org.webskey.opener.model.Program;
import org.webskey.opener.services.FileTextReader;
import org.webskey.opener.services.JsonToObjectParser;
import org.webskey.opener.services.RuntimeRunner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			RuntimeRunner runtime = new RuntimeRunner();
			JsonToObjectParser jsonToObjectParser = new JsonToObjectParser();
			FileTextReader fileTextReader = new FileTextReader();
			//final File folder = new File("data/");
			final File folder = new File("src/main/resources/first");
			//ButtonRun
			Button buttonRun = new Button("RUN");
			buttonRun.setOnAction((event) -> {				
				for (final File fileEntry : folder.listFiles()) 
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
			TableRunners tableRunners = new TableRunners();			
			for (final File fileEntry : folder.listFiles()) {
				tableRunners.adda(jsonToObjectParser.parse(fileTextReader.getFile(fileEntry)));
			}
			//ButtonRemove
			Button buttonRemove = new Button("Remove");
			buttonRemove.setOnAction((event) -> {				
				tableRunners.remowe(tableRunners.getSelectionModel().getSelectedItem());
			});
			//RunSelectedButton
			Button btnR = new Button("RUN Selected");
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
			HBox root = new HBox(buttonRun, buttonAdd, buttonRemove, tableRunners, btnR);
			root.setSpacing(15);
			
			Scene scene = new Scene(root, 800, 400);
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
