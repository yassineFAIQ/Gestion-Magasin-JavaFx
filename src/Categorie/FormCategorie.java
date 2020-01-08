package Categorie;


import java.util.function.Predicate;

import Client.FormClient;
import DataAccess.*;
import Paiement.FormPaiement;
import Produit.FormProduit;
import Vente.FormVente;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;



public class FormCategorie extends Application  {
	int windowHeight = 750;
	 int windowWidth = 1300;
    //Scene
    
   public Scene scene;
   
   
   
    // Conteneurs
    BorderPane root = new BorderPane();
    HBox hBoxButtons = new HBox();
    VBox vBoxCategorieList = new VBox();
    HBox hBoxLabelName = new HBox();
    GridPane gridPaneData = new GridPane();
    //Controles/elements
    // label main title
    Label labelMainTitle=new Label("Gestion des catégories");
    //Buttons of left border
    Button buttonAdd = new Button("Ajouter");
    Button buttonUpdate = new Button("Modifier");
    Button buttonDelete = new Button("supprimer");

    
    

    
    
    //Grid elements
    //Text labels
    Label labelId = new Label("Id");
    Label labelLibelle = new Label("Libelle");
 
    // Text fields
    TextField fieldId = new TextField();
    TextField fieldLibelle = new TextField();
  

    // Categorie Array
    TableView<Categorie> CategorieTable =new TableView<>();
    ObservableList<Categorie> observableArray = FXCollections.observableArrayList();
    TextField textKeyWord=new TextField();

    // Get Data
    CategorieDAO catDAO = new CategorieDAOIMPL();
    List<Categorie> CategorieList =catDAO.getAll();

    VBox vBoxmenu = new VBox();

    Button buttonProduit = new Button("Produit");
    Button buttonCategorie = new Button("Categorie");
    Button buttonClient = new Button("Client");
    Button buttonVente = new Button("Vente");
    Button buttonPaiement = new Button("Paiement");
    
    
    // Error msg
    Alert alert = new Alert(Alert.AlertType.ERROR);


