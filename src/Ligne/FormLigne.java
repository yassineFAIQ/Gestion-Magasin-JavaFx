package Ligne;


import java.util.function.Predicate;

import javax.swing.text.LabelView;



import Carte.Carte;
import Categorie.Categorie;
import Categorie.CategorieDAO;
import Categorie.CategorieDAOIMPL;
import Categorie.FormCategorie;
import Cheque.Cheque;
import Client.Client;
import Client.ClientDAO;
import Client.ClientDAOIMPL;
import Client.FormClient;
import Compte.Compte;
import DataAccess.*;
import Paiement.FormPaiement;
import Paiement.Paiement;
import Paiement.PaiementDAO;
import Paiement.PaiementDAOIMPL;
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
import java.util.Collection;
import java.util.List;
import java.util.Optional;



public class FormLigne extends Application {
	int windowHeight = 950;
	 int windowWidth = 1300;
    //Scene
    public Scene scene;
    
    // Conteneurs
    BorderPane root = new BorderPane();

    
    
    
    VBox vBoxButtons = new VBox();
    VBox vBoxProduitList = new VBox();
    VBox vBoxLigneList = new VBox();
    HBox hBoxLabelName = new HBox();
    VBox VboxClient = new VBox();
    GridPane gridPaneData = new GridPane();
    //Controles/elements
    // label main title
    Label labelMainTitle=new Label("Gestion des ligne de commandes");
    //Buttons of left border
    	
    Button buttonAjouter = new Button("Add");
    
    Button buttonAdd = new Button("Ajouter");
    Button buttonUpdate = new Button("Modifier");
    Button buttonDelete = new Button("supprimer");


    Label labelClient = new Label(" Client : ");
    

    // Ligne Array
    
    static TableView<Ligne> LigneTable =new TableView<>();
    
    static ObservableList<Ligne> observableLigne = FXCollections.observableArrayList();
    

    TextField textKeyWord=new TextField();


    static ObservableList<Paiement> observablePaiement = FXCollections.observableArrayList();


    
    
    
   

    // Error msg
    Alert alert = new Alert(Alert.AlertType.ERROR);

    // Produit
    TableView<Produit> ProduitTable =new TableView<>();


    ObservableList<Produit> observableProduit = FXCollections.observableArrayList();
    ProduitDAO pDAO = new ProduitDAOIMPL();
    List<Produit> ProduitList =pDAO.getAll();
    
    
    //comboBox categorie
    ObservableList<Client> observable = FXCollections.observableArrayList();
    ClientDAO cDAO = new ClientDAOIMPL();
    List<Client> ClientList =cDAO.getAll();
    ComboBox<Client> comboBoxClient = new ComboBox<>(observable);
    
    
    VenteDAO vDAO = new VenteDAOIMPL();
  
   
    
    
    PaiementDAO paiDAO = new PaiementDAOIMPL();

    
    //Menu
    VBox vBoxmenu = new VBox();
    Button buttonProduit = new Button("Produit");
    Button buttonCategorie = new Button("Categorie");
    Button buttonClient = new Button("Client");
    Button buttonVente = new Button("Vente");
    Button buttonPaiement = new Button("Paiement");
    

    //Payment
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



    
    
