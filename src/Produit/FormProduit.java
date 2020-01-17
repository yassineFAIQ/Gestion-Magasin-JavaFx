package Produit;


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
import Paiement.FormPaiement;
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



public class FormProduit extends Application {
	int windowHeight = 750;
	 int windowWidth = 1300;
    //Scene
    public Scene scene;
    
    // Conteneurs
    BorderPane root = new BorderPane();

    
    VBox vBoxmenu = new VBox();
    VBox vBoxmenuPrime = new VBox();

    HBox HBoxButtons = new HBox();
    VBox vBoxProductList = new VBox();
    HBox hBoxLabelName = new HBox();

    GridPane gridPaneData = new GridPane();
    //Controles/elements
    // label main title
    Label labelMainTitle=new Label("Gestion des produits");
    //Buttons of bottom border
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
    Label labelId = new Label("Id");
    Label labelDesignation = new Label("Designation");
    Label labelQte = new Label("Quantité");
    Label labelPrixAchat = new Label("Prix achat");
    Label labelPrixVente = new Label("Prix vente");
    Label labelCategorie = new Label("Categorie");
    // Text fields
    TextField fieldId = new TextField();
    TextField fieldDesignation = new TextField();
    TextField fieldPrixAchat = new TextField();
    TextField fieldQte = new TextField();
    TextField fieldPrixVente = new TextField();
    
    // Product Array
    TableView<Produit> productTable =new TableView<>();
    ObservableList<Produit> observableArray = FXCollections.observableArrayList();
    TextField textKeyWord=new TextField();

    // Get Data
    ProduitDAO pDAO = new ProduitDAOIMPL();
    List<Produit> productList =pDAO.getAll();

    // Error msg
    Alert alert = new Alert(Alert.AlertType.ERROR);

    //comboBox categorie
    ObservableList<Categorie> observable = FXCollections.observableArrayList();
    CategorieDAO cDAO = new CategorieDAOIMPL();
    List<Categorie> categorieList =cDAO.getAll();
    ComboBox<Categorie> comboBoxCategorie = new ComboBox<>(observable);
    Button buttonCategerie = new Button("+");

      
   
    
    
    private void initPanes() {

    	//scene = new Scene(root);
    	
    	
        // hBoxLabelName style
        hBoxLabelName.setAlignment(Pos.CENTER);
        hBoxLabelName.setPrefSize(300, 80);
        hBoxLabelName.getStyleClass().add("topHBox");
        // vBoxButtons style
        
        vBoxmenu.getStyleClass().add("leftVBox");
        vBoxmenu.setPrefSize(200, 100);
        vBoxmenu.setSpacing(0.2);
        
      //  root.setBottom(HBoxButtons);
        HBoxButtons.getStyleClass().add("BottomVBox");
        HBoxButtons.setPrefSize(200, 50);
        HBoxButtons.setSpacing(15);
     //   HBoxButtons.setAlignment(Pos.BOTTOM_CENTER);
     

        // gridPaneData style
        root.setCenter(gridPaneData);
        gridPaneData.getStyleClass().add("gridPaneData");
        gridPaneData.setVgap(10);
        gridPaneData.setHgap(10);

        
        root.setLeft(vBoxmenu);
     
        


        
        
        // vBoxProductList style
        root.setRight(vBoxProductList);
   
        vBoxProductList.getStyleClass().add("vBoxProductList");
        // Main title style
        root.setTop(hBoxLabelName);
        labelMainTitle.getStyleClass().add("custom-label");
        //Buttons style
        buttonAdd.getStyleClass().add("custom-button");
        buttonUpdate.getStyleClass().add("custom-button");
        buttonDelete.getStyleClass().add("custom-button");
        
        
        buttonCategorie.getStyleClass().add("menu");
        buttonClient.getStyleClass().add("menu");
        buttonProduit.getStyleClass().add("menu");
        buttonVente.getStyleClass().add("menu");
        buttonPaiement.getStyleClass().add("menu");
        
        
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
        
       
        observable.setAll(categorieList);
        comboBoxCategorie.setItems(observable);
        
        buttonCategerie.setMaxSize(10,10);
        comboBoxCategorie.setMaxWidth(300);

        
        initElements();
        initTable();
        
        initEvents();
        
    }

    





