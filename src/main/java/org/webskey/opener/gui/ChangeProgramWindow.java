package org.webskey.opener.gui;

import java.io.File;

import org.webskey.opener.dao.FileCrud;
import org.webskey.opener.model.FilesPaths;
import org.webskey.opener.model.Program;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangeProgramWindow extends Stage {

	private FileCrud fileCrud;
	private Program program;
	private TextField nameTextField;
	private TextField pathTextField;
	private TextField optionsTextField;

	public ChangeProgramWindow(RunnersTable table) {
		fileCrud = new FileCrud();		
		//Name
		Label nameInfoLabel = new Label("Podaj nazwe programu:");
		nameTextField = new TextField();
		//Path
		Label pathInfoLabel = new Label("Podaj sciezke do programu:");
		pathTextField = new TextField();
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
		optionsTextField = new TextField();
		//ButtonConfirm
		Button confirm = new Button("Zmien");
		confirm.setOnAction((event) -> {
			String name = program.getName();
			program.setName(nameTextField.getText());
			program.setPath(pathTextField.getText());
			program.setOptions(optionsTextField.getText());

			if(program.getName().equals(name)) 
				fileCrud.update(FilesPaths.getProgramPath(), program);
			else 
				fileCrud.replace(FilesPaths.getProjectPath(), name, program);
			table.create();
			close();
		});
		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(10));
		
		HBox hBox = new HBox(confirm);
		hBox.setPadding(new Insets(50, 0, 0, 0));
		hBox.setAlignment(Pos.CENTER);

		ObservableList<Node> list = vBox.getChildren(); 
		list.addAll(nameInfoLabel, nameTextField, pathInfoLabel, pathTextField, chooseFile, optionsInfoLabel, optionsTextField, hBox);

		setTitle("Zmien program");
		setScene(new Scene(vBox, 300, 350));
		initModality(Modality.APPLICATION_MODAL);
	}

	public void setWindow() {
		program = fileCrud.read(FilesPaths.getProgramPath());
		nameTextField.setText(program.getName());
		pathTextField.setText(program.getPath());
		optionsTextField.setText(program.getOptions());
		show();
	}
}
