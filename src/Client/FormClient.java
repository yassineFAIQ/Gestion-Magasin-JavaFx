package Client;


import java.util.function.Predicate;

import Categorie.FormCategorie;
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



public class FormClient extends Application {
	int windowHeight = 750;
	 int windowWidth = 1300;
    //Scene
    public Scene scene;
    // Conteneurs
   public BorderPane root = new BorderPane();
    VBox vBoxButtons = new VBox();
    VBox vBoxclientList = new VBox();
    HBox hBoxLabelName = new HBox();
    GridPane gridPaneData = new GridPane();
    //Controles/elements
    // label main title
    Label labelMainTitle=new Label("Gestion des Clients");
    //Buttons of left border
    Button buttonAdd = new Button("Ajouter");
    Button buttonUpdate = new Button("Modifier");
    Button buttonDelete = new Button("supprimer");


    //Grid elements
    //Text labels
    Label labelId = new Label("Id");
    Label labelPrenom = new Label("Prenom");
    Label labelNom= new Label("Nom");
    Label labelEmail = new Label("Email");
    Label labelAdresse = new Label("Adresse");
    Label labelTel = new Label("Tel");
    // Text fields
    TextField fieldId = new TextField();
    TextField fieldPrenom = new TextField();
    TextField fieldNom = new TextField();
    TextField fieldEmail = new TextField();
    TextField fieldAdresse = new TextField();
    TextField fieldTel = new TextField();

    // client Array
    TableView<Client> clientTable =new TableView<>();
    ObservableList<Client> observableArray = FXCollections.observableArrayList();
    TextField textKeyWord=new TextField();

    // Get Data
    ClientDAO pDAO = new ClientDAOIMPL();
    List<Client> clientList =pDAO.getAll();

    // Error msg
    Alert alert = new Alert(Alert.AlertType.ERROR);

    //Menu
    VBox vBoxmenu = new VBox();
    Button buttonProduit = new Button("Produit");
    Button buttonCategorie = new Button("Categorie");
    Button buttonClient = new Button("Client");
    Button buttonVente = new Button("Vente");
    Button buttonPaiement = new Button("Paiement");
   
	private void initPanes() {

        // hBoxLabelName style
        hBoxLabelName.setAlignment(Pos.CENTER);
        hBoxLabelName.setPrefSize(300, 80);
        hBoxLabelName.getStyleClass().add("topHBox");
        // vBoxButtons style
        root.setLeft(vBoxmenu);
        vBoxmenu.getStyleClass().add("leftVBox");
        vBoxmenu.setPrefSize(200, 100);
        vBoxmenu.setSpacing(0.2);
        vBoxButtons.setPrefSize(200, 50);
        vBoxButtons.setSpacing(15);
        // gridPaneData style
        root.setCenter(gridPaneData);
        gridPaneData.getStyleClass().add("gridPaneData");
        gridPaneData.setVgap(10);
        gridPaneData.setHgap(10);

        // vBoxclientList style
        root.setRight(vBoxclientList);
        vBoxclientList.getStyleClass().add("vBoxclientList");
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

        labelAdresse.setFont(new Font("Calibri", 16));
        labelEmail.setFont(new Font("Calibri", 16));
        labelId.setFont(new Font("Calibri", 16));
        labelNom.setFont(new Font("Calibri", 16));
        labelPrenom.setFont(new Font("Calibri", 16));
        labelTel.setFont(new Font("Calibri", 16));

        
        
        labelAdresse.setTextFill(Color.BLACK);
        labelEmail.setTextFill(Color.BLACK);
        labelId.setTextFill(Color.BLACK);
        labelNom.setTextFill(Color.BLACK);
        labelPrenom.setTextFill(Color.BLACK);
        labelTel.setTextFill(Color.BLACK);
        
        
        buttonCategorie.getStyleClass().add("menu");
        buttonClient.getStyleClass().add("menu");
        buttonProduit.getStyleClass().add("menu");
        buttonVente.getStyleClass().add("menu");
        buttonPaiement.getStyleClass().add("menu");

        

        initElements();
        initTable();
        initEvents();
    }

    private void initTable(){
    	  textKeyWord.setMaxWidth(600);
      	  clientTable.setMaxWidth(600);
      	clientTable.setMaxHeight(2000);
      	
        TableColumn<Client, Long> clientIdColumn =new TableColumn<>("id");
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        clientIdColumn.setPrefWidth(40);

       
        TableColumn<Client, Long> clientPrenomColumn =new TableColumn<>("prenom");
        clientPrenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        clientPrenomColumn.setPrefWidth(100);
        
        TableColumn<Client, Long> clientNomColumn =new TableColumn<>("nom");
        clientNomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        clientNomColumn.setPrefWidth(100);
        
        TableColumn<Client, Long> clientEmailColumn =new TableColumn<>("email");
        clientEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        clientEmailColumn.setPrefWidth(120);
        
        TableColumn<Client, Long> clientAdresseColumn =new TableColumn<>("adresse");
        clientAdresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        clientAdresseColumn.setPrefWidth(150);
        
        TableColumn<Client, Long> clientTelColumn =new TableColumn<>("tel");
        clientTelColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        clientTelColumn.setPrefWidth(100);

        clientTable.getColumns().addAll(clientIdColumn,clientPrenomColumn,clientNomColumn,clientEmailColumn,clientAdresseColumn,clientTelColumn);
        observableArray.setAll(clientList);
        clientTable.setItems(observableArray);
    }