    private void initPanes() {
       // root.setCenter(VboxClient);
    //	HboxClient.setAlignment(Pos.BOTTOM_RIGHT);
        VboxClient.setSpacing(15);

        // hBoxLabelName style
        hBoxLabelName.setAlignment(Pos.CENTER);
        hBoxLabelName.setPrefSize(300, 80);
        hBoxLabelName.getStyleClass().add("topHBox");
        
        // vBoxButtons style
        root.setBottom(vBoxButtons);
        vBoxButtons.setAlignment(Pos.BOTTOM_CENTER);
        root.setCenter(vBoxProduitList);

        root.setLeft(vBoxmenu);
        vBoxmenu.getStyleClass().add("leftVBox");
        vBoxmenu.setPrefSize(200, 100);
        vBoxmenu.setSpacing(20);
        


        
        
        
        vBoxButtons.getStyleClass().add("leftVBox");
        vBoxButtons.setPrefSize(200, 50);
        vBoxButtons.setSpacing(15);
        // gridPaneData style
        /*
        root.setCenter(gridPaneData);
        gridPaneData.getStyleClass().add("gridPaneData");
        gridPaneData.setVgap(10);
        gridPaneData.setHgap(10);
*/
        // vBoxProduitList style
      //  vBoxProduitList.getStyleClass().add("vBoxProduitList");
        
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
        
        
        
        labelTotal.getStyleClass().add("labelTotal");
        labelTotalPaye.getStyleClass().add("labelTotal");
        labelReste.getStyleClass().add("labelTotal");
        TexfFieldReste.getStyleClass().add("labelTotalT");
        TexfFieldTotal.getStyleClass().add("labelTotalT");
        TexfFieldTotalPaye.getStyleClass().add("labelTotalT");
        
        
        TextFieldMontant.setMaxWidth(100);
        TextFieldAnneeExpiration.setMaxWidth(100);
        TextFieldMoisExpiration.setMaxWidth(100);
        TextFieldNumeroCarte.setMaxWidth(200);
        TextFieldTitulaire.setMaxWidth(200);
        TextFieldCodeVerification.setMaxWidth(50);
        TextFieldTypeCarte.setMaxWidth(200);

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
        
        
        buttonCategorie.getStyleClass().add("menu");
        buttonClient.getStyleClass().add("menu");
        buttonProduit.getStyleClass().add("menu");
        buttonVente.getStyleClass().add("menu");
        buttonPaiement.getStyleClass().add("menu");
     
        labelClient.getStyleClass().add("labelText");
        labelTypePaiment.getStyleClass().add("labelText");

        observableProduit.setAll(ProduitList);
        
        
        observable.setAll(ClientList);
        comboBoxClient.setItems(observable);
        comboBoxTypeCarte.getItems().addAll("VISA",  "MASTER CARD");
        comboBoxType.getItems().addAll("Espece", "Cheque","Traites","En ligne");
        initElements();
        initTableProduit();
        initTableLigne();
        initEvents();
    }


    public static void add(Ligne ligne) {
        int index;
        //PaymentsController.addToTotal(orderLine.getTotal());
        for (Ligne l: observableLigne) {
            if((l.getProduit().getId_produit()) == (ligne.getProduit().getId_produit())) {
                l.setQte_ligne(l.getQte_ligne() + ligne.getQte_ligne());
                l.setSousTotal(l.getQte_ligne()*l.getProduit().getPrix_vente());
                Ligne ligne1 = new Ligne(l);
                index = observableLigne.indexOf(l);
               observableLigne.remove(l);
                observableLigne.add(index,ligne1);
               System.out.println(l.getQte_ligne());
               
               return;
            }
             
        }
        observableLigne.add(ligne);
        
       

    }
    /*

    public static void addP(Paiement paiement) {
        int index;
        //PaymentsController.addToTotal(orderLine.getTotal());
        for (Paiement l: observablePaiement) {

                l.set(l.getQte_ligne() + ligne.getQte_ligne());
                l.setSousTotal(l.getQte_ligne()*l.getProduit().getPrix_vente());
                Ligne ligne1 = new Ligne(l);
                index = observableLigne.indexOf(l);
               observableLigne.remove(l);
                observableLigne.add(index,ligne1);
               System.out.println(l.getQte_ligne());
               
               return;
            
             
        }
        observablePaiement.add(paiement);
        

    }
    
    
    */
    public  int TextInputDialog() {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Quantité");
        dialog.setHeaderText(null);
        dialog.setContentText("Entrer la quantité : ");
        dialog.setOnCloseRequest(e-> dialog.hide());
        while(true) {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                try {
                    int qty = Integer.parseInt(result.get());
                    if(qty <= 0) continue;

                    return qty;
                } catch (NumberFormatException e) {
                    // nothing
                }
            } else break;
        }

