package org.webskey.opener.gui;

import java.io.File;

import org.webskey.opener.dao.FileCrud;
import org.webskey.opener.model.FilesPaths;
import org.webskey.opener.model.Program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RunnersTable extends TableView<Program>{
	private ObservableList<Program> data;
	private FileCrud fileCrud;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RunnersTable() {
		data = FXCollections.observableArrayList();
		fileCrud = new FileCrud();

		setEditable(true);
		TableColumn name = new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		name.setPrefWidth(50);

		TableColumn path = new TableColumn("Path");
		path.setCellValueFactory(new PropertyValueFactory<>("path"));
		path.setPrefWidth(250);

		TableColumn options = new TableColumn("Options");
		options.setCellValueFactory(new PropertyValueFactory<>("options"));
		options.setPrefWidth(200);

		setItems(data);
		getColumns().addAll(name, path, options);

		getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(!getSelectionModel().isEmpty()) 				
				FilesPaths.setProgramPath(newSelection.getName());
		});

		setPrefWidth(560);
	}

	public void remove(Program program) {
		data.remove(program);
	}

	public void create() {	
		data.clear();
		File file = new File(FilesPaths.getProjectPath());
		for (File fileEntry : file.listFiles()) {
			data.add(fileCrud.read(fileEntry));
		}
	}
}
