package Cheque;


public class Cheque {
	
	    private long id_cheque;
	    private String titulaire;
	    
		public Cheque(long id_cheque, String titulaire) {
		
			this.id_cheque = id_cheque;
			this.titulaire = titulaire;
		}

		public Cheque(String titulaire) {
	
			this.titulaire = titulaire;
		}
		

		public Cheque(long id_cheque) {
		
			this.id_cheque = id_cheque;
		}

		public long getId_cheque() {
			return id_cheque;
		}

		public void setId_cheque(long id_cheque) {
			this.id_cheque = id_cheque;
		}

		public String getTitulaire() {
			return titulaire;
		}

		public void setTitulaire(String titulaire) {
			this.titulaire = titulaire;
		}
		@Override
		public String toString() {
		
		return id_cheque + "";
		}

		


		
		
	    
	   
	    
}