        return -1;
    }

   private void initTableProduit() {
	   ProduitTable.setMaxWidth(600);
	   textKeyWord.setMaxWidth(600);

    	TableColumn<Produit, Long> productIdColumn =new TableColumn<>("id");
         productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
         productIdColumn.setPrefWidth(100);



         TableColumn<Produit, String> productDesignationColumn =new TableColumn<>("designation");
         productDesignationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        productDesignationColumn.setPrefWidth(238);
  

         
         TableColumn<Produit, Integer> productQteColumn =new TableColumn<>("qte");
         productQteColumn.setCellValueFactory(new PropertyValueFactory<>("qte"));
         productQteColumn.setPrefWidth(100);
 

         
                  
         TableColumn<Produit, Long> productPrixVenteColumn =new TableColumn<>("prix");
         productPrixVenteColumn.setCellValueFactory(new PropertyValueFactory<>("prix_vente"));
         productPrixVenteColumn.setPrefWidth(100);
  

         TableColumn addProductCol = new TableColumn<>("");
         addProductCol.setCellFactory(ButtonCell.forTableColumn("", (Produit p) -> {
        	 
            int qte = TextInputDialog();
             if (qte == -1) return p;

        	 Ligne ligne  = new Ligne(0, p,qte,p.getPrix_vente()*qte );
             add(ligne);
             
           

             double total = 0.0;
        	 for (Ligne ligne1 : observableLigne) {
                 total += ligne1.getSousTotal();
                 

             }
        	 
        	 TexfFieldTotal.setText(String.valueOf(total));
        	 TexfFieldTotalPaye.setText(String.valueOf(0));
           	TexfFieldReste.setText(String.valueOf(0));
             return p;
         }));

         addProductCol.setPrefWidth(60);



         
      
         
         
         ProduitTable.getColumns().addAll(productIdColumn,productDesignationColumn,productQteColumn,productPrixVenteColumn,addProductCol);
         observableProduit.setAll(ProduitList);
         ProduitTable.setItems(observableProduit);
         
         
     
    }



   private void initTableLigne() {
    	
	   
	   LigneTable.setMaxWidth(400);

	//   vBoxLigneList.setAlignment(Pos.);
    	  TableColumn<Ligne, Produit> LigneDesignationColumn =new TableColumn<>("Produit");
          LigneDesignationColumn.setCellValueFactory(new PropertyValueFactory<>("produit"));
          LigneDesignationColumn.setPrefWidth(100);
       //    LigneDesignationColumn.prefWidthProperty().bind(LigneTable.widthProperty().divide(100 / 50));

          
          TableColumn<Ligne, Integer> LigneQteColumn =new TableColumn<>("Qte");
          LigneQteColumn.setCellValueFactory(new PropertyValueFactory<>("qte_ligne"));
          LigneQteColumn.setPrefWidth(150);
         //     LigneQteColumn.prefWidthProperty().bind(LigneTable.widthProperty().divide(100 / 20));

          
                   
          TableColumn<Ligne, Double> LigneSousTotalVenteColumn =new TableColumn<>("Sous total");
          LigneSousTotalVenteColumn.setCellValueFactory(new PropertyValueFactory<>("sousTotal"));
          LigneSousTotalVenteColumn.setPrefWidth(150);
     //       LigneSousTotalVenteColumn.prefWidthProperty().bind(LigneTable.widthProperty().divide(100 /30));



          LigneTable.getColumns().addAll(LigneDesignationColumn,LigneQteColumn,LigneSousTotalVenteColumn);
       //   observableLigne.setAll(LigneList);
          LigneTable.setItems(observableLigne);
          
    	


    }

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
       

        vBoxmenu.getChildren().addAll(buttonProduit, buttonCategorie, buttonClient,buttonVente,buttonPaiement);
        // Buttons
        vBoxButtons.getChildren().addAll(buttonAdd);
        VboxClient.getChildren().add(labelClient);
        
        

        
        // add Ligne Array
        vBoxProduitList.getChildren().addAll(textKeyWord,ProduitTable,labelClient,comboBoxClient,labelTypePaiment,comboBoxType);
       
     


        
        vBoxLigneList.getChildren().addAll(LigneTable,labelTotal,TexfFieldTotal,labelTotalPaye,TexfFieldTotalPaye,labelReste,TexfFieldReste);
   
    }

    private void drawAll() {

    }

    public ObservableList<Ligne> getLignes() {
        return observableLigne == null ? FXCollections.observableArrayList() : observableLigne;
    }
    public  ObservableList<Paiement> getPayments() {
        return observablePaiement == null ? FXCollections.observableArrayList(new PaiementDAOIMPL().getAll()) : observablePaiement;
    }
    private  void refreshProductTable(){
    	/*
        fieldId.clear();
        fieldQte.clear();
        fieldPrixAchat.clear();
        fieldPrixVente.clear();
        fieldDesignation.clear();
        
        */
        List<Produit> ProduitList =pDAO.getAll();
        observableProduit.setAll(ProduitList);
        ProduitTable.setItems(observableProduit);
    }
    private  void refreshLigneTable(){
    	
    	/*
        fieldId.clear();
        fieldQte.clear();
        fieldPrixAchat.clear();
        fieldPrixVente.clear();
        fieldDesignation.clear();
        
       
        List<Ligne> LigneList =lDAO.getAll();
        observableLigne.setAll(LigneList);
        LigneTable.setItems(observableLigne);
         */
    }
    private void initEvents(){
    	
    	
    	
    	   comboBoxType.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent t) {
	               	if(comboBoxType.getValue()=="Espece") {
	               		vBoxProduitList.getChildren().removeAll(labelNumeroCarte,TextFieldNumeroCarte,labelAnneeExpiration,TextFieldAnneeExpiration,
	               				labelMoisExpiration,TextFieldMoisExpiration,labelCodeVerification,TextFieldCodeVerification,labelMontant,TextFieldMontant);
	   		               	 
	   	                  	 vBoxProduitList.getChildren().removeAll(labelTypeCarte,comboBoxTypeCarte,labelTitulaire,TextFieldTitulaire,labelMontant,TextFieldMontant);
	              
	               	}
	               	
	               	if(comboBoxType.getValue()=="Traites") {
	               		vBoxProduitList.getChildren().removeAll(labelNumeroCarte,TextFieldNumeroCarte,labelAnneeExpiration,TextFieldAnneeExpiration,
	               				labelMoisExpiration,TextFieldMoisExpiration,labelCodeVerification,TextFieldCodeVerification,labelMontant,TextFieldMontant);
	   		               	 
	   	                  	 vBoxProduitList.getChildren().removeAll(labelTypeCarte,comboBoxTypeCarte,labelTitulaire,TextFieldTitulaire,labelMontant,TextFieldMontant);
	               	 vBoxProduitList.getChildren().addAll(labelMontant,TextFieldMontant);
	               	}
	               	if(comboBoxType.getValue()=="Cheque") {
		               	 vBoxProduitList.getChildren().removeAll(labelMontant,TextFieldMontant);
		               	 
		               	 vBoxProduitList.getChildren().removeAll(labelTypeCarte,comboBoxTypeCarte,labelNumeroCarte,TextFieldNumeroCarte,labelAnneeExpiration,TextFieldAnneeExpiration,
		               			labelMoisExpiration,TextFieldMoisExpiration,labelCodeVerification,TextFieldCodeVerification);
		               	 
	                  	 vBoxProduitList.getChildren().addAll(labelTitulaire,TextFieldTitulaire,labelMontant,TextFieldMontant);
	                }
	               
	               	if(comboBoxType.getValue()=="En ligne") {
	               		 vBoxProduitList.getChildren().removeAll(labelTitulaire,TextFieldTitulaire,labelMontant,TextFieldMontant);
	               		 vBoxProduitList.getChildren().removeAll(labelMontant,TextFieldMontant);
	               	 
	                  	 vBoxProduitList.getChildren().addAll(labelTypeCarte,comboBoxTypeCarte,labelNumeroCarte,TextFieldNumeroCarte,labelAnneeExpiration,TextFieldAnneeExpiration,
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
				Predicate<Produit> filter = prd -> (prd.getDesignation().toLowerCase().contains(txt.toLowerCase()));
				ProduitTable.setItems(observableProduit.filtered(filter));
			}
		});
    	
    	
        buttonAdd.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	Client c ;
            	c= comboBoxClient.getSelectionModel().getSelectedItem();
            
            	String etat = "en cours";
            	
            	double totalPaye=0,reste =0 ,total=0;
            	
            	
            	 for (Ligne ligne : observableLigne) {
                     total += ligne.getSousTotal();
                     

                 }
            	 
            	// TexfFieldTotal.setText(String.valueOf(total));
            	
            	
            	 for (Paiement paiement : observablePaiement) {
                     totalPaye+=paiement.getMontant_paiment();
              	}
             // 	 totalPaye += Double.parseDouble(TextFieldMontant.getText());
              
              
              	
            //	tot1 = tot + Double.parseDouble(TextFieldMontant.getText());
            	 
            	if(comboBoxType.getValue()=="Espece") {
            		
            		etat="payé";
            		TexfFieldTotalPaye.setText(String.valueOf(total));
                  	TexfFieldReste.setText(String.valueOf(0));
                    observablePaiement.add(new Paiement(total, comboBoxType.getValue(), LocalDateTime.now(),null,etat));
                    
               
                  
            	}
            	if(comboBoxType.getValue()=="Traites") {
            		
            		totalPaye+=Double.valueOf(TextFieldMontant.getText());
             		reste = total - totalPaye ;
             		
            		if(totalPaye>total) {
             			Alert alert4 = new Alert(AlertType.INFORMATION);
                     	alert4.setTitle("Erreur !");
                     	
                     	alert4.setContentText("Le paiement a été effectué");

                     	alert4.showAndWait();
                     
                     	return ;
             			
             		}
            		TexfFieldTotalPaye.setText(String.valueOf(totalPaye));
                 	TexfFieldReste.setText(String.valueOf(reste));
                 	if(reste<=0) {
                 		etat="payé";
                 	}
                  	
            	
                    observablePaiement.add(new Paiement( Double.valueOf(TextFieldMontant.getText()), comboBoxType.getValue(), LocalDateTime.now(),null,etat));
               
                  
            	}
            	
            	if(comboBoxType.getValue()=="Cheque") {
            		if(totalPaye>total) {
             			Alert alert4 = new Alert(AlertType.INFORMATION);
                     	alert4.setTitle("Erreur !");
                     	
                     	alert4.setContentText("Le paiement a été effectué");

                     	alert4.showAndWait();
                     	TexfFieldTotalPaye.setText(String.valueOf(totalPaye));
                      	TexfFieldReste.setText(String.valueOf(0));
                     	return ;
             			
             		}
            		
            		TexfFieldTotalPaye.setText(String.valueOf(TextFieldMontant.getText()));
            		reste = total - Double.valueOf(TextFieldMontant.getText()) ;
                  	TexfFieldReste.setText(String.valueOf(reste));
                  	if(reste<=0) {
                  		etat="payé";
                  	}
                  	
            		Cheque cheque = new Cheque(0, TextFieldTitulaire.getText());
                    
                    observablePaiement.add(new Paiement(0, Double.parseDouble(TextFieldMontant.getText()),  comboBoxType.getValue(), cheque, LocalDateTime.now(), null,etat));
            	}
            	
            	if(comboBoxType.getValue()=="En ligne") {
            		
            
                    Carte carte = new Carte(comboBoxTypeCarte.getValue(),Integer.parseInt(TextFieldNumeroCarte.getText()),
                    		 Integer.parseInt(TextFieldAnneeExpiration.getText().toString()),
                    		Integer.parseInt(TextFieldMoisExpiration.getText().toString()),                        
                            Integer.parseInt(TextFieldCodeVerification.getText())
                      );
                    
                 

                    Client c1 ;
                	c1= comboBoxClient.getSelectionModel().getSelectedItem();
                    
                    
                    Compte compte = new Compte(0,total,c1 , carte);
                   
                    // send instance to remote server
                    Banque SocketBanque = new Banque(compte);
                    // get response
                    int success = SocketBanque.sendPayment();
                    Alert alert = new Alert(AlertType.INFORMATION);
                    // persist payment
                    switch (success) {
                        case 200:
                        	etat = "payé";
                            observablePaiement.add(new Paiement(0, total, comboBoxType.getValue(), LocalDateTime.now(), null,etat));
                           alert = new Alert(AlertType.INFORMATION);
                        	alert.setTitle("Erreur !");
                        	
                        	alert.setContentText("Transmition avec succès ");

                        	alert.showAndWait();
                        	
                        	TexfFieldTotalPaye.setText(String.valueOf(total));
                          	TexfFieldReste.setText(String.valueOf(0));
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
            	
            	
            	Vente v = (new Vente(c, total,LocalDateTime.now(), getLignes(),getPayments()));
            	vDAO.create(v);
            	observableLigne.clear();
            	observablePaiement.clear();
            	
                alert = new Alert(AlertType.INFORMATION);
               	alert.setTitle("Succès !");
               	
               	alert.setContentText("Ajout avec succès :)  ");

               	alert.showAndWait();
               
               	
             	TextFieldTitulaire.clear();
               	TextFieldMontant.clear(); 	
               	TextFieldTypeCarte.clear();
               	 TextFieldNumeroCarte.clear();
               	  TextFieldAnneeExpiration.clear();
               	   TextFieldMoisExpiration.clear();
               	   TextFieldCodeVerification.clear();
        	    
            	
            	
            	}
            
        });
        
         

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
        //Display Stage window
        
        buttonCategorie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	FormCategorie FC = new FormCategorie();		
				Stage window1 = new Stage();
				window1.setScene(FC.scene);		
				observableLigne.clear();
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
				observableLigne.clear();
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
				observableLigne.clear();
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
				observableLigne.clear();
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
				observableLigne.clear();
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
