package org.webskey.opener.gui;

import java.io.IOException;

import org.webskey.opener.model.Program;
import org.webskey.opener.services.MajorClass;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class TableRunners extends TableView<Program>{
	ObservableList<Program> data = FXCollections.observableArrayList();
	MajorClass mc;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRunners(MajorClass mc) {
		this.mc = mc;
		setEditable(true);
		TableColumn name = new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		name.setPrefWidth(80);
		
		TableColumn path = new TableColumn("Path");
		path.setCellValueFactory(new PropertyValueFactory<>("path"));
		path.setPrefWidth(300);
		
		TableColumn options = new TableColumn("Options");
		options.setCellValueFactory(new PropertyValueFactory<>("options"));
		options.setPrefWidth(180);
		
		setItems(data);
		getColumns().addAll(name, path, options);
		getSelectionModel().getSelectedItems();
		
		create();
		
		setPrefWidth(560);
	}
	
	public void adda(Program program) {
		data.add(program);
	}
	
	public void remowe(Program program) {
		data.remove(program);
	}
	
	public void create() {		
		try {
			data.clear();
			data.addAll(mc.getProgramList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