	private void initTable(){
        TableColumn<Produit, Long> productIdColumn =new TableColumn<>("id");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        productIdColumn.setPrefWidth(50);

        TableColumn<Produit, Long> productDesignationColumn =new TableColumn<>("Designation");
        productDesignationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        productDesignationColumn.setPrefWidth(100);

        TableColumn<Produit, Long> productQteColumn =new TableColumn<>("Quantité");
        productQteColumn.setCellValueFactory(new PropertyValueFactory<>("qte"));
        productQteColumn.setPrefWidth(100);
        
        
        TableColumn<Produit, Long> productPrixAchatColumn =new TableColumn<>("Prix achat");
        productPrixAchatColumn.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
        productPrixAchatColumn.setPrefWidth(100);
        
        TableColumn<Produit, Long> productPrixVenteColumn =new TableColumn<>("Prix vente");
        productPrixVenteColumn.setCellValueFactory(new PropertyValueFactory<>("prix_vente"));
        productPrixVenteColumn.setPrefWidth(100);
        
        TableColumn<Produit, Long> productCategorieColumn =new TableColumn<>("Categorie");
        productCategorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        productCategorieColumn.setPrefWidth(100);

        

        productTable.getColumns().addAll(productIdColumn,productDesignationColumn,productQteColumn,productPrixAchatColumn,productPrixVenteColumn,productCategorieColumn);
        observableArray.setAll(productList);
        productTable.setItems(observableArray);
        
        
    }


    private void initElements() {
    	

    //	TabProduit.setContent();
        // label main title
        hBoxLabelName.getChildren().add(labelMainTitle);
       

        // Buttons
        HBoxButtons.getChildren().addAll(buttonAdd, buttonUpdate, buttonDelete);
        vBoxmenu.getChildren().addAll(buttonProduit, buttonCategorie, buttonClient,buttonVente,buttonPaiement);

        // add Text/Text fiels to gridPaneData
        gridPaneData.add(labelId, 0, 2);
        gridPaneData.add(labelDesignation, 0, 3);
        gridPaneData.add(labelQte, 0, 4);
        gridPaneData.add(labelPrixAchat, 0, 5);
        gridPaneData.add(labelPrixVente, 0, 6);
        gridPaneData.add(labelCategorie, 0, 7);

        
        gridPaneData.add(fieldId, 1, 2);
        gridPaneData.add(fieldDesignation, 1, 3);
        gridPaneData.add(fieldQte, 1, 4);
        gridPaneData.add(fieldPrixAchat, 1, 5);
        gridPaneData.add(fieldPrixVente, 1, 6);
        gridPaneData.add(comboBoxCategorie, 1, 7);
        
        gridPaneData.add(buttonCategerie, 2,7);
        gridPaneData.add(buttonAdd, 0, 25);
        gridPaneData.add(buttonUpdate, 1, 25);
        gridPaneData.add(buttonDelete, 2, 25);
        
        // add Product Array
        vBoxProductList.getChildren().addAll(textKeyWord,productTable);
    }

    private void drawAll() {

    }
    /*

    public void SelectTable() {
    
    productTable.getSelectionModel().selectedItemProperty().addListener((observableArray, oldValue, newValue) -> {
    	
    	fieldId.setText(Long.toString(newValue.getId_produit()));	
        fieldDesignation.setText(newValue.getDesignation());
        fieldPrixAchat.setText(Double.toString(newValue.getPrix_achat()));
        fieldPrixVente.setText(Double.toString(newValue.getPrix_vente()));
        fieldQte.setText(Integer.toString(newValue.getQte()));

        
        comboBoxCategorie.setValue(newValue.getCategorie());


    	});

    }
    */

