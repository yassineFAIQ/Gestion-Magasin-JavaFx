package Produit;

import Categorie.Categorie;

public class Produit {
    private long id_produit;
    private String designation;
    private int qte ;
    private double prix_achat;
    private double prix_vente;
    //private String image;
    private Categorie categorie ;
    
    
	public Produit(long id_produit, String designation, int qte, double prix_achat, double prix_vente, 
			Categorie categorie) {
		
		this.id_produit = id_produit;
		this.designation = designation;
		this.qte = qte;
		this.prix_achat = prix_achat;
		this.prix_vente = prix_vente;
		
		this.categorie = categorie;
	}


	public Produit(String designation, int qte, double prix_achat, double prix_vente, 
			Categorie categorie) {
		super();
		this.designation = designation;
		this.qte = qte;
		this.prix_achat = prix_achat;
		this.prix_vente = prix_vente;
		
		this.categorie = categorie;
	}
	public Produit(String designation, int qte, double prix_achat, double prix_vente) {
		
	
		this.designation = designation;
		this.qte = qte;
		this.prix_achat = prix_achat;
		this.prix_vente = prix_vente;
	
	}
	
	public Produit(Long id_produit , String designation, int qte, double prix_achat, double prix_vente) {
		
		this.id_produit=id_produit;
		this.designation = designation;
		this.qte = qte;
		this.prix_achat = prix_achat;
		this.prix_vente = prix_vente;
	
	}
	
	


	public Produit(long id_produit, String designation) {
		
		this.id_produit = id_produit;
		this.designation = designation;
	}


	public long getId_produit() {
		return id_produit;
	}


	public void setId_produit(long id_produit) {
		this.id_produit = id_produit;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public int getQte() {
		return qte;
	}


	public void setQte(int qte) {
		this.qte = qte;
	}


	public double getPrix_achat() {
		return prix_achat;
	}


	public void setPrix_achat(double prix_achat) {
		this.prix_achat = prix_achat;
	}


	public double getPrix_vente() {
		return prix_vente;
	}


	public void setPrix_vente(double prix_vente) {
		this.prix_vente = prix_vente;
	}




	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	@Override
	public String toString() {
		return designation ;
	}
	
	
    
    
    
    

   
}

