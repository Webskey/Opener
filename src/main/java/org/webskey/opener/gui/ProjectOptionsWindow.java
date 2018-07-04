package org.webskey.opener.gui;

import java.util.Optional;

import org.webskey.opener.dao.FileCrud;
import org.webskey.opener.model.FilesPaths;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProjectOptionsWindow extends Stage {

	public ProjectOptionsWindow(ComboBox<String> comboBox) {
		FileCrud fileCrud = new FileCrud();	
		//Dialog
		TextInputDialog dialog = new TextInputDialog();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setTitle("Dodaj/Zmien");
		dialog.setContentText("Podaj nazwe:");
		//projectList
		Label projectListInfoLabel = new Label("Lista projektow:");
		ListView<String> projectList = new ListView<String>();
		projectList.setPrefSize(20, 150);
		projectList.getItems().addAll(comboBox.getItems());
		projectList.getSelectionModel().select(0);
		//Delete
		Button removeButton = new Button("Usun zaznaczone");
		removeButton.setOnAction((event) -> {
			fileCrud.deleteDirectory(FilesPaths.getPath() + projectList.getSelectionModel().getSelectedItem());
			comboBox.getItems().remove(projectList.getSelectionModel().getSelectedItem());
			projectList.getItems().remove(projectList.getSelectionModel().getSelectedItem());
		});
		//Rename
		Button renameButton = new Button("Zmien nazwe");
		renameButton.setOnAction((event) -> {
			dialog.setHeaderText("Zmiana nazwy projektu");
			dialog.getEditor().setText(projectList.getSelectionModel().getSelectedItem());
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(newName -> {
				fileCrud.renameDirectory(FilesPaths.getPath(), projectList.getSelectionModel().getSelectedItem(), newName);
				comboBox.getItems().set(comboBox.getItems().indexOf(projectList.getSelectionModel().getSelectedItem()), newName);
				projectList.getItems().set(projectList.getItems().indexOf(projectList.getSelectionModel().getSelectedItem()), newName);
			});
		});
		//Add
		Button addButton = new Button("Dodaj");
		addButton.setOnAction((event) -> {
			dialog.setHeaderText("Dodawanie nowego projektu");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(name -> {
				fileCrud.createDirectory(FilesPaths.getPath(), name);
				comboBox.getItems().add(name);
				projectList.getItems().add(name);
			});
		});
		//ButtonConfirm
		Button confirm = new Button("Wyjdz");
		confirm.setOnAction((event) -> {
			close();
		});
		//Look
		HBox hBox = new HBox(addButton, removeButton, renameButton);
		hBox.setSpacing(15);
		hBox.setPadding(new Insets(0, 0, 100, 0));

		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(10));

		ObservableList<Node> list = vBox.getChildren(); 
		list.addAll(projectListInfoLabel, projectList, hBox, confirm);

		setTitle("Dodaj projekt");
		setScene(new Scene(vBox, 300, 400));
		initModality(Modality.APPLICATION_MODAL);
	}
}