    private  void refreshProductTable(){
        fieldId.clear();
        fieldQte.clear();
        fieldPrixAchat.clear();
        fieldPrixVente.clear();
        fieldDesignation.clear();
        List<Produit> productList =pDAO.getAll();
        observableArray.setAll(productList);
        productTable.setItems(observableArray);
    }
    private void initEvents(){

    	/*
        textKeyWord.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                productList =pDAO.getAll(textKeyWord.getText());
                observableArray.setAll(productList);
                productTable.setItems(observableArray);
            }
        });
       */
    	buttonCategerie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	FormCategorie FC = new FormCategorie();
            	
            
				Stage window = new Stage();
				window.setScene(FC.scene);
				try {
					FC.start(window);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              
                  }
        });
    	
    	buttonCategorie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	FormCategorie FC = new FormCategorie();
            	
            
				Stage window = new Stage();
				Stage window1 = new Stage();
				window.setScene(FC.scene);
				window1.setScene(scene);
				
				try {
					window1.hide();
					FC.start(window);
					
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              
                  }
        });

    	 
    	
    	textKeyWord.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent arg0) {
				String txt = textKeyWord.getText();
				Predicate<Produit> filter = prd -> (prd.getDesignation().toLowerCase().contains(txt.toLowerCase()));
				productTable.setItems(observableArray.filtered(filter));
			}
		});
        buttonAdd.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	Categorie c ;
            	c= comboBoxCategorie.getSelectionModel().getSelectedItem();
               
            	
            	try {
            		if(fieldDesignation.getLength() !=0 && fieldPrixAchat.getLength() !=0 && fieldPrixVente.getLength() !=0 && fieldQte.getLength() !=0) {
                        Produit Prod = new Produit(fieldDesignation.getText(), Integer.valueOf(fieldQte.getText()),Double.valueOf(fieldPrixAchat.getText()),Double.valueOf(fieldPrixVente.getText()),c);
                        pDAO.create(Prod);
                        refreshProductTable();
                    }
                    else{
                        alert.setTitle("Input Error");
                        alert.setHeaderText("Veuillez remplir tous les champs");
                        alert.showAndWait();
                    }
                    
                } catch (NumberFormatException e) {
                	alert.setTitle("Input Error");
                    alert.setHeaderText("Veuillez saisir que des chiffres !! ");
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
                Produit prod = new Produit(id,designation ,qte,prixAchat,prixVente,c);
                pDAO.update(prod);
                List<Produit> productList =pDAO.getAll();
                observableArray.setAll(productList);
                productTable.setItems(observableArray);
            	}else {
            		alert.setTitle("Input Error");
                    alert.setHeaderText("Veuillez remplir tous les champs");
                    alert.showAndWait();
            	}
                //productTable.getSelectionModel().selectedIndexProperty().get()

            }
        });
        
        
        buttonDelete.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(fieldId.getLength() != 0) {
                    pDAO.delete(Long.valueOf(fieldId.getText()));
                    refreshProductTable();
                }
            }
        });
        
      

    
        
        
        
        productTable.setOnMouseClicked((e) -> {
        	
            int index = observableArray.indexOf(productTable.getSelectionModel().selectedItemProperty().get());
         
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
            comboBoxCategorie.setValue(observableArray.get(index).getCategorie());
            
        });
        
        
        
        /*
         * search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<Produit> lists=new ArrayList();
                for (Produit p:produits){
                    if (p.getDesignation().toLowerCase().contains(newValue.toLowerCase())||Long.toString(p.getId()).toLowerCase().contains(newValue.toLowerCase())||Double.toString(p.getPrix()).toLowerCase().contains(newValue.toLowerCase())){
                        lists.add(p);
                    }
                }
                updateTable(lists);
            }
        });
        */
         

    }
    /*
    @Override
    public void start(Stage window) throws Exception {
        // Init Scene

    	scene = new Scene(TabPaneProduit,1500,1000);
        scene.getStylesheets().addAll("css/style.css");
        // Init Panes - Elements
        initPanes();


        //Modify window details
        window.setScene(scene);
        window.setHeight(windowHeight);
        window.setHeight(windowWight);
      //  window.getIcons().add(new Image("images/icons/window_icon.png"));

        //Display Stage window
        window.show();
    }

    // main function
    public static void main(String[] args) {
        launch(args);
    }

*/
    
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

}
