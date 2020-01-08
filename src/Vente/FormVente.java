package Vente;


import java.util.function.Predicate;

import javax.swing.text.LabelView;



import Categorie.Categorie;
import Categorie.CategorieDAO;
import Categorie.CategorieDAOIMPL;
import Categorie.FormCategorie;
import Client.Client;
import Client.ClientDAO;
import Client.ClientDAOIMPL;
import Client.FormClient;
import DataAccess.*;
import Ligne.FormLigne;
import Ligne.Ligne;
import Ligne.LigneDAO;
import Ligne.LigneDAOIMPL;
import Paiement.FormPaiement;
import Produit.FormProduit;
import Produit.Produit;
import Produit.ProduitDAO;
import Produit.ProduitDAOIMPL;
import Vente.Vente;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



public class FormVente extends Application {
	int windowHeight = 750;
	 int windowWidth = 1300;
    //Scene
    public Scene scene;
    // Conteneurs
    BorderPane root = new BorderPane();
    HBox HBoxButtons = new HBox();
    VBox vBoxmenu = new VBox();
    VBox vBoxVenteList = new VBox();
    VBox vBoxLigneList = new VBox();
    HBox hBoxLabelName = new HBox();
    GridPane gridPaneData = new GridPane();
    //Controles/elements
    // label main title
    Label labelMainTitle=new Label("Gestion des ventes");
    //Buttons of left border
    	
    Button buttonAjouter = new Button("Add");
    
    Button buttonAdd = new Button("Ajouter");
    Button buttonUpdate = new Button("Modifier");
    Button buttonDelete = new Button("supprimer");

    
    //Menu
    Button buttonProduit = new Button("Produit");
    Button buttonCategorie = new Button("Categorie");
    Button buttonClient = new Button("Client");
    Button buttonVente = new Button("Vente");
    Button buttonPaiement = new Button("Paiement");
    //Grid elements
    //Text labels
    /*
    Label labelId = new Label("Id");
    Label labelDesignation = new Label("Designation");
    Label labelQte = new Label("Quantité");
    Label labelPrixAchat = new Label("Prix achat");
    Label labelPrixVente = new Label("Prix vente");
    
    // Text fields
    TextField fieldId = new TextField();
    TextField fieldDesignation = new TextField();
    TextField fieldPrixAchat = new TextField();
    TextField fieldQte = new TextField();
    TextField fieldPrixVente = new TextField();
    */
    // Ligne Array
    
    TableView<Vente> VenteTable =new TableView<>();
    
    ObservableList<Vente> observableVente = FXCollections.observableArrayList();
    VenteDAO vDAO = new VenteDAOIMPL();
    List<Vente> VenteList =vDAO.getAll();
    
    TextField textKeyWord=new TextField();

    // Get Data
    TableView<Ligne> LigneTable =new TableView<>();
    
    ObservableList<Ligne> observableLigne = FXCollections.observableArrayList();
    LigneDAO LDAO = new LigneDAOIMPL();
   
    private static long id  ;
    List<Ligne> LigneList ;
   

    // Error msg
    Alert alert = new Alert(Alert.AlertType.ERROR);

    // Produit
    /*
    TableView<Produit> ProduitTable =new TableView<>();

    ObservableList<Produit> observableProduit = FXCollections.observableArrayList();
    ProduitDAO pDAO = new ProduitDAOIMPL();
    List<Produit> ProduitList =pDAO.getAll();
    */
    
   // ComboBox<Categorie> comboBoxCategorie = new ComboBox<>(observable);
    
  //  ChoiceBox<Categorie> choise = ChoiceBox<Categorie>();
    
