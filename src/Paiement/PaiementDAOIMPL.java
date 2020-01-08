package Paiement;

import DataAccess.*;
import Date.DateGenerator;
import Produit.Produit;
import Vente.Vente;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



import Categorie.Categorie;
import Cheque.Cheque;

public class PaiementDAOIMPL implements PaiementDAO<Paiement>{
    private PreparedStatement statement;
    private ResultSet resultSet;
    private Connection conn;
    public PaiementDAOIMPL(){
        conn = DataConnection.getDataConnection().getConnection();
    }
    @Override
    public Paiement getOne(long id) {
        String sql = "SELECT * from paiement where id_paiement = ?" ;
        
    
        try {
        	statement = conn.prepareStatement(sql);
			statement.setLong(1,id);
		 resultSet = statement.executeQuery();

		 Vente vente = new Vente(resultSet.getLong("id_vente"));
		 LocalDateTime date = DateGenerator.toLocalDateTime(resultSet.getTimestamp("date_paiement"));
		 Cheque cheque = new Cheque(resultSet.getLong("id_cheque"));


            if(resultSet.next()){
                return new Paiement(resultSet.getLong("id_paiement"),resultSet.getDouble("montant_paiement"),resultSet.getString("type_paiement"),cheque,date
                		,vente,resultSet.getString("etat"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
             return null;
    }
    @Override
    public boolean delete(long id){
        String sql = "DELETE FROM Paiement where id_paiement = ?";
        try{
        	statement=conn.prepareStatement(sql);
			statement.setLong(1,id);
			statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean create(Paiement obj) {
    	
   
        String sql = "INSERT INTO Paiement(montant_paiement,type_paiement,id_cheque,date_paiement,id_vente,etat) values(?,?,?,now(),?,?)";
        try {
        	statement=conn.prepareStatement(sql);
        	if(obj.getCheque() != null)
			statement.setLong(3,obj.getCheque().getId_cheque());
        	 else
       	   statement.setNull(3, Types.DOUBLE);
			statement.setLong(4,obj.getVente().getId_vente());
			statement.setDouble(1,obj.getMontant_paiment());
			statement.setString(2,obj.getType_paiement());
			statement.setString(5, obj.getEtat());

			
			statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    
    @Override
    public boolean update(Paiement obj) {
          String sql = "UPDATE Paiement set montant_paiement=?,type_paiement=?,id_cheque=?,id_vente = ? ,etat = ? where id_paiement = ? ";
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setLong(3,obj.getCheque().getId_cheque());
			statement.setLong(4,obj.getVente().getId_vente());
			statement.setDouble(1,obj.getMontant_paiment());
			statement.setString(2,obj.getType_paiement());
			statement.setLong(6,obj.getId_paiement());
			statement.setString(5,obj.getEtat());

			
			statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Paiement> getAll() {
      List<Paiement> list = new ArrayList<Paiement>();

      String sql = "SELECT * from paiement ";      
      
      try {
      	statement = conn.prepareStatement(sql);

		 resultSet = statement.executeQuery();
		
		 while(resultSet.next()){
			 Vente vente = new Vente(resultSet.getLong("id_vente"));
			 LocalDateTime date = DateGenerator.toLocalDateTime(resultSet.getTimestamp("date_paiement"));
			 Cheque cheque = new Cheque(resultSet.getLong("id_cheque"));
              list.add(new Paiement(resultSet.getLong("id_paiement"),resultSet.getDouble("montant_paiement"),
            		  resultSet.getString("type_paiement"),cheque,date,vente,resultSet.getString("etat")));
         
          }
      }
      catch(SQLException e){
            e.printStackTrace();
            
        }
        if(list.size() == 0) return null;
        return list;
    }

    @Override
    public List<Paiement> getAll(String key) {
             List<Paiement> list = new ArrayList<Paiement>();

        String sql = "SELECT p.id_paiement , p.montant_paiement , p.type_paiement , p.id_cheque , p.date_paiement , p.id_vente , c.prenom ,"
        		+ "c.nom from paiement p , vente v , client c where  c.prenom LIKE ? or c.nom LIKE ?";
      
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setString(1,key);
        	statement.setString(2,key);
    
        	
		resultSet=statement.executeQuery();
		 while(resultSet.next()){
		 Vente vente = new Vente(resultSet.getLong("id_vente"));
		 LocalDateTime date = DateGenerator.toLocalDateTime(resultSet.getTimestamp("date_paiement"));
		 Cheque cheque = new Cheque(resultSet.getLong("id_cheque"), resultSet.getString("titulaire_cheque"));
          list.add(new Paiement(resultSet.getLong("id_paiement"),resultSet.getDouble("montant_paiement"),
        		  resultSet.getString("type_paiement"),cheque,date,vente,resultSet.getString("etat")));
		 }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }
    
    @Override
    public List<Paiement> getAll(long id) {
             List<Paiement> list = new ArrayList<Paiement>();

        String sql = "SELECT * from paiement where id_vente = ?";
      
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setLong(1,id);
        	
    
        	
		resultSet=statement.executeQuery();
		
		 while(resultSet.next()){
			 
		 Vente vente = new Vente(resultSet.getLong("id_vente"));
		 LocalDateTime date = DateGenerator.toLocalDateTime(resultSet.getTimestamp("date_paiement"));
		 Cheque cheque = new Cheque(resultSet.getLong("id_cheque"));
          list.add(new Paiement(resultSet.getLong("id_paiement"),resultSet.getDouble("montant_paiement"),
        	 resultSet.getString("type_paiement"),cheque,date,vente,resultSet.getString("etat")));
         
     
          
		 }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }

    
    

}
