package org.webskey.opener.gui;

import org.webskey.opener.model.Program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableRunners extends TableView<Program>{
	ObservableList<Program> data = FXCollections.observableArrayList();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRunners() {
		setEditable(true);
		TableColumn name = new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn path = new TableColumn("Path");
		path.setCellValueFactory(new PropertyValueFactory<>("path"));
		TableColumn options = new TableColumn("Options");
		options.setCellValueFactory(new PropertyValueFactory<>("options"));
		setItems(data);
		getColumns().addAll(name, path, options);
		getSelectionModel().getSelectedItems();
		
		setWidth(600);
	}
	
	public void adda(Program program) {
		data.add(program);
	}
	
	public void remowe(Program program) {
		data.remove(program);
	}
}