    private void initElements() {
        // label main title
        hBoxLabelName.getChildren().add(labelMainTitle);

        // Buttons
        vBoxButtons.getChildren().addAll(buttonAdd, buttonUpdate, buttonDelete);

        vBoxmenu.getChildren().addAll(buttonProduit, buttonCategorie, buttonClient,buttonVente,buttonPaiement);
        // add Text/Text fiels to gridPaneData
        gridPaneData.add(labelId, 0, 2);
        gridPaneData.add(labelPrenom, 0, 3);
        gridPaneData.add(labelNom, 0, 4);
        gridPaneData.add(labelEmail, 0, 5);
        gridPaneData.add(labelAdresse, 0,6);
        gridPaneData.add(labelTel, 0, 7);
        
        
        gridPaneData.add(fieldId, 1,2);
        gridPaneData.add(fieldPrenom, 1, 3);
        gridPaneData.add(fieldNom, 1, 4);
        gridPaneData.add(fieldEmail, 1, 5);
        gridPaneData.add(fieldAdresse, 1, 6);
        gridPaneData.add(fieldTel, 1, 7);

        
        gridPaneData.add(buttonAdd, 0, 25);
        gridPaneData.add(buttonUpdate, 1, 25);
        gridPaneData.add(buttonDelete, 2, 25);

        // add client Array
        vBoxclientList.getChildren().addAll(textKeyWord,clientTable);
    }

    private void drawAll() {

    }

    private  void refreshclientTable(){
        fieldId.clear();
        fieldAdresse.clear();
        fieldEmail.clear();
        fieldNom.clear();
        fieldPrenom.clear();
        fieldTel.clear();

        List<Client> clientList =pDAO.getAll();
        observableArray.setAll(clientList);
        clientTable.setItems(observableArray);
    }
    private void initEvents(){

    
    	textKeyWord.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent arg0) {
				String txt = textKeyWord.getText();
				Predicate<Client> filter = prd -> (prd.getNom().toLowerCase().contains(txt.toLowerCase()) || prd.getPrenom().toLowerCase().contains(txt.toLowerCase()));
				clientTable.setItems(observableArray.filtered(filter));
			}
		});
        buttonAdd.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(fieldNom.getLength() !=0 && fieldAdresse.getLength() !=0 && fieldPrenom.getLength() !=0 && fieldTel.getLength() !=0 && fieldEmail.getLength() !=0 ) {
                    Client Prod = new Client(fieldPrenom.getText(), fieldNom.getText(),fieldEmail.getText(),fieldAdresse.getText(),Integer.valueOf(fieldTel.getText()));
                    pDAO.create(Prod);
                    refreshclientTable();
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
                if(fieldNom.getLength() !=0 && fieldAdresse.getLength() !=0 && fieldPrenom.getLength() !=0 && fieldTel.getLength() !=0 && fieldEmail.getLength() !=0 ) {
            	long id = Long.valueOf(fieldId.getText());
                String prenom = fieldPrenom.getText();
                String nom = fieldNom.getText();
                String email = fieldEmail.getText();
                String adresse = fieldAdresse.getText();
               
                int tel = Integer.valueOf(fieldTel.getText());
                Client prod = new Client(id,prenom ,nom , email , adresse , tel);
                pDAO.update(prod);
                List<Client> clientList =pDAO.getAll();
                observableArray.setAll(clientList);
                clientTable.setItems(observableArray);
            	}else {
            		alert.setTitle("Input Error");
                    alert.setHeaderText("Veuillez remplir tous les champs");
                    alert.showAndWait();
            	}
                //clientTable.getSelectionModel().selectedIndexProperty().get()

            }
        });
        
        
        buttonDelete.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(fieldId.getLength() != 0) {
                    pDAO.delete(Long.valueOf(fieldId.getText()));
                    refreshclientTable();
                }
            }
        });
        
        
        clientTable.setOnMouseClicked((e) -> {
        	if(clientTable.getSelectionModel().selectedItemProperty().getValue()!= null) {
            int index = observableArray.indexOf(clientTable.getSelectionModel().selectedItemProperty().get());
            fieldId.clear();
            fieldAdresse.clear();
            fieldEmail.clear();
            fieldNom.clear();
            fieldPrenom.clear();
            fieldTel.clear();
            fieldId.insertText(0,String.valueOf(observableArray.get(index).getId_client()));
            fieldPrenom.insertText(0,String.valueOf(observableArray.get(index).getPrenom()));
            fieldNom.insertText(0,String.valueOf(observableArray.get(index).getNom()));
            fieldEmail.insertText(0,String.valueOf(observableArray.get(index).getEmail()));
            fieldAdresse.insertText(0,String.valueOf(observableArray.get(index).getAdresse()));
            fieldTel.insertText(0,String.valueOf(observableArray.get(index).getTel()));
        	}
        
        });
        
        /*
         * search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<Client> lists=new ArrayList();
                for (Client p:Clients){
                    if (p.getDesignation().toLowerCase().contains(newValue.toLowerCase())||Long.toString(p.getId()).toLowerCase().contains(newValue.toLowerCase())||Double.toString(p.getAge()).toLowerCase().contains(newValue.toLowerCase())){
                        lists.add(p);
                    }
                }
                updateTable(lists);
            }
        });
        */
         

    }
    public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

    @Override
    public void start(Stage window) throws Exception {
        // Init Scene
    	scene = new Scene(root,1500,1000);
        scene.getStylesheets().addAll("css/style.css");
        // Init Panes - Elements
        initPanes();

        BackgroundFill background_fill = new BackgroundFill(Color.valueOf("#93E2E9"), null, null); 
        Background background = new Background(background_fill); 
        root.setBackground(background);

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

        //Modify window details
        window.setScene(scene);
        window.setHeight(windowHeight);
        window.setWidth(windowWidth);
        window.setResizable(false);
      //  window.getIcons().add(new Image("images/icons/window_icon.png"));

        //Display Stage window
        window.show();
    }

    // main function
    public static void main(String[] args) {
        launch(args);
    }

}