   // observableArrayList.setAll(clientList);
//    clientTable.setItems(observableArray);
   // comboBoxCategorie.setPromptText("Choisissez une catÃ©gorie");
    
   
    
    
    private void initPanes() {

        // hBoxLabelName style
        hBoxLabelName.setAlignment(Pos.CENTER);
        hBoxLabelName.setPrefSize(300, 80);
        hBoxLabelName.getStyleClass().add("topHBox");
        // vBoxButtons style
      //  root.setBottom(HBoxButtons);
        HBoxButtons.getStyleClass().add("BottomVBox");
        HBoxButtons.setPrefSize(200, 50);
        HBoxButtons.setSpacing(0.2);
     //   HBoxButtons.setAlignment(Pos.BOTTOM_CENTER);
        // gridPaneData style
       
    /*
        gridPaneData.getStyleClass().add("gridPaneData");
        gridPaneData.setVgap(10);
        gridPaneData.setHgap(10);
*/
        
        root.setLeft(vBoxmenu);
        vBoxmenu.getStyleClass().add("leftVBox");
        vBoxmenu.setPrefSize(200, 100);
        vBoxmenu.setSpacing(0.2);
        buttonCategorie.getStyleClass().add("menu");
        buttonClient.getStyleClass().add("menu");
        buttonProduit.getStyleClass().add("menu");
        buttonVente.getStyleClass().add("menu");
        buttonPaiement.getStyleClass().add("menu");
        
        
        // vBoxProduitList style
        root.setCenter(vBoxVenteList);
        vBoxVenteList.getStyleClass().add("vBoxVenteList");
        
        // vBoxProduitList style
        root.setRight(vBoxLigneList);
        vBoxLigneList.getStyleClass().add("vBoxLigneList");
        // Main title style
        root.setTop(hBoxLabelName);
        labelMainTitle.getStyleClass().add("custom-label");
        //Buttons style
        buttonAdd.getStyleClass().add("custom-button");
        buttonUpdate.getStyleClass().add("custom-button");
        buttonDelete.getStyleClass().add("custom-button");
        /*
        // fields style
        fieldId.setDisable(true);
        // Labels Style
        labelId.setFont(new Font("Calibri", 16));
        labelId.setTextFill(Color.BLACK);

        labelDesignation.setFont(new Font("Calibri", 16));
        labelDesignation.setTextFill(Color.BLACK);

        labelQte.setFont(new Font("Calibri", 16));
        labelQte.setTextFill(Color.BLACK);
        
        
        labelPrixAchat.setFont(new Font("Calibri", 16));
        labelPrixAchat.setTextFill(Color.BLACK);
        
        labelPrixVente.setFont(new Font("Calibri", 16));
        labelPrixVente.setTextFill(Color.BLACK);
        
       */
        observableVente.setAll(VenteList);
      

     //   comboBoxCategorie.setItems(observable);
       // comboBoxCategorie.getSelectionModel().select(3);
        initElements();
        initTableVente();
        initTableLC();
        initEvents();
        
    
    }

    public void initItems(long id) {

    	LigneList= LDAO.getAll(id);
    	if(LigneList!= null) {
    		 
    		   observableLigne.setAll(LigneList);
    	       LigneTable.setItems(observableLigne);
        }
        else {
     	   alert = new Alert(AlertType.INFORMATION);
         	alert.setTitle("Erreur !");
         	
         	alert.setContentText("Empty ");

         	alert.showAndWait();
     	 
        }
    	
    	
    	
	  

	   System.out.println("ID vente selectionné : " + id);
    }
    
