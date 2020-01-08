package Vente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import Client.Client;
import Ligne.Ligne;
import Paiement.Paiement;

public class Vente {
	
	 private long id_vente;
	    private Client client;
	    private double total;
	    private LocalDateTime date;
	    private Collection<Ligne> lignes;
	    private Collection<Paiement> paiements;
		public Vente(long id_vente, Client client, double total, LocalDateTime date, Collection<Ligne> lignes,Collection<Paiement> paiements) {
		
			this.id_vente = id_vente;
			this.client = client;
			this.total = total;
			this.date = date;
			this.lignes = lignes;
			this.paiements = paiements;
		}
	public Vente(Client client, double total, LocalDateTime date, Collection<Ligne> lignes,Collection<Paiement> paiements) {
		
		this.client = client;
		this.total = total;
		this.date = date;
		this.lignes = lignes;
		this.paiements = paiements;
	}
	
public Vente(double total, LocalDateTime date,Collection<Paiement> paiements) {
		
		
		this.total = total;
		this.date = date;
	
		this.paiements = paiements;
	}
	
	
	public Vente(long id_vente, Client client, double total, LocalDateTime date) {
		super();
		this.id_vente = id_vente;
		this.client = client;
		this.total = total;
		this.date = date;
	}
	public Vente() {
		this.id_vente=1;
	}
	public Vente(long id_vente) {
		
		this.id_vente = id_vente;
	}
	public long getId_vente() {
		return id_vente;
	}
	public void setId_vente(long id_vente) {
		this.id_vente = id_vente;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Collection<Ligne> getLignes() {
		return lignes;
	}
	public void setLignes(ArrayList<Ligne> lignes) {
		this.lignes = lignes;
	}
	public Collection<Paiement> getPaiements() {
		return paiements;
	}
	public void setPaiements(Collection<Paiement> paiements) {
		this.paiements = paiements;
	}
	
	
	
	
	
	    
		
	    
	    

}
