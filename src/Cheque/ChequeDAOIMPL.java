package Cheque;

import DataAccess.*;
import Produit.Produit;
import Vente.Vente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import Categorie.Categorie;

public class ChequeDAOIMPL implements ChequeDAO<Cheque>{
    private PreparedStatement statement;
    private ResultSet resultSet;
    private Connection conn;
    public ChequeDAOIMPL(){
        conn = DataConnection.getDataConnection().getConnection();
    }
    @Override
    public Cheque getOne(long id) {
        String sql = "SELECT id_Cheque , titulaire_cheque from cheque WHERE id_Cheque= ? " ;
        try {
        	statement = conn.prepareStatement(sql);
			statement.setLong(1,id);
		 resultSet = statement.executeQuery();
		 
            if(resultSet.next()){
                return new Cheque(resultSet.getLong("id_Cheque"),resultSet.getString("titulaire_cheque"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
             return null;
    }
    @Override
    public Cheque getLast() {
        String sql = "SELECT id_Cheque , titulaire_cheque from cheque ORDER BY id_cheque DESC LIMIT 1 " ;
        try {
        	statement = conn.prepareStatement(sql);
		
		 resultSet = statement.executeQuery();
		 
            if(resultSet.next()){
                return new Cheque(resultSet.getLong("id_Cheque"),resultSet.getString("titulaire_cheque"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
             return null;
    }
    
    @Override
    public boolean delete(long id){
        String sql = "DELETE FROM Cheque where id_Cheque = ?";
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
    public int create(Cheque obj) {
        String sql = "INSERT INTO Cheque(id_cheque,titulaire_cheque) values(NULL,?)";
        try {
     
        	statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,obj.getTitulaire());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			  if(generatedKeys.next()) return generatedKeys.getInt(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    


    @Override
    public boolean update(Cheque obj) {
          String sql = "UPDATE Cheque set titulaire_cheque = ? where id_Cheque = ? ";
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setString(1,obj.getTitulaire());
			statement.setLong(2,obj.getId_cheque());

			statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Cheque> getAll() {
      List<Cheque> list = new ArrayList<Cheque>();

      String sql = "SELECT id_Cheque , titulaire_cheque from  Cheque " ;      
      
      try {
      	statement = conn.prepareStatement(sql);

		 resultSet = statement.executeQuery();
		
		 while(resultSet.next()){
			
              list.add(new Cheque(resultSet.getLong("id_Cheque"),resultSet.getString("titulaire_cheque")));
          }
      }
      catch(SQLException e){
            e.printStackTrace();
            
        }
        if(list.size() == 0) return null;
        return list;
    }

    @Override
    public List<Cheque> getAll(String key) {
             List<Cheque> list = new ArrayList<Cheque>();

        String sql = "SELECT id_Cheque , titulaire_cheque from Cheque where titulaire_cheque LIKE ?";
      
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setString(1,key);
    
        	
		resultSet=statement.executeQuery();
            while(resultSet.next()){
            	list.add(new Cheque(resultSet.getLong("id_Cheque"),resultSet.getString("titulaire_cheque")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }

    
    

}
