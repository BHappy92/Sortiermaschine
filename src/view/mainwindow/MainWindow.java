package view.mainwindow;

import java.util.Collections;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainWindow {
	
	public static double mainWindowHeight = 500;
	public static double mainWindowWidth  = 1000;
	
	private Stage primaryStage;
	private BorderPane mainArea;
	private Pane background;	
	private Scene scene;
	private Button start;
	private Button backwardBtn;
	private Button pauseBtn;
	private Button forwardBtn;
	private ToggleButton stepByStep;
	private TextField txtfSampleSize;
	private ComboBox<String> algorithmus;
	private ComboBox<String> orders;
	private Button test;
	
	
	private Vector<Rectangle> units;
	
	
	private boolean unitsAreGenerated = false;
	
	public MainWindow() {
		
		initComponents();	
		posComponents();
		styleComponents();
		addFunctionality();
		//testComponents();
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	private void initComponents() {
		//Initialisierung der Oberflächenkomponenten
		//Oberfläche wird erzeugt
		primaryStage 	= new Stage();
		background 		= new Pane();
		scene 			= new Scene(background, mainWindowWidth, mainWindowHeight);
		start			= new Button("START");
		backwardBtn		= new Button();
		pauseBtn 		= new Button();
		forwardBtn		= new Button();
		stepByStep 		= new ToggleButton("Step-By-Step");
		txtfSampleSize	= new TextField();
		algorithmus		= new ComboBox<>();
			algorithmus.getItems().addAll(
							"BubbleSort",
							"Quicksort",
							"Countingsort" );
		
		orders 			= new ComboBox<>();
			orders.getItems().addAll(
							"Ordered",
							"Random"
					);
		test			= new Button("Test");
		
		//Hier neue Elemente zum Hauptfenster hinzufügen
		//vvvvvvvvvvvvvvvvvvvvvvv
		background.getChildren().addAll(start, backwardBtn, pauseBtn, forwardBtn, stepByStep, txtfSampleSize, algorithmus, orders, test);
		
	}
	
	private void posComponents() {
		start.relocate(5, 5);
		backwardBtn.relocate(700, 5);
		pauseBtn.relocate(760, 5);
		forwardBtn.relocate(820, 5);
		stepByStep.relocate(900, 5);
		txtfSampleSize.relocate(500, 5);
		algorithmus.relocate(250, 5);
		orders.relocate(100, 5);
		test.relocate(400, 5);
	}
	
	private void styleComponents(){
		scene.getStylesheets().add("util/style.css");
		background.getStyleClass().add("pane");
		//Style pausebutton
		Image pause=new Image("util/images/pause.png");
		ImageView iv1=new ImageView(pause);
		iv1.setFitHeight(30);
		iv1.setFitWidth(30);
		pauseBtn.setGraphic(iv1);
		
		//Style playbutton
		Image play=new Image("util/images/play.png");
		ImageView iv2=new ImageView(play);
		iv2.setFitHeight(30);
		iv2.setFitWidth(30);
		forwardBtn.setGraphic(iv2);
		
		//Style backbutton
		Image back=new Image("util/images/back.png");
		ImageView iv3=new ImageView(back);
		iv3.setFitHeight(30);
		iv3.setFitWidth(30);
		backwardBtn.setGraphic(iv3);
		
		//Stylen des Eingabefeldes für die Samplesize
		txtfSampleSize.setPromptText("Anzahl Elemente");
		txtfSampleSize.setPrefWidth(55);
		
		algorithmus.setPromptText("Algorithmus");
		orders.setPromptText("Anordnung");
	}
	
	private void testComponents() {		
		swapUnit(1, 3);	
	}
	
	public Vector<Rectangle> generateUnits(int amount) {
		units = new Vector<>(amount);	
		for(int i = 0; i < amount; i++) {
			Rectangle tempUnit;
			
			//(...-40) damit man links und rechts einen Abstand vom mainWindow hat
			double width = ((mainWindowWidth - 40)/amount)-1;		
			double height = 400-((380/(amount-1))*i);
			tempUnit = new Rectangle(width, height);
			tempUnit.setFill(Color.WHITE);
			units.add(tempUnit);	
		}	
		return units;
	}
	
	
	public void posRandom() {
		//Hilfsvektor um die Positionen zu speichern
		Vector<Double> xPositios = new Vector<>();
		posNormal();
		
		for(int i = 0; i < units.size(); i++) {
			double tempPos = units.get(i).getLayoutX();
			xPositios.add(tempPos);
		}

		Collections.shuffle(units);
		
		for (int i = 0; i < units.size(); i++) {
			units.get(i).setLayoutX(xPositios.get(i));;
		}
		
	}
	
	public void posNormal() {
		//Damit die Units von Links nach Rechts der Größe nach geordnet werden
		reverseOrder();
		
		for(int i = 0; i < units.size(); i++) {
			double margin = i;
			double xPos = (20 + margin) + i * (units.get(i).getWidth());
			double yPos = mainWindowHeight - units.get(i).getHeight();
			units.get(i).relocate(xPos, yPos);
		}	
	}
	/*
	 * Das Speichern der x-Positionen der zu swappenden Elemente
	 * 
	 * */
	public void swapUnit(int left, int right) {	
		double leftXPos = units.get(left).getLayoutX();
		double rightXPos = units.get(right).getLayoutX();
		Collections.swap(units, left, right);
		units.get(left).setLayoutX(leftXPos);
		units.get(right).setLayoutX(rightXPos);	
	}
	
	public void reverseOrder() {
		Collections.reverse(units);		
	}	
	public void addUnitsToWindow() {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().add(units.get(i));
		}
		
	}
	public void addUnitToWindow(int index) {
		background.getChildren().add(units.get(index));
	}
	public void removeUnitsFromWindow() {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().remove(units.get(i));
		}
	}	
	public void removeUnitFromWindow(int index) {
		background.getChildren().remove(units.get(index));
	}
	
	private void addFunctionality() {
		
		start.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(unitsAreGenerated) {		
					removeUnitsFromWindow();
				}
				int sampleSize = Integer.parseInt(txtfSampleSize.getText());
				generateUnits(sampleSize);
				addUnitsToWindow();
				/*"Ordered",
					"Random"*/
				switch(orders.getValue()) {
					case "Ordered":
						posNormal(); break;
						
					case "Random":
						posRandom(); break;
						
					default: break;
				}
				
				txtfSampleSize.setText("");
				txtfSampleSize.setPromptText(Integer.toString(sampleSize));
				unitsAreGenerated = true;
			}				
		});
		
		test.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				testComponents();
			}
		});
	}
	
	
}
