package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.*;
import view.mainwindow.MainWindow;
import control.*;

public class Main extends Application{
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		new MainWindow(primaryStage);	
	}

	public static void main(String[] args) {
		launch(args);
	}

}
