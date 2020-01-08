package Paiement;




import java.util.function.Predicate;

import javax.swing.text.LabelView;


import Carte.Carte;
import Categorie.Categorie;
import Categorie.CategorieDAO;
import Categorie.CategorieDAOIMPL;
import Categorie.FormCategorie;
import Cheque.Cheque;
import Cheque.ChequeDAO;
import Cheque.ChequeDAOIMPL;
import Client.Client;
import Client.ClientDAO;
import Client.ClientDAOIMPL;
import Client.FormClient;
import Compte.Compte;
import DataAccess.*;
import Ligne.FormLigne;
import Ligne.Ligne;
import Ligne.LigneDAO;
import Ligne.LigneDAOIMPL;
import Produit.FormProduit;
import Produit.Produit;
import Produit.ProduitDAO;
import Produit.ProduitDAOIMPL;
import Socket.Banque;
import Vente.FormVente;
import Vente.Vente;
import Vente.VenteDAO;
import Vente.VenteDAOIMPL;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class FormPaiement extends Application {
	int windowHeight = 870;
	 int windowWidth = 1600;
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
    Label labelMainTitle=new Label("Gestion des paiements");
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
  
    // Ligne Array
    
    TableView<Vente> VenteTable =new TableView<>();
    
    ObservableList<Vente> observableVente = FXCollections.observableArrayList();
    VenteDAO vDAO = new VenteDAOIMPL();
    List<Vente> VenteList =vDAO.getAll();
    
    TextField textKeyWord=new TextField();

    // Get Data
    TableView<Paiement> PaiementTable =new TableView<>();
    
    static ObservableList<Paiement> observablePaiement = FXCollections.observableArrayList();
    PaiementDAO PDAO = new PaiementDAOIMPL();

   List<Paiement> PaiementList ;
   
    private static long id  ;
    private static double total ;
    private static double totalPaye =0;
    private static double reste =0;

    
    
    Label labelTotal = new Label("Total : ");
    Label TexfFieldTotal = new Label("");
    Label labelTotalPaye = new Label("Total Payé : ");
    Label TexfFieldTotalPaye = new Label("");
    Label labelReste = new Label("Reste : ");
    Label TexfFieldReste = new Label("");
    

    // Error msg
    Alert alert = new Alert(Alert.AlertType.ERROR);


    //Paiement
    Label labelTypePaiment = new Label("Type du paiement");
    Label labelTitulaire = new Label("Titulaire");
    TextField TextFieldTitulaire = new TextField();
    Label labelMontant = new Label("Montant à payer");
    TextField TextFieldMontant = new TextField();
    Label labelTypeCarte = new Label("type de carte bancaire");
    TextField TextFieldTypeCarte = new TextField();

    Label labelNumeroCarte = new Label("N de carte");
    TextField TextFieldNumeroCarte = new TextField();
    Label labelAnneeExpiration = new Label("Annee d expiration");
    Label labelMoisExpiration = new Label("Mois d expiration");
    Label labelCodeVerification = new Label("Code Verification");

    TextField TextFieldAnneeExpiration = new TextField();
    TextField TextFieldMoisExpiration = new TextField();
    TextField TextFieldCodeVerification = new TextField();
    
    ComboBox<String> comboBoxType = new ComboBox<String>();
    ComboBox<String> comboBoxTypeCarte = new ComboBox<String>();
    VBox vBoxLibelle = new VBox();
    
    //comboBox categorie
    Label labelClient = new Label(" Client : ");
    
    ObservableList<Client> observable = FXCollections.observableArrayList();
    ClientDAO cDAO = new ClientDAOIMPL();
    List<Client> ClientList =cDAO.getAll();
    ComboBox<Client> comboBoxClient = new ComboBox<>(observable);
    ChequeDAO chDAO = new ChequeDAOIMPL();
    
    public static long getId() {
		return id;
	}

	public static void setId(long id) {
		FormPaiement.id = id;
	}
	
	 public static double getTotal() {
			return total;
		}

		public static void setTotal(long total) {
			FormPaiement.total = total;
		}
    
    public FormPaiement() {
    	
    }
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
        
        
        labelTotal.getStyleClass().add("labelTotal");
        labelTotalPaye.getStyleClass().add("labelTotal");
        labelReste.getStyleClass().add("labelTotal");
        TexfFieldReste.getStyleClass().add("labelTotalT");
        TexfFieldTotal.getStyleClass().add("labelTotalT");
        TexfFieldTotalPaye.getStyleClass().add("labelTotalT");


        
        
        // vBoxProduitList style
        root.setCenter(vBoxVenteList);
        vBoxVenteList.getStyleClass().add("vBoxVenteList");
        
        // vBoxProduitList style
        root.setRight(vBoxLigneList);
        vBoxLigneList.getStyleClass().add("vBoxLigneList");
        vBoxLigneList.getChildren().addAll(PaiementTable,labelTotal,TexfFieldTotal,labelTotalPaye,TexfFieldTotalPaye,labelReste,TexfFieldReste);
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
        initTablePaiement();
        initEvents();
        
    
    }

    public void initItems(long id) {

        PaiementList= PDAO.getAll(id);

       if(PaiementList!= null) {
    	   
    	   observablePaiement.setAll(PaiementList);
    	   PaiementTable.setItems(observablePaiement);

    	   
       }
       else {
    	  
  			 observablePaiement.clear();
  		
    	   return ;
    	
       }
       System.out.println("ID vente selectionné : " + id);
	  
    }
    
   private void initTablePaiement() {
	//   textKeyWord.setMaxWidth(1000);
	   PaiementTable.setMaxWidth(650);
	   PaiementTable.setMaxHeight(300);
	   
	   TableColumn<Paiement, Long> PaiementIdColumn =new TableColumn<>("id");
	   PaiementIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_paiement"));
	   PaiementIdColumn.setPrefWidth(35);
   

       
	   TableColumn<Paiement, Double> montantColumn =new TableColumn<>("Montant");
	   montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant_paiment"));
	   montantColumn.setPrefWidth(100);
     

       
                
       TableColumn<Paiement, String> PaiementTypeColumn =new TableColumn<>("Type");
       PaiementTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type_paiement"));
       PaiementTypeColumn.setPrefWidth(100);


       TableColumn<Paiement, Cheque> PaiementChequeColumn =new TableColumn<>("N cheque");
       PaiementChequeColumn.setCellValueFactory(new PropertyValueFactory<>("cheque"));
       PaiementChequeColumn.setPrefWidth(100);
		
		TableColumn<Paiement, Date> DatePaiementColumn =new TableColumn<>("Date");
		DatePaiementColumn.setCellValueFactory(new PropertyValueFactory<>("date_paiement"));
		DatePaiementColumn.setPrefWidth(200);
	  
		TableColumn<Paiement, String> EtatColumn =new TableColumn<>("Etat");
		EtatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
		EtatColumn.setPrefWidth(100);
	  


		

 

       PaiementTable.getColumns().addAll(PaiementIdColumn,montantColumn,PaiementTypeColumn,PaiementChequeColumn,DatePaiementColumn,EtatColumn);
       
      // observableLigne.setAll(LigneList);
       PaiementTable.setItems(observablePaiement);
       
      
        
   }
    
    
   private void initTableVente() {
	  textKeyWord.setMaxWidth(650);
	  VenteTable.setMaxWidth(650);
	  VenteTable.setMaxHeight(300);

    	TableColumn<Vente, Long> venteIdColumn =new TableColumn<>("id");
    	venteIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_vente"));
    	venteIdColumn.setPrefWidth(50);
    	//venteIdColumn.prefWidthProperty().bind(VenteTable.widthProperty().divide(100 / 15));


         TableColumn<Vente, String> venteClientColumn =new TableColumn<>("Client");
         venteClientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
         venteClientColumn.setPrefWidth(200);
         //venteClientColumn.prefWidthProperty().bind(VenteTable.widthProperty().divide(100 / 50));

         
         TableColumn<Vente, Double> venteTotalColumn =new TableColumn<>("Total");
         venteTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
         venteTotalColumn.setPrefWidth(200);
      //   venteTotalColumn.prefWidthProperty().bind(VenteTable.widthProperty().divide(100 / 20));

         
                  
         TableColumn<Vente, Date> venteDateVenteColumn =new TableColumn<>("Date");
         venteDateVenteColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
         venteDateVenteColumn.setPrefWidth(200);
      //   venteDateVenteColumn.prefWidthProperty().bind(VenteTable.widthProperty().divide(100 /20));

        


        
         
         VenteTable.getColumns().addAll(venteIdColumn,venteClientColumn,venteTotalColumn,venteDateVenteColumn);
         observableVente.setAll(VenteList);
         VenteTable.setItems(observableVente);
         
         
     
    }

   

   public  ObservableList<Paiement> getPayments() {
       return observablePaiement == null ? FXCollections.observableArrayList(new PaiementDAOIMPL().getAll()) : observablePaiement;
   }
    private void initElements() {
        // label main title
        hBoxLabelName.getChildren().add(labelMainTitle);
       

        vBoxmenu.getChildren().addAll(buttonProduit, buttonCategorie, buttonClient,buttonVente,buttonPaiement);

        // add Ligne Array
        TextFieldMontant.setMaxWidth(100);
        TextFieldAnneeExpiration.setMaxWidth(100);
        TextFieldMoisExpiration.setMaxWidth(100);
        TextFieldNumeroCarte.setMaxWidth(200);
        TextFieldTitulaire.setMaxWidth(200);
        TextFieldCodeVerification.setMaxWidth(50);
        TextFieldTypeCarte.setMaxWidth(200);
        
        observable.setAll(ClientList);
        comboBoxClient.setItems(observable);
        
        
        comboBoxType.getItems().addAll("Cheque",  "En ligne","Traites");
        comboBoxTypeCarte.getItems().addAll("VISA",  "MASTER CARD");


        vBoxVenteList.getChildren().addAll(textKeyWord,VenteTable,buttonAdd,labelClient,comboBoxClient,labelTypePaiment,comboBoxType);
  
    }

    private void drawAll() {

    }

    private  void refreshVenteTable(){
    
        List<Vente> VenteList =vDAO.getAll();
        observableVente.setAll(VenteList);
        VenteTable.setItems(observableVente);
    }

    private void initEvents(){
    	
    	comboBoxType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
	               	if(comboBoxType.getValue()=="Traites") {
	               		vBoxVenteList.getChildren().removeAll(labelNumeroCarte,TextFieldNumeroCarte,labelAnneeExpiration,TextFieldAnneeExpiration,
	   	                  	 labelMoisExpiration,TextFieldMoisExpiration,labelCodeVerification,TextFieldCodeVerification);
	   		               	 
	               		vBoxVenteList.getChildren().removeAll(labelTypeCarte,comboBoxTypeCarte,labelTitulaire,TextFieldTitulaire,labelMontant,TextFieldMontant);
	               		vBoxVenteList.getChildren().addAll(labelMontant,TextFieldMontant);
	               	}
	               	if(comboBoxType.getValue()=="Cheque") {
	               		vBoxVenteList.getChildren().removeAll(labelMontant,TextFieldMontant);
		               	 
	               		vBoxVenteList.getChildren().removeAll(labelTypeCarte,comboBoxTypeCarte,labelNumeroCarte,TextFieldNumeroCarte,labelAnneeExpiration,TextFieldAnneeExpiration,
	               				labelMoisExpiration,TextFieldMoisExpiration,labelCodeVerification,TextFieldCodeVerification);
		               	 
	               		vBoxVenteList.getChildren().addAll(labelTitulaire,TextFieldTitulaire,labelMontant,TextFieldMontant);
	                }
	               
	               	if(comboBoxType.getValue()=="En ligne") {
	               		vBoxVenteList.getChildren().removeAll(labelTitulaire,TextFieldTitulaire,labelMontant,TextFieldMontant);
	               		vBoxVenteList.getChildren().removeAll(labelMontant,TextFieldMontant);
	               	 
	               		vBoxVenteList.getChildren().addAll(labelTypeCarte,comboBoxTypeCarte,labelNumeroCarte,TextFieldNumeroCarte,labelAnneeExpiration,TextFieldAnneeExpiration,
	                  	 labelMoisExpiration,TextFieldMoisExpiration,labelCodeVerification,TextFieldCodeVerification);
	                }
	               	
            }
        });
    	
    	

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
    	
    	
  

	 VenteTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
       
		 

   	TextFieldTitulaire.clear();
   	TextFieldMontant.clear(); 	
   	TextFieldTypeCarte.clear();
   	 TextFieldNumeroCarte.clear();
   	  TextFieldAnneeExpiration.clear();
   	   TextFieldMoisExpiration.clear();
   	   TextFieldCodeVerification.clear();
			
   	   
		 
	   	 labelTypePaiment.setDisable(false);
    	  labelTitulaire.setDisable(false);
    	TextFieldTitulaire.setDisable(false);
    	  labelMontant.setDisable(false);
    	TextFieldMontant.setDisable(false);
    	labelTypeCarte.setDisable(false);         	
    	TextFieldTypeCarte.setDisable(false);

    labelNumeroCarte.setDisable(false);
    	 TextFieldNumeroCarte.setDisable(false);
    	   labelAnneeExpiration.setDisable(false);
    	 labelMoisExpiration.setDisable(false);
    	 labelCodeVerification.setDisable(false);

    	  TextFieldAnneeExpiration.setDisable(false);
    	   TextFieldMoisExpiration.setDisable(false);
    	   TextFieldCodeVerification.setDisable(false);
			
       		
	//	 totalPaye = 0 ;
	//	 reste = 0 ;
         initItems(newValue.getId_vente());
         id = newValue.getId_vente() ;
         
         
         TexfFieldTotal.setText(String.valueOf(newValue.getTotal()));
        
         
         double totalPaye=0,reste =0 ;
         
         total = newValue.getTotal();
         
        
         for (Paiement paiement : observablePaiement) {
             totalPaye+=paiement.getMontant_paiment();
             
      	}
      	//tot1 = tot + Double.parseDouble(TextFieldMontant.getText());
      	reste = total - totalPaye ;
      	TexfFieldTotalPaye.setText(String.valueOf(totalPaye));
      	TexfFieldReste.setText(String.valueOf(reste));
      	
      	if(reste<=0) {
     		
        	 labelTypePaiment.setDisable(true);
        	  labelTitulaire.setDisable(true);
        	TextFieldTitulaire.setDisable(true);
        	  labelMontant.setDisable(true);
        	TextFieldMontant.setDisable(true);
        	labelTypeCarte.setDisable(true);         	
        	TextFieldTypeCarte.setDisable(true);

        labelNumeroCarte.setDisable(true);
        	 TextFieldNumeroCarte.setDisable(true);
        	   labelAnneeExpiration.setDisable(true);
        	 labelMoisExpiration.setDisable(true);
        	 labelCodeVerification.setDisable(true);

        	  TextFieldAnneeExpiration.setDisable(true);
        	   TextFieldMoisExpiration.setDisable(true);
        	   TextFieldCodeVerification.setDisable(true);
        	    
       		
       	}
      	
      	
      	if(totalPaye>total) {
      		totalPaye=total ;
 			
         	TexfFieldTotalPaye.setText(String.valueOf(total));
          	TexfFieldReste.setText(String.valueOf(0));
         	
         	
         	
          	

 			return ;
 		}
      	
		 
      
      	
        });


    	
	 buttonAdd.setOnAction(new EventHandler<ActionEvent>(){
		 
         @Override
         public void handle(ActionEvent event) {
        	 totalPaye = 0 ;
    		 reste = 0 ;
        	 Client c ;
         	c= comboBoxClient.getSelectionModel().getSelectedItem();
         	
         	Vente vente = vDAO.getOne(id);
         
         	String etat ="en cours";
         
            double totalPaye=0,reste =0 ;

            
        
         
         	for (Paiement paiement : observablePaiement) {
                totalPaye+=paiement.getMontant_paiment();
         	}
         	 
         	
         	 
         	if(comboBoxType.getValue()=="Traites") {
         		totalPaye+=Double.valueOf(TextFieldMontant.getText());
         		reste = total - totalPaye ;
             
         		
         		
         		if(totalPaye>total) {
              		

         			Alert alert4 = new Alert(AlertType.INFORMATION);
                 	alert4.setTitle("Erreur !");
                 	
                 	alert4.setContentText("Valeur est plus grande que le montant total !!");

                 	alert4.showAndWait();
                 	
                 	//TexfFieldTotalPaye.setText(String.valueOf(totalPaye));
                  	//TexfFieldReste.setText(String.valueOf(0));
                  	
                 	return ;
         			
         		}
         		TexfFieldTotalPaye.setText(String.valueOf(totalPaye));
             	TexfFieldReste.setText(String.valueOf(reste));
         		
         		if(reste<=0) {
             		
                	 labelTypePaiment.setDisable(true);
                	  labelTitulaire.setDisable(true);
                	TextFieldTitulaire.setDisable(true);
                	  labelMontant.setDisable(true);
                	TextFieldMontant.setDisable(true);
                	labelTypeCarte.setDisable(true);         	
                	TextFieldTypeCarte.setDisable(true);

                labelNumeroCarte.setDisable(true);
                	 TextFieldNumeroCarte.setDisable(true);
                	   labelAnneeExpiration.setDisable(true);
                	 labelMoisExpiration.setDisable(true);
                	 labelCodeVerification.setDisable(true);

                	  TextFieldAnneeExpiration.setDisable(true);
                	   TextFieldMoisExpiration.setDisable(true);
                	   TextFieldCodeVerification.setDisable(true);
                	    
                	   etat="payé";
               		
               	}
         		
         		Paiement pai = new Paiement(Double.valueOf(TextFieldMontant.getText()), comboBoxType.getValue(), LocalDateTime.now(),vente,etat);
                 observablePaiement.add(pai);
                 PDAO.create(pai);
                 
                alert = new Alert(AlertType.INFORMATION);
               	alert.setTitle("Succès !");
               	
               	alert.setContentText("Ajout avec succès :)  ");

               	alert.showAndWait();
               
         	}
         	
         	if(comboBoxType.getValue()=="Cheque") {
         		
         		totalPaye+=Double.valueOf(TextFieldMontant.getText());
         		reste = total - totalPaye ;
        
             	
         		if(totalPaye>total) {
         			Alert alert4 = new Alert(AlertType.INFORMATION);
                 	alert4.setTitle("Erreur !");
                 	
                 	alert4.setContentText("Valeur est plus grande que le montant total !! ");

                 	alert4.showAndWait();
                 	
                 	//TexfFieldTotalPaye.setText(String.valueOf(total));
                  	//TexfFieldReste.setText(String.valueOf(0));
                  	
                 	return ;
         			
         		}
             	TexfFieldTotalPaye.setText(String.valueOf(totalPaye));
             	TexfFieldReste.setText(String.valueOf(reste));
             	if(reste<=0) {
             		
               	 labelTypePaiment.setDisable(true);
               	  labelTitulaire.setDisable(true);
               	TextFieldTitulaire.setDisable(true);
               	  labelMontant.setDisable(true);
               	TextFieldMontant.setDisable(true);
               	labelTypeCarte.setDisable(true);         	
               	TextFieldTypeCarte.setDisable(true);

               labelNumeroCarte.setDisable(true);
               	 TextFieldNumeroCarte.setDisable(true);
               	   labelAnneeExpiration.setDisable(true);
               	 labelMoisExpiration.setDisable(true);
               	 labelCodeVerification.setDisable(true);

               	  TextFieldAnneeExpiration.setDisable(true);
               	   TextFieldMoisExpiration.setDisable(true);
               	   TextFieldCodeVerification.setDisable(true);
               	   
               	etat="payé";
              		
              	}
             	
         		Cheque cheque = new Cheque(0,TextFieldTitulaire.getText());
         		chDAO.create(cheque);
         		
         		Cheque ch = chDAO.getLast();
                Paiement pai2 = new Paiement(0, Double.parseDouble(TextFieldMontant.getText()),  comboBoxType.getValue(), ch, LocalDateTime.now(), vente,etat);
                observablePaiement.add(pai2);
                
        	
              
                 PDAO.create(pai2);
                 
                 
                 
                 
                alert = new Alert(AlertType.INFORMATION);
              	alert.setTitle("Succès !");
              	
              	alert.setContentText("Ajout avec succès :)  ");

              	alert.showAndWait();
              	
         	}
         	
         	if(comboBoxType.getValue()=="En ligne") {
         		
         	
         		reste = total - totalPaye ;
            
         
                 Carte carte = new Carte(comboBoxTypeCarte.getValue(),Integer.parseInt(TextFieldNumeroCarte.getText()),
                 		 Integer.parseInt(TextFieldAnneeExpiration.getText().toString()),
                 		Integer.parseInt(TextFieldMoisExpiration.getText().toString()),                        
                         Integer.parseInt(TextFieldCodeVerification.getText())
                   );
                 
              
                 if(totalPaye>total) {
               		

          			Alert alert4 = new Alert(AlertType.INFORMATION);
                  	alert4.setTitle("Erreur !");
                  	
                  	alert4.setContentText("Valeur est plus grande que le montant total !!");

                  	alert4.showAndWait();
                  	
                  	//TexfFieldTotalPaye.setText(String.valueOf(totalPaye));
                   //	TexfFieldReste.setText(String.valueOf(0));
                   	
                  	return ;
          			
          		}
             
                 
              	TexfFieldTotalPaye.setText(String.valueOf(totalPaye));
             	TexfFieldReste.setText(String.valueOf(reste));
                 
                 
                 Compte compte = new Compte(0,reste,c , carte);
                
                 // send instance to remote server
                 Banque SocketBanque = new Banque(compte);
                 // get response
                 int success = SocketBanque.sendPayment();
                 Alert alert = new Alert(AlertType.INFORMATION);
                 // persist payment
                 switch (success) {
                     case 200:
                    	 etat = "payé";
                    	 Paiement pai = new Paiement(0, reste, comboBoxType.getValue(), LocalDateTime.now(), vente,etat) ;
                         observablePaiement.add(pai);
                         PDAO.create(pai);
                         
                        alert = new Alert(AlertType.INFORMATION);
                     	alert.setTitle("Succès !");
                     	
                     	alert.setContentText("Transmition avec succès ");

                     	alert.showAndWait();
                         break;
                     case 400:
                         alert = new Alert(AlertType.INFORMATION);

                     	alert.setTitle("Erreur !");
                     	
                     	alert.setContentText("Votre carte ne contient le montant demandé");

                     	alert.showAndWait();
                         return;
                     case 404:
                      alert = new Alert(AlertType.INFORMATION);
                     	alert.setTitle("Erreur !");
                     	
                     	alert.setContentText("Les données de la carte sont incorrectes");

                     	alert.showAndWait();
              
                         return;
                     case 500:
                     alert = new Alert(AlertType.INFORMATION);
                     	alert.setTitle("Erreur !");
                     	
                     	alert.setContentText("Une erreur s'est produite, merci de réssayer plus tard :) ");

                     	alert.showAndWait();
              
                         return;
                 }
         		
         		
         	}
         	
         	
         	
      //   	Vente vente = (new Vente(total,LocalDateTime.now(),getPayments()));
         	
    
         	
         	
				           
         	}
         
     });
    	
    	
    	

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