   private void initTableLC() {
	//   textKeyWord.setMaxWidth(1000);
		  LigneTable.setMaxWidth(500);
		  LigneTable.setMaxHeight(200);
	   
	   TableColumn<Ligne, Produit> LigneDesignationColumn =new TableColumn<>("Produit");
       LigneDesignationColumn.setCellValueFactory(new PropertyValueFactory<>("produit"));
       LigneDesignationColumn.setPrefWidth(100);
    //    LigneDesignationColumn.prefWidthProperty().bind(LigneTable.widthProperty().divide(100 / 50));

       
       TableColumn<Ligne, Integer> LigneQteColumn =new TableColumn<>("Qte");
       LigneQteColumn.setCellValueFactory(new PropertyValueFactory<>("qte_ligne"));
       LigneQteColumn.setPrefWidth(200);
      //     LigneQteColumn.prefWidthProperty().bind(LigneTable.widthProperty().divide(100 / 20));

       
                
       TableColumn<Ligne, Double> LigneSousTotalVenteColumn =new TableColumn<>("Sous total");
       LigneSousTotalVenteColumn.setCellValueFactory(new PropertyValueFactory<>("sousTotal"));
       LigneSousTotalVenteColumn.setPrefWidth(200);
  //       LigneSousTotalVenteColumn.prefWidthProperty().bind(LigneTable.widthProperty().divide(100 /30));

  //     LigneList= LDAO.getAll(id);

       LigneTable.getColumns().addAll(LigneDesignationColumn,LigneQteColumn,LigneSousTotalVenteColumn);
      // observableLigne.setAll(LigneList);
       LigneTable.setItems(observableLigne);
        
   }
    
    
   private void initTableVente() {
	  textKeyWord.setMaxWidth(1000);
	  VenteTable.setMaxWidth(1000);
	  VenteTable.setMaxHeight(300);

    	TableColumn<Vente, Long> venteIdColumn =new TableColumn<>("id");
    	venteIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_vente"));
    	venteIdColumn.setPrefWidth(250);
    	//venteIdColumn.prefWidthProperty().bind(VenteTable.widthProperty().divide(100 / 15));


         TableColumn<Vente, String> venteClientColumn =new TableColumn<>("Client");
         venteClientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
         venteClientColumn.setPrefWidth(250);
         //venteClientColumn.prefWidthProperty().bind(VenteTable.widthProperty().divide(100 / 50));

         
         TableColumn<Vente, Double> venteTotalColumn =new TableColumn<>("Total");
         venteTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
         venteTotalColumn.setPrefWidth(250);
      //   venteTotalColumn.prefWidthProperty().bind(VenteTable.widthProperty().divide(100 / 20));

         
                  
         TableColumn<Vente, Date> venteDateVenteColumn =new TableColumn<>("Date");
         venteDateVenteColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
         venteDateVenteColumn.setPrefWidth(250);
      //   venteDateVenteColumn.prefWidthProperty().bind(VenteTable.widthProperty().divide(100 /20));

        


        
         
         VenteTable.getColumns().addAll(venteIdColumn,venteClientColumn,venteTotalColumn,venteDateVenteColumn);
         observableVente.setAll(VenteList);
         VenteTable.setItems(observableVente);
         
         
     
    }
   
   /*
    * tableProduits.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    */


    /*
    private void buttonAjouter() {

        ProduitTable = initTableProduit();
        productsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // "add product to order" column
        TableColumn addProductCol = new TableColumn<>("Ajouter");
        addProductCol.setCellFactory(ActionButtonTableCell.forTableColumn("Ajouter", (Product p) -> {
            int qty = GUITools.openQtyTextInputDialog();
            if (qty == -1) return p;

            OrderLinesController.add(new OrderLine(0, p, p.getSellingPrice(), qty));
            return p;
        }));

        productsTv.getColumns().add(addProductCol);

        productsTv.setItems(getProductsOl());

        return new VBox(productsTv);
    }
    */

    private void initElements() {
        // label main title
        hBoxLabelName.getChildren().add(labelMainTitle);
       

        // Buttons
     //   HBoxButtons.getChildren().addAll(buttonAdd, buttonUpdate, buttonDelete);
        vBoxmenu.getChildren().addAll(buttonProduit, buttonCategorie, buttonClient,buttonVente,buttonPaiement);
        /*
        // add Text/Text fiels to gridPaneData
        gridPaneData.add(labelId, 0, 0);
        gridPaneData.add(labelDesignation, 0, 1);
        gridPaneData.add(labelQte, 0, 2);
        gridPaneData.add(labelPrixAchat, 0, 3);
        gridPaneData.add(labelPrixVente, 0, 4);
        
        gridPaneData.add(fieldId, 1, 0);
        gridPaneData.add(fieldDesignation, 1, 1);
        gridPaneData.add(fieldQte, 1, 2);
        gridPaneData.add(fieldPrixAchat, 1, 3);
        gridPaneData.add(fieldPrixVente, 1, 4);

	*/
    //    gridPaneData.add(comboBoxCategorie, 0, 5);
        
        /*
        gridPaneData.add(buttonAdd, 3, 0);
        gridPaneData.add(buttonUpdate,4, 0);
        gridPaneData.add(buttonDelete, 5, 0);
        */
        // add Ligne Array
        
       
        vBoxVenteList.getChildren().addAll(textKeyWord,VenteTable,LigneTable,buttonAdd);
  
    }

    private void drawAll() {

    }

    private  void refreshVenteTable(){
    	/*
        fieldId.clear();
        fieldQte.clear();
        fieldPrixAchat.clear();
        fieldPrixVente.clear();
        fieldDesignation.clear();
        
        */
        List<Vente> VenteList =vDAO.getAll();
        observableVente.setAll(VenteList);
        VenteTable.setItems(observableVente);
    }

