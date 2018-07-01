package org.webskey.opener.gui;

import org.webskey.opener.model.Program;
import org.webskey.opener.services.FileTextWriter;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeWindow extends Stage {

	public ChangeWindow(Program program, String path) {
		FileTextWriter ftw = new FileTextWriter();		
		
		//Name
		Label nameInfoLabel = new Label("Podaj nazwe programu:");
		TextField nameTextField = new TextField();
		nameTextField.setText(program.getName());
		//Path
		Label pathInfoLabel = new Label("Podaj sciezke do programu:");
		TextField pathTextField = new TextField();
		pathTextField.setText(program.getPath());
		//Options
		Label optionsInfoLabel = new Label("Podaj opcje:");
		TextField optionsTextField = new TextField();
		optionsTextField.setText(program.getOptions());
		//Directory
		Label directoryInfoLabel = new Label("Podaj directory:");
		TextField directoryTextField = new TextField();
		directoryTextField.setText(program.getDirectory());
		//ButtonConfirm
		Button confirm = new Button("Dodaj");
		confirm.setOnAction((event) -> {
			program.setName(nameTextField.getText());
			program.setPath(pathTextField.getText());
			program.setDirectory(directoryTextField.getText());
			program.setOptions(optionsTextField.getText());

			ftw.write(path, program);
			close();
		});
		VBox vBox = new VBox();
		vBox.setSpacing(10);

		ObservableList<Node> list = vBox.getChildren(); 
		list.addAll(nameInfoLabel, nameTextField, pathInfoLabel, pathTextField, optionsInfoLabel, optionsTextField, directoryInfoLabel, directoryTextField, confirm);

		setScene(new Scene(vBox, 300, 300));
		setAlwaysOnTop(true);
	}
}
