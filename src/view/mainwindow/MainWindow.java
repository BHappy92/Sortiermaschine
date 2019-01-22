package view.mainwindow;

import java.util.Vector;

import control.sortcontrol.BubbleSort;
import control.sortcontrol.QuickSort;
import control.sortcontrol.Sorts;
import control.unitControl.Swap;
import control.unitControl.UnitControl;
import control.unitControl.UnitValues;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;

public class MainWindow {
	
	public static double mainWindowHeight = 600;
	public static double mainWindowWidth  = 1200;
	
	private Stage primaryStage;
	private BorderPane root;
	private Scene scene;
	private HBox menuBox;
	private Pane background;	
	private Button generateBtn;
	private Button backwardBtn;
	private Button pauseBtn;
	private Button forwardBtn;
	private ToggleButton stepByStepToggle;
	private TextField txtfSampleSize;
	private ComboBox<String> algorithmsCB;
	private ComboBox<String> ordersCB;
	private Button startBtn;
	private Label delayLbl;	
	
	private Vector<Rectangle> units;
	private Vector<UnitValues> unitValues;
	private boolean unitsAreGenerated = false;
	
	public MainWindow() {
		
		initComponents();	
		posComponents();
		styleComponents();
		addFunctionality();
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	private void initComponents() {
		primaryStage 		= new Stage();
		root			= new BorderPane();
		menuBox				= new HBox();
		background 			= new Pane();
		scene 				= new Scene(root, mainWindowWidth, mainWindowHeight);
		generateBtn			= new Button("Generate");
		backwardBtn			= new Button();
		pauseBtn 			= new Button();
		forwardBtn			= new Button();
		stepByStepToggle	= new ToggleButton("Step-By-Step");
		txtfSampleSize		= new TextField();
		algorithmsCB		= new ComboBox<>();
		
		new Vector<Swap>();
			algorithmsCB.getItems().addAll(
							"BubbleSort",
							"QuickSort"
							);
			algorithmsCB.getSelectionModel().selectFirst();
		ordersCB 			= new ComboBox<>();
			ordersCB.getItems().addAll(
							"Ordered",
							"Random"
					);
			ordersCB.getSelectionModel().select(1);;
		startBtn				= new Button("Start");
		
		delayLbl			= new Label("Delay: "+Sorts.delay + "ms");
		
		background.getChildren().addAll(generateBtn, backwardBtn, pauseBtn, forwardBtn, 
				/*stepByStepToggle,*/ txtfSampleSize, algorithmsCB, ordersCB, startBtn, delayLbl);
		
		
		root.setTop(menuBox);
		
		
		menuBox.setPrefSize(mainWindowWidth, (1/5)*mainWindowHeight);
		
		root.setCenter(background);
		background.setPrefSize(mainWindowWidth, (4/5)*mainWindowHeight);

		txtfSampleSize.setPromptText("Anzahl Elemente");
		txtfSampleSize.setPrefWidth(55);
		
		algorithmsCB.setPromptText("Algorithmus");
		ordersCB.setPromptText("Anordnung");
		
		
	}
	private void posComponents() {
		generateBtn.relocate(5, 5);
		backwardBtn.relocate(700, 5);
		pauseBtn.relocate(760, 5);
		forwardBtn.relocate(820, 5);
		stepByStepToggle.relocate(900, 5);
		txtfSampleSize.relocate(500, 5);
		algorithmsCB.relocate(250, 5);
		ordersCB.relocate(100, 5);
		startBtn.relocate(400, 5);
		delayLbl.relocate(580, 5);
	}	
	private void styleComponents(){
		scene.getStylesheets().add("util/style.css");
		background.getStyleClass().add("pane");
		
		Image pause=new Image("util/images/pause.png");
		ImageView iv1=new ImageView(pause);
		iv1.setFitHeight(30);
		iv1.setFitWidth(30);
		pauseBtn.setGraphic(iv1);
		
		
		Image play=new Image("util/images/play.png");
		ImageView iv2=new ImageView(play);
		iv2.setFitHeight(30);
		iv2.setFitWidth(30);
		forwardBtn.setGraphic(iv2);
		
		
		Image back=new Image("util/images/back.png");
		ImageView iv3=new ImageView(back);
		iv3.setFitHeight(30);
		iv3.setFitWidth(30);
		backwardBtn.setGraphic(iv3);
		
		menuBox.setStyle("-fx-background-color: black");
		menuBox.setPadding(new Insets(5, 10, 5, 10));
		
		delayLbl.setTextFill(Color.WHITE);
	}
	

	public void initOrderCB() {
		switch(ordersCB.getValue()) {
		case "Ordered":
			UnitControl.posNormal(units); 
			unitValues = UnitControl.initUnitValues(units);
			break;
			
		case "Random":
			UnitControl.posRandom(units); 
			unitValues = UnitControl.initUnitValues(units);
			break;
			
		default: 
			if(ordersCB.getValue() == null)
				UnitControl.posRandom(units);
			break;
		}
	}
	
	
	
	private void addFunctionality() {	
		generateBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if (!txtfSampleSize.getText().isEmpty()) {
					if (unitsAreGenerated) {
						UnitControl.removeUnits(background, units);
					}
					int sampleSize = Integer.parseInt(txtfSampleSize.getText());
					units = UnitControl.generateUnits(sampleSize);
					UnitControl.addUnits(background, units);
					initOrderCB();
					
					txtfSampleSize.setText("");
					txtfSampleSize.setPromptText(Integer.toString(sampleSize));
					
					unitsAreGenerated = true;
				}		
			}				
		});
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				switch(algorithmsCB.getValue()) {
					case "BubbleSort":
						BubbleSort bubblesort = new BubbleSort(units, unitValues, 200);
						delayLbl.setText("Delay: "+Sorts.delay + "ms");
						bubblesort.start();
						break;
						
					case "QuickSort":
						QuickSort quicksort = new QuickSort(units, unitValues, 200);
						delayLbl.setText("Delay: "+Sorts.delay + "ms");
						quicksort.start();
						break;
				}
				
			}
		});	
		
		forwardBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if(Sorts.delay >= 60) 			
					Sorts.delay -= 10;

				delayLbl.setText("Delay: " + Sorts.delay + "ms");
			}
		});
		
		backwardBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Sorts.delay += 10;
				delayLbl.setText("Delay: " + Sorts.delay + "ms");
			}
		});

		
	}	
}
