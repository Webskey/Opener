package org.webskey.opener.gui;

import org.webskey.opener.dao.FileCrud;
import org.webskey.opener.model.Program;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeWindow extends Stage {

	private FileCrud fileCrud;
	private Program program;
	private String path;
	private TextField nameTextField;
	private TextField pathTextField;
	private TextField optionsTextField;
	private TextField directoryTextField;

	public ChangeWindow(TableRunners table) {
		fileCrud = new FileCrud();		

		//Name
		Label nameInfoLabel = new Label("Podaj nazwe programu:");
		nameTextField = new TextField();
		//Path
		Label pathInfoLabel = new Label("Podaj sciezke do programu:");
		pathTextField = new TextField();
		//Options
		Label optionsInfoLabel = new Label("Podaj opcje:");
		optionsTextField = new TextField();
		//Directory
		Label directoryInfoLabel = new Label("Podaj directory:");
		directoryTextField = new TextField();
		//ButtonConfirm
		Button confirm = new Button("Zmien");
		confirm.setOnAction((event) -> {
			String name = program.getName();
			program.setName(nameTextField.getText());
			program.setPath(pathTextField.getText());
			program.setDirectory(directoryTextField.getText());
			program.setOptions(optionsTextField.getText());

			if(program.getName().equals(name)) 
				fileCrud.update(path, program);
			else 
				fileCrud.replace(path, name, program);
			table.create();
			close();
		});
		VBox vBox = new VBox();
		vBox.setSpacing(10);

		ObservableList<Node> list = vBox.getChildren(); 
		list.addAll(nameInfoLabel, nameTextField, pathInfoLabel, pathTextField, optionsInfoLabel, optionsTextField, directoryInfoLabel, directoryTextField, confirm);
		
		setTitle("Zmien program");
		setScene(new Scene(vBox, 300, 300));
		setAlwaysOnTop(true);
	}

	public void setWindow(String path, String fileName) {
		program = fileCrud.read(path + fileName);
		this.path = path;
		nameTextField.setText(program.getName());
		pathTextField.setText(program.getPath());
		optionsTextField.setText(program.getOptions());
		directoryTextField.setText(program.getDirectory());
		show();
	}
}
