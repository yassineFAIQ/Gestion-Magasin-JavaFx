package Ligne;


import Produit.Produit;
import Vente.Vente;

public class Ligne {
	
	    private long id_ligne;
	    private Produit produit;
	    private Vente vente;
	    private int qte_ligne;
	    private double sousTotal;
	    
	    
		public Ligne(long id_ligne, Produit produit, Vente vente, int qte_ligne, double sousTotal) {
			
			this.id_ligne = id_ligne;
			this.produit = produit;
			this.vente = vente;
			this.qte_ligne = qte_ligne;
			this.sousTotal = sousTotal;
		}


		public Ligne(Produit produit, Vente vente, int qte_ligne, double sousTotal) {
			
			this.produit = produit;
			this.vente = vente;
			this.qte_ligne = qte_ligne;
			this.sousTotal = sousTotal;
		}
		
		
		public Ligne(long id_ligne, Produit produit, int qte_ligne, double sousTotal) {
			
			this.id_ligne = id_ligne;
			this.produit = produit;
			this.qte_ligne = qte_ligne;
			this.sousTotal = sousTotal;
		}


		public Ligne(Ligne ligne) {
	        this.id_ligne = ligne.id_ligne;
	        this.produit = ligne.produit;
	        this.sousTotal = ligne.sousTotal;
	        this.qte_ligne = ligne.qte_ligne;
	        this.vente = ligne.vente;
	    }
		
		public long getId_ligne() {
			return id_ligne;
		}


		public void setId_ligne(long id_ligne) {
			this.id_ligne = id_ligne;
		}


		public Produit getProduit() {
			return produit;
		}


		public void setProduit(Produit produit) {
			this.produit = produit;
		}


		public Vente getVente() {
			return vente;
		}


		public void setVente(Vente vente) {
			this.vente = vente;
		}


		public int getQte_ligne() {
			return qte_ligne;
		}


		public void setQte_ligne(int qte_ligne) {
			this.qte_ligne = qte_ligne;
		}


		public double getSousTotal() {
			return sousTotal;
		}


		public void setSousTotal(double sousTotal) {
			this.sousTotal = sousTotal;
		}
		
		
		
		
	    
	    
	    
	    
	    
	    
	    
	   
	    
}