    private void initEvents(){

    	/*
        textKeyWord.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                LigneList =lDAO.getAll(textKeyWord.getText());
                observableArray.setAll(LigneList);
                LigneTable.setItems(observableArray);
            }
        });
        */
    	textKeyWord.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent arg0) {
				String txt = textKeyWord.getText();
				Predicate<Vente> filter = vente -> (vente.getClient().getNom().toLowerCase().contains(txt.toLowerCase()));
				VenteTable.setItems(observableVente.filtered(filter));
			}
		});
    	
    	
  
	// List<Ligne> LigneList =LDAO.getAll();
	 VenteTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
       
         initItems(newValue.getId_vente());
         id = newValue.getId_vente() ;
   
            
        });


    	
    	
    	
    	/*
        buttonAdd.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	Categorie c ;
            	c= comboBoxCategorie.getSelectionModel().getSelectedItem();
               // System.out.println(c);
            	if(fieldDesignation.getLength() !=0 && fieldPrixAchat.getLength() !=0 && fieldPrixVente.getLength() !=0 && fieldQte.getLength() !=0) {
                    Ligne ligne = new Ligne(fieldDesignation.getText(), Integer.valueOf(fieldQte.getText()),Double.valueOf(fieldPrixAchat.getText()),Double.valueOf(fieldPrixVente.getText()),c);
                    lDAO.create(ligne);
                    refreshProduitTable();
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
                if(fieldDesignation.getLength() !=0 && fieldPrixAchat.getLength() !=0 && fieldPrixVente.getLength() !=0 && fieldQte.getLength() !=0) {
            	long id = Long.valueOf(fieldId.getText());
                String designation = fieldDesignation.getText();
                double prixAchat = Double.valueOf(fieldPrixAchat.getText());
                double prixVente = Double.valueOf(fieldPrixVente.getText());
                int qte = Integer.valueOf(fieldQte.getText());
                Categorie c ;
            	c= comboBoxCategorie.getSelectionModel().getSelectedItem();
                Ligne prod = new Ligne(id,designation ,qte,prixAchat,prixVente,c);
                lDAO.update(prod);
                List<Ligne> LigneList =lDAO.getAll();
                observableArray.setAll(LigneList);
                LigneTable.setItems(observableArray);
            	}else {
            		alert.setTitle("Input Error");
                    alert.setHeaderText("Veuillez remplir tous les champs");
                    alert.showAndWait();
            	}
                //LigneTable.getSelectionModel().selectedIndexProperty().get()

            }
        });
        
        
        buttonDelete.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(fieldId.getLength() != 0) {
                    lDAO.delete(Long.valueOf(fieldId.getText()));
                    refreshProduitTable();
                }
            }
        });
        
        
        
        LigneTable.setOnMouseClicked((e) -> {
            int index = observableArray.indexOf(LigneTable.getSelectionModel().selectedItemProperty().get());
            
            fieldId.clear();
            fieldPrixAchat.clear();
            fieldDesignation.clear();
            fieldPrixVente.clear();
            fieldQte.clear();
          	
            fieldId.insertText(0,String.valueOf(observableArray.get(index).getId_produit()));
            fieldDesignation.insertText(0,String.valueOf(observableArray.get(index).getDesignation()));
            fieldPrixAchat.insertText(0,String.valueOf(observableArray.get(index).getPrix_achat()));
            fieldPrixVente.insertText(0,String.valueOf(observableArray.get(index).getPrix_vente()));
            fieldQte.insertText(0,String.valueOf(observableArray.get(index).getQte()));
        //    comboBoxCategorie.setSelectionModel(observableArray.get(index).getCategorie());

        });
        
        */
        
        /*
         * search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<Ligne> lists=new ArrayList();
                for (Ligne p:produits){
                    if (p.getDesignation().toLowerCase().contains(newValue.toLowerCase())||Long.toString(p.getId()).toLowerCase().contains(newValue.toLowerCase())||Double.toString(p.getPrix()).toLowerCase().contains(newValue.toLowerCase())){
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

        scene = new Scene(root,1000,800);
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

        //Display Stage window
        
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
    	
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	FormLigne FL = new FormLigne();		
				Stage window1 = new Stage();
				window1.setScene(FL.scene);				
				try {
					window.hide();
					FL.start(window1);			
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

        window.show();
        
    }

    // main function
    public static void main(String[] args) {
        launch(args);
    }

	public static long getId() {
		return id;
	}

	public static void setId(long id) {
		FormVente.id = id;
	}

}
