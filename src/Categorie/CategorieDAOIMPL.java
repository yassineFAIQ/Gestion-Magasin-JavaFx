package Categorie;

import DataAccess.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategorieDAOIMPL implements CategorieDAO<Categorie>{
    private PreparedStatement statement;
    private ResultSet resultSet;
    private Connection conn;
    public CategorieDAOIMPL(){
        conn = DataConnection.getDataConnection().getConnection();
    }
    @Override
    public Categorie getOne(long id) {
        String sql = "SELECT * from categorie WHERE id_cat= ? ";
        try {
        	statement = conn.prepareStatement(sql);
			statement.setLong(1,id);
		resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new Categorie(resultSet.getLong("id_cat"),resultSet.getString("libelle"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
             return null;
    }
    @Override
    public boolean delete(long id){
        String sql = "DELETE FROM categorie where id_cat= ? ";
        try{
        	statement=conn.prepareStatement(sql);
			statement.setLong(1,id);
			statement.executeUpdate();
			return true ;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean create(Categorie obj) {
        String sql = "INSERT INTO categorie(libelle) values(?)";
        try {
        	statement=conn.prepareStatement(sql);
			statement.setString(1,obj.getLibelle());
			statement.executeUpdate();
			return true ;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Categorie obj) {
          String sql = "UPDATE categorie set libelle= ? where id_cat=?";
        try{
        	statement=conn.prepareStatement(sql);
			statement.setString(1,obj.getLibelle());
			statement.setLong(2,obj.getId_cat());
			statement.executeUpdate();
			return true ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Categorie> getAll() {
      List<Categorie> list = new ArrayList<Categorie>();

        String sql = "SELECT * from categorie";
        try{
        	statement=conn.prepareStatement(sql);
			resultSet=statement.executeQuery();
            while(resultSet.next()){ 
             list.add(new Categorie(resultSet.getLong("id_cat"),resultSet.getString("libelle")));
        
            }
           
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
		return list;
        
    }

    @Override
    public List<Categorie> getAll(String key) {
             List<Categorie> list = new ArrayList<Categorie>();

        String sql = "SELECT * from categorie where libelle LIKE ? OR id_cat like ? ";
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setString(1,key);
        	statement.setString(2,key);
        	
			resultSet=statement.executeQuery();
            while(resultSet.next()){
            	list.add(new Categorie(resultSet.getLong("id_cat"),resultSet.getString("libelle")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }
    
    @Override
    public List<Categorie> findAll() {
      List<Categorie> list = new ArrayList<Categorie>();

        String sql = "SELECT libelle from categorie";
        try{
        	statement=conn.prepareStatement(sql);
			resultSet=statement.executeQuery();
            while(resultSet.next()){ 
             list.add(new Categorie(resultSet.getString("libelle")));
        
            }
           
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
		return list;
        
    }

	
}
