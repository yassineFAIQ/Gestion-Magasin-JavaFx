package Ligne;

import DataAccess.*;
import Produit.Produit;
import Vente.Vente;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import Categorie.Categorie;

public class LigneDAOIMPL implements LigneDAO<Ligne>{
    private PreparedStatement statement;
    private ResultSet resultSet;
    private Connection conn;
    public LigneDAOIMPL(){
        conn = DataConnection.getDataConnection().getConnection();
    }
    @Override
    public Ligne getOne(long id) {
        String sql = "SELECT id_ligne , l.id_produit , l.id_vente , qte_ligne , sousTotal , p.designation from produit p , lignedecommande l , " 
        		+ " vente v  WHERE id_ligne= ? and l.id_produit = p.id_produit and l.id_vente = v.id_vente" ;
        
    
        try {
        	statement = conn.prepareStatement(sql);
			statement.setLong(1,id);
		 resultSet = statement.executeQuery();
		 Produit produit = new Produit(resultSet.getLong("id_produit"), resultSet.getString("designation"));
		 Vente vente = new Vente(resultSet.getLong("id_vente"));
		 
            if(resultSet.next()){
                return new Ligne(resultSet.getLong("id_ligne"),produit,vente,resultSet.getInt("qte_ligne"),resultSet.getDouble("sousTotal"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
             return null;
    }
    @Override
    public boolean delete(long id){
        String sql = "DELETE FROM Ligne where id_ligne = ?";
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
    public boolean create(Ligne obj) {
        String sql = "INSERT INTO lignedecommande(id_produit,id_vente,qte_ligne,sousTotal) values(?,?,?,?)";
        try {
        	statement=conn.prepareStatement(sql);
			statement.setLong(1,obj.getProduit().getId_produit());
			statement.setLong(2,obj.getVente().getId_vente());
			statement.setDouble(3,obj.getQte_ligne());
			statement.setDouble(4,obj.getSousTotal());
		
			
			
			
			
			statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Ligne obj) {
          String sql = "UPDATE Ligne set id_produit= ? , id_vente = ? , qte_ligne = ? , sousTotal = ? , where id_ligne = ? ";
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setLong(1,obj.getProduit().getId_produit());
			statement.setLong(2,obj.getVente().getId_vente());
			statement.setDouble(3,obj.getQte_ligne());
			statement.setDouble(4,obj.getSousTotal());
			statement.setDouble(4,obj.getId_ligne());
			statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Ligne> getAll() {
      List<Ligne> list = new ArrayList<Ligne>();

      String sql = "SELECT id_ligne , l.id_produit , l.id_vente , qte_ligne , sousTotal , p.designation from produit p , lignedecommande l , " 
      		+ " vente v  WHERE l.id_produit = p.id_produit and l.id_vente = v.id_vente" ;      
      
      try {
      	statement = conn.prepareStatement(sql);

		 resultSet = statement.executeQuery();
		
		 while(resultSet.next()){
			 Produit produit = new Produit(resultSet.getLong("id_produit"), resultSet.getString("designation"));
			 Vente vente = new Vente(resultSet.getLong("id_vente"));
              list.add(new Ligne(resultSet.getLong("id_ligne"),produit,vente,resultSet.getInt("qte_ligne"),resultSet.getDouble("sousTotal")));
          }
      }
      catch(SQLException e){
            e.printStackTrace();
            
        }
        if(list.size() == 0) return null;
        return list;
    }

    @Override
    public List<Ligne> getAll(String key) {
             List<Ligne> list = new ArrayList<Ligne>();

        String sql = "SELECT * from ligne l , produit p , vente v where l.id_produit = p.id_ligne and l.id_vente = v.id_vente and p.designation LIKE ?";
      
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setString(1,key);
    
        	
		resultSet=statement.executeQuery();
		
	
            while(resultSet.next()){
            	 Produit produit = new Produit(resultSet.getLong("id_produit"), resultSet.getString("designation"));
        		 Vente vente = new Vente(resultSet.getLong("id_vente"));
                list.add(new Ligne(resultSet.getLong("id_ligne"),produit,vente,resultSet.getInt("qte_ligne"),resultSet.getDouble("sousTotal")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }
    @Override
    public List<Ligne> getAll(long id) {
             List<Ligne> list = new ArrayList<Ligne>();

        String sql = "SELECT id_ligne , l.id_produit , l.id_vente , qte_ligne , sousTotal , p.designation from produit p , lignedecommande l , " +  
        		"vente v  WHERE l.id_vente= ? and l.id_produit = p.id_produit and l.id_vente = v.id_vente";
      
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setLong(1,id);
    
        	
		resultSet=statement.executeQuery();
	
	
            while(resultSet.next()){
           	 Produit produit = new Produit(resultSet.getLong("id_produit"), resultSet.getString("designation"));
    		 Vente vente = new Vente(resultSet.getLong("id_vente"));
                list.add(new Ligne(resultSet.getLong("id_ligne"),produit,vente,resultSet.getInt("qte_ligne"),resultSet.getDouble("sousTotal")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }

    
    

}
