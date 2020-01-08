package Categorie;

public class Categorie {
	private long id_cat;
    private String libelle;
   

    public Categorie(long id_cat, String libelle) {
        this.id_cat = id_cat;
        this.libelle = libelle;
        
    }
    public Categorie(String libelle) {
        this.libelle = libelle;
        
    }

    public long getId_cat() {
        return id_cat;
    }

    public void setId_cat(long id_cat) {
        this.id_cat = id_cat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
	@Override
	public String toString() {
		return libelle;
	}

    

}
