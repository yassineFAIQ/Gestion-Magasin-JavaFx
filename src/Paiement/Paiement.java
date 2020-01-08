package Paiement;


import java.time.LocalDateTime;



import Cheque.Cheque;

import Vente.Vente;

public class Paiement {
	
	    private long id_paiement;
	    private double montant_paiment;
	    private String type_paiement;
	  //  private PaymentMethod method;
	    private Cheque cheque;
	    private LocalDateTime date_paiement;
	    private Vente vente;
	    private String etat;
	    

	    public Paiement(long id_paiement, double montant_paiment, String type_paiement, Cheque cheque,LocalDateTime date_paiement, Vente vente,String etat) {
			this.id_paiement = id_paiement;
			this.montant_paiment = montant_paiment;
			this.type_paiement = type_paiement;
			this.cheque = cheque;
			this.date_paiement = date_paiement;
			this.vente = vente;
			this.etat=etat ;
		}
	    public Paiement(long id_paiement, double montant_paiment, String type_paiement,LocalDateTime date_paiement, Vente vente,String etat) {
			this.id_paiement = id_paiement;
			this.montant_paiment = montant_paiment;
			this.type_paiement = type_paiement;
			this.cheque = null;
			this.date_paiement = date_paiement;
			this.vente = vente;
			this.etat=etat ;
		}


	    
	    public Paiement(double montant_paiment, String type_paiement, LocalDateTime date_paiement, Vente vente,String etat) {
	      
	        this.montant_paiment = montant_paiment;
	        this.type_paiement = type_paiement;
	        this.date_paiement = date_paiement;
	        this.vente = vente;
	        this.etat=etat ;
	      //  this.cheque = null;
	    }
	    

		public Paiement(double montant_paiment, String type_paiement, Cheque cheque, LocalDateTime date_paiement,Vente vente,String etat) {
			this.montant_paiment = montant_paiment;
			this.type_paiement = type_paiement;
			this.cheque = cheque;
			this.date_paiement = date_paiement;
			this.vente = vente;
			this.etat=etat ;
		}
		

		public Paiement(Paiement paiement) {
			this.id_paiement = paiement.id_paiement;
			this.montant_paiment = paiement.montant_paiment;
			this.type_paiement = paiement.type_paiement;
			this.cheque = paiement.cheque;
			this.date_paiement = paiement.date_paiement;
			this.vente = paiement.vente;
			this.etat=paiement.etat;
	    }
		
		
		public Paiement(double montant_paiment, String type_paiement, LocalDateTime date_paiement,String etat) {
		      
	        this.montant_paiment = montant_paiment;
	        this.type_paiement = type_paiement;
	        this.date_paiement = date_paiement;
	        this.etat=etat ;
	    //    this.vente = vente;
	     //   this.cheque = null;
	    }


		public long getId_paiement() {
			return id_paiement;
		}


		public void setId_paiement(long id_paiement) {
			this.id_paiement = id_paiement;
		}


		public double getMontant_paiment() {
			return montant_paiment;
		}


		public void setMontant_paiment(double montant_paiment) {
			this.montant_paiment = montant_paiment;
		}


		public String getType_paiement() {
			return type_paiement;
		}


		public void setType_paiement(String type_paiement) {
			this.type_paiement = type_paiement;
		}
		
		public String getEtat() {
			return etat;
		}


		public void setEtat(String etat) {
			this.etat = etat;
		}


		public Cheque getCheque() {
			return cheque;
		}


		public void setCheque(Cheque cheque) {
			this.cheque = cheque;
		}


		public LocalDateTime getDate_paiement() {
			return date_paiement;
		}


		public void setDate_paiement(LocalDateTime date_paiement) {
			this.date_paiement = date_paiement;
		}


		public Vente getVente() {
			return vente;
		}


		public void setVente(Vente vente) {
			this.vente = vente;
		}
		
		
	    
	    
	    
	    
	    
	    
	   
	    
}