    public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}


	private void initPanes() {

        // hBoxLabelName style
        hBoxLabelName.setAlignment(Pos.CENTER);
        hBoxLabelName.setPrefSize(300, 80);
        hBoxLabelName.getStyleClass().add("topHBox");
        // vBoxButtons style
       // root.setLeft(vBoxButtons);
        hBoxButtons.getStyleClass().add("leftVBox");
        hBoxLabelName.getStyleClass().add("topHBox");
   
        
        vBoxmenu.getStyleClass().add("leftVBox");
        vBoxmenu.setPrefSize(200, 100);
        vBoxmenu.setSpacing(0.2);
        
        buttonCategorie.getStyleClass().add("menu");
        buttonClient.getStyleClass().add("menu");
        buttonProduit.getStyleClass().add("menu");
        buttonVente.getStyleClass().add("menu");
        buttonPaiement.getStyleClass().add("menu");
        
        
        
        // gridPaneData style
        root.setCenter(gridPaneData);
        gridPaneData.getStyleClass().add("gridPaneData");
        gridPaneData.setVgap(10);
        gridPaneData.setHgap(10);

        // vBoxCategorieList style
        root.setRight(vBoxCategorieList);
        vBoxCategorieList.getStyleClass().add("vBoxCategorieList");
        // Main title style
        root.setTop(hBoxLabelName);
        labelMainTitle.getStyleClass().add("custom-label");
        //Buttons style
        buttonAdd.getStyleClass().add("custom-button");
        buttonUpdate.getStyleClass().add("custom-button");
        buttonDelete.getStyleClass().add("custom-button");
        // fields style
        fieldId.setDisable(true);
        // Labels Style
        labelId.setFont(new Font("Calibri", 16));
        labelId.setTextFill(Color.BLACK);

        labelLibelle.setFont(new Font("Calibri", 16));
        labelLibelle.setTextFill(Color.BLACK);

        
        root.setLeft(vBoxmenu);
    



        initElements();
        initTable();
        initEvents();
    }

    private void initTable(){
  	  textKeyWord.setMaxWidth(500);
  	  CategorieTable.setMaxWidth(500);
  	CategorieTable.setMaxHeight(2000);
        TableColumn<Categorie, Long> CategorieIdColumn =new TableColumn<>("id_cat");
        CategorieIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        CategorieIdColumn.setPrefWidth(248);

        TableColumn<Categorie, Long> CategorieLibelleColumn =new TableColumn<>("libelle");
        CategorieLibelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        CategorieLibelleColumn.setPrefWidth(250);

   

        CategorieTable.getColumns().addAll(CategorieIdColumn,CategorieLibelleColumn);
        observableArray.setAll(CategorieList);
        CategorieTable.setItems(observableArray);
    }

    private void initElements() {

        // label main title
        hBoxLabelName.getChildren().add(labelMainTitle);

        // Buttons
        hBoxButtons.getChildren().addAll(buttonAdd, buttonUpdate, buttonDelete);

        // add Text/Text fiels to gridPaneData
        gridPaneData.add(labelId, 0, 2);
        gridPaneData.add(labelLibelle, 0, 3);
 
        gridPaneData.add(fieldId, 1, 2);
        gridPaneData.add(fieldLibelle, 1, 3);
      
        
    
        gridPaneData.add(buttonAdd, 0, 25);
        gridPaneData.add(buttonUpdate, 1, 25);
        gridPaneData.add(buttonDelete, 2, 25);

        vBoxmenu.getChildren().addAll(buttonProduit, buttonCategorie, buttonClient,buttonVente,buttonPaiement);
        // add Categorie Array
        vBoxCategorieList.getChildren().addAll(textKeyWord,CategorieTable);
    }

    private void drawAll() {

    }

    private  void refreshCategorieTable(){
        fieldId.clear();
     
        fieldLibelle.clear();
        List<Categorie> CategorieList =catDAO.getAll();
        observableArray.setAll(CategorieList);
        CategorieTable.setItems(observableArray);
    }
    private void initEvents(){

    	/*
        textKeyWord.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                CategorieList =catDAO.getAll(textKeyWord.getText());
                observableArray.setAll(CategorieList);
                CategorieTable.setItems(observableArray);
            }
        });
        */
    	textKeyWord.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent arg0) {
				String txt = textKeyWord.getText();
				Predicate<Categorie> filter = cat -> (cat.getLibelle().toLowerCase().contains(txt.toLowerCase()));
				CategorieTable.setItems(observableArray.filtered(filter));
			}
		});
        buttonAdd.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(fieldLibelle.getLength() !=0 ) {
                    Categorie Cat = new Categorie(fieldLibelle.getText());
                    catDAO.create(Cat);
                    refreshCategorieTable();
                }
                else{
                    alert.setTitle("Input Error");
                    alert.setHeaderText("Veuillez remplir tous les champs");
                    alert.showAndWait();
                }
            }
        });
        buttonUpdate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	if(fieldLibelle.getLength() !=0 ) {
            	long id = Long.valueOf(fieldId.getText());
                String libelle = fieldLibelle.getText();
        
                Categorie cat = new Categorie(id,libelle);
                catDAO.update(cat);
                List<Categorie> CategorieList =catDAO.getAll();
                observableArray.setAll(CategorieList);
                CategorieTable.setItems(observableArray);
            	}else {
            		alert.setTitle("Input Error");
                    alert.setHeaderText("Veuillez remplir tous les champs");
                    alert.showAndWait();
            	}
                //CategorieTable.getSelectionModel().selectedIndexProperty().get()

            }
        });
        
        
        buttonDelete.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(fieldId.getLength() != 0) {
                    catDAO.delete(Long.valueOf(fieldId.getText()));
                    refreshCategorieTable();
                }
            }
        });
        
        
        
        CategorieTable.setOnMouseClicked((e) -> {
        	if(CategorieTable.getSelectionModel().selectedItemProperty().getValue()!= null) {
            int index = observableArray.indexOf(CategorieTable.getSelectionModel().selectedItemProperty().get());
            fieldId.clear();
      
            fieldLibelle.clear();
            fieldId.insertText(0,String.valueOf(observableArray.get(index).getId_cat()));
            fieldLibelle.insertText(0,String.valueOf(observableArray.get(index).getLibelle()));
        	}
        });
        
        
        
        
        
        
        
        /*
         * search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<Categorie> lists=new ArrayList();
                for (Categorie p:Categories){
                    if (p.getLibelle().toLowerCase().contains(newValue.toLowerCase())||Long.toString(p.getId()).toLowerCase().contains(newValue.toLowerCase())||Double.toString(p.getAge()).toLowerCase().contains(newValue.toLowerCase())){
                        lists.add(p);
                    }
                }
                updateTable(lists);
            }
        });
        */
         

    }

    @Override
    public void start(Stage window) throws Exception {
        // Init Scene

        scene = new Scene(root,windowWidth,windowHeight);
        scene.getStylesheets().addAll("css/style.css");
        // Init Panes - Elements
        initPanes();

        BackgroundFill background_fill = new BackgroundFill(Color.valueOf("#93E2E9"), null, null); 
        Background background = new Background(background_fill); 
        root.setBackground(background);
        
        //Modify window details
        window.setScene(scene);
        window.setHeight(windowHeight);
        window.setWidth(windowWidth);
        window.setResizable(false);
      //  window.getIcons().add(new Image("images/icons/window_icon.png"));

        
        buttonCategorie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	FormCategorie FC = new FormCategorie();		
				Stage window1 = new Stage();
				window1.setScene(FC.scene);				
				try {
					window.hide();
					FC.start(window);			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}           
                  }
        });
    	
    	buttonProduit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	FormProduit FP = new FormProduit();
				Stage window1 = new Stage();
				window1.setScene(FP.scene);
				try {
					window.hide();
					FP.start(window1);				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                  }
        });
    	
    	buttonClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	FormClient FCL= new FormClient();     
				Stage window1 = new Stage();
				window1.setScene(FCL.scene);
				try {
					window.hide();
					FCL.start(window1);				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}          
                  }
        });
    	buttonVente.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	FormVente FV = new FormVente();	           
				Stage window1 = new Stage();
				window1.setScene(FV.scene);
				try {
					window.hide();
					FV.start(window1);			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}             
                  }
        });
    	buttonPaiement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	FormPaiement FPAI = new FormPaiement();	           
				Stage window1 = new Stage();
				window1.setScene(FPAI.scene);
				try {
					window.hide();
					FPAI.start(window1);			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}             
                  }
        });
        //Display Stage window
        window.show();
    }

    // main function
    public static void main(String[] args) {
        launch(args);
    }

}

