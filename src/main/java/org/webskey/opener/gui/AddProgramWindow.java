package org.webskey.opener.gui;

import java.io.File;

import org.webskey.opener.dao.FileCrud;
import org.webskey.opener.model.Program;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddProgramWindow extends Stage {
	
	private String path;
	
	public AddProgramWindow(TableRunners tr) {
		FileCrud fileCrud = new FileCrud();		
		Program program = new Program();

		//Name
		Label nameInfoLabel = new Label("Podaj nazwe programu:");
		TextField nameTextField = new TextField();
		//Path
		Label pathInfoLabel = new Label("Podaj sciezke do programu:");
		TextField pathTextField = new TextField();
		//FileChooser
		FileChooser fileChooser = new FileChooser();
		Button chooseFile = new Button("Wybierz plik");
		chooseFile.setOnAction((event) -> {
			File selectedFile = fileChooser.showOpenDialog(this);
			if (selectedFile != null) 				
				pathTextField.setText(selectedFile.getAbsolutePath());
		});
		//Options
		Label optionsInfoLabel = new Label("Podaj opcje:");
		TextField optionsTextField = new TextField();
		//Directory
		Label directoryInfoLabel = new Label("Podaj directory:");
		TextField directoryTextField = new TextField();
		//ButtonConfirm
		Button confirm = new Button("Dodaj");
		confirm.setOnAction((event) -> {
			program.setName(nameTextField.getText());
			program.setPath(pathTextField.getText());
			program.setDirectory(directoryTextField.getText());
			program.setOptions(optionsTextField.getText());

			fileCrud.write(path, program);
			tr.create();
			close();
		});
		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(10));

		ObservableList<Node> list = vBox.getChildren(); 
		list.addAll(nameInfoLabel, nameTextField, pathInfoLabel, pathTextField, chooseFile, optionsInfoLabel, optionsTextField, directoryInfoLabel, directoryTextField, confirm);

		setTitle("Dodaj program");
		setScene(new Scene(vBox, 300, 400));
		initModality(Modality.APPLICATION_MODAL);
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
