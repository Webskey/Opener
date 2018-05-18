package org.webskey.opener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.webskey.model.Program;
import org.webskey.services.FileTxtWriter;
import org.webskey.services.ObjectToJsonParser;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondStage extends Stage {

	public SecondStage() {
		FileTxtWriter ftw = new FileTxtWriter();
		ObjectToJsonParser objectToJsonParser = new ObjectToJsonParser();
		Program program = new Program();
		
		Label nameInfoLabel = new Label("Podaj nazwe programu:");
		TextField nameTextField = new TextField();
		Label pathInfoLabel = new Label("Podaj sciezke do programu:");
		TextField pathTextField = new TextField();
		Label optionsInfoLabel = new Label("Podaj opcje:");
		TextField optionsTextField = new TextField();
		Button confirm = new Button("Dodaj");
		confirm.setOnAction((event) -> {
			program.setName(nameTextField.getText());
			program.setPath(pathTextField.getText());
			String[] arr = optionsTextField.getText().split(" ");
			program.setOptions(arr);
			try {
				ftw.write(objectToJsonParser.parse(program), create(program));
			} catch (IOException e) {
				e.printStackTrace();
			}
			close();
		});
		VBox vBox = new VBox();
		vBox.setSpacing(10);
		
		ObservableList<Node> list = vBox.getChildren(); 
		list.addAll(nameInfoLabel, nameTextField, pathInfoLabel, pathTextField, optionsInfoLabel, optionsTextField, confirm);
		
		setScene(new Scene(vBox, 300, 300));
		setAlwaysOnTop(true);
	}
	
	public Path create(Program program) {
		String path = "src/main/resources/" + program.getName() + ".txt";
		Path fpath = null;
		 try {
			fpath = Files.createFile(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return fpath;
	}
}
