package org.webskey.opener;
	
import java.io.File;

import org.webskey.services.RunTimeRunner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			RunTimeRunner runtime = new RunTimeRunner();
			final File folder = new File("src/main/resources/");
			Button btn = new Button("RUN");
			btn.setOnAction((event) -> {
				
				for (final File fileEntry : folder.listFiles()) {
			        
					runtime.run(fileEntry);
			        
			    }	

			
			    
			});
			
			Stage secondStage = new SecondStage();    
			secondStage.setTitle("");
			Button btn1 = new Button("ADD");
			btn1.setOnAction((event) -> {				
				secondStage.show();
			});
			
			HBox root = new HBox(btn, btn1);
			root.setSpacing(15);
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
