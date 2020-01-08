package Produit;

import DataAccess.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import Categorie.Categorie;

public class ProduitDAOIMPL implements ProduitDAO<Produit>{
    private PreparedStatement statement;
    private ResultSet resultSet;
    private Connection conn;
    public ProduitDAOIMPL(){
        conn = DataConnection.getDataConnection().getConnection();
    }
    @Override
    public Produit getOne(long id) {
        String sql = "SELECT id_produit , designation , qte , prix_achat , prix_vente , image , p.id_cat from produit p , categorie c WHERE id_produit= ? and c.id_cat = p.id_cat" ;
        
    
        try {
        	statement = conn.prepareStatement(sql);
			statement.setLong(1,id);
		 resultSet = statement.executeQuery();
		 Categorie categorie = new Categorie(resultSet.getLong("id_cat"), resultSet.getString("libelle"));
            if(resultSet.next()){
                return new Produit(resultSet.getLong("id_produit"),resultSet.getString("designaton"),resultSet.getInt("qte"),resultSet.getDouble("prix_achat"),resultSet.getDouble("prix_vente"),categorie);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
             return null;
    }
    @Override
    public boolean delete(long id){
        String sql = "DELETE FROM PRODUIT where id_produit = ?";
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
    public boolean create(Produit obj) {
        String sql = "INSERT INTO PRODUIT(designation,qte,prix_achat,prix_vente,id_cat) values(?,?,?,?,?)";
        try {
        	statement=conn.prepareStatement(sql);
			statement.setString(1,obj.getDesignation());
			statement.setInt(2,obj.getQte());
			statement.setDouble(3,obj.getPrix_achat());
			statement.setDouble(4,obj.getPrix_vente());
		//	statement.setString(5,obj.getImage());
			
			
			statement.setLong(5,obj.getCategorie().getId_cat());
			
			statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Produit obj) {
          String sql = "UPDATE PRODUIT set designation= ? , qte = ? , prix_achat = ? , prix_vente = ? ,  id_cat = ? where id_produit = ? ";
        try{
        	statement=conn.prepareStatement(sql);
			statement.setString(1,obj.getDesignation());
			statement.setInt(2,obj.getQte());
			statement.setDouble(3,obj.getPrix_achat());
			statement.setDouble(4,obj.getPrix_vente());
		//	statement.setString(5,obj.getImage());
			
			statement.setLong(5,obj.getCategorie().getId_cat());
			
			statement.setLong(6,obj.getId_produit());
			statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Produit> getAll() {
      List<Produit> list = new ArrayList<Produit>();

      String sql = "SELECT id_produit , designation , qte , prix_achat , prix_vente , p.id_cat , c.libelle from produit p , categorie c WHERE c.id_cat = p.id_cat" ;
      
      
      try {
      	statement = conn.prepareStatement(sql);

		 resultSet = statement.executeQuery();
		 
		 while(resultSet.next()){
        	  Categorie categorie = new Categorie(resultSet.getLong("id_cat"), resultSet.getString("libelle"));
              list.add(new Produit(resultSet.getLong("id_produit"),resultSet.getString("designation"),resultSet.getInt("qte"),resultSet.getDouble("prix_achat"),resultSet.getDouble("prix_vente"),categorie));
          }
      }
      catch(SQLException e){
    	  
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        
        return list;
        
    }

    @Override
    public List<Produit> getAll(String key) {
             List<Produit> list = new ArrayList<Produit>();

        String sql = "SELECT * from PRODUIT p , categorie c where p.id_cat = c.id_cat and p.designation LIKE ?";
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setString(1,key);
    
        	
		resultSet=statement.executeQuery();
		 Categorie categorie = new Categorie(resultSet.getLong("id_cat"), resultSet.getString("libelle"));

            while(resultSet.next()){
                list.add(new Produit(resultSet.getLong("id_produit"),resultSet.getString("designaton"),resultSet.getInt("qte"),resultSet.getDouble("prix_achat"),resultSet.getDouble("prix_vente"),categorie));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }
    
    

}
