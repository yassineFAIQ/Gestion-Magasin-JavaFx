package Client;

import DataAccess.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Categorie.Categorie;

public class ClientDAOIMPL implements ClientDAO<Client>{
    private PreparedStatement statement;
    private ResultSet resultSet;
    private Connection conn;
    public ClientDAOIMPL(){
        conn = DataConnection.getDataConnection().getConnection();
    }
    @Override
    public Client getOne(long id) {
        String sql = "SELECT * from client WHERE id_client = ? ";
        try {
        	statement = conn.prepareStatement(sql);
			statement.setLong(1,id);
			 resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new Client(resultSet.getLong("id_client"),resultSet.getString("prenom"),resultSet.getString("nom"),resultSet.getString("email"),resultSet.getString("adresse"),resultSet.getInt("tel"));

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
             return null;
    }
    @Override
    public boolean delete(long id){
        String sql = "DELETE FROM client where id_client = ? ";
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
    public boolean create(Client obj) {
        String sql = "INSERT INTO client(prenom,nom,email,adresse,tel) values(?,?,?,?,?)";
        try {
        	statement=conn.prepareStatement(sql);
			statement.setString(1,obj.getPrenom());
			statement.setString(2,obj.getNom());
			statement.setString(3,obj.getEmail());
			statement.setString(4,obj.getAdresse());
			statement.setInt(5,obj.getTel());
			statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Client obj) {
          String sql = "UPDATE client set prenom = ? , nom = ? , email = ? , adresse = ? , tel = ? where id_client = ? ";
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setString(1,obj.getPrenom());
			statement.setString(2,obj.getNom());
			statement.setString(3,obj.getEmail());
			statement.setString(4,obj.getAdresse());
			statement.setInt(5,obj.getTel());
			statement.setDouble(6,obj.getId_client());
			statement.executeUpdate();
			
			return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Client> getAll() {
    	
      List<Client> list = new ArrayList<Client>();

        String sql = "SELECT * from client";
        try{
        	statement=conn.prepareStatement(sql);
			
			resultSet = statement.executeQuery();
			
            while(resultSet.next()){ 
             list.add(new Client(resultSet.getLong("id_client"),resultSet.getString("prenom"),resultSet.getString("nom"),resultSet.getString("email"),resultSet.getString("adresse"),resultSet.getInt("tel")));
             }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }

    @Override
    public List<Client> getAll(String key) {
             List<Client> list = new ArrayList<Client>();

             String sql = "SELECT * from client where nom LIKE ? OR prenom LIKE ? OR email LIKE ? OR adresse LIKE ? OR tel LIKE ? id_client like ? ";
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setString(1,key);
        	statement.setString(2,key);
        	statement.setString(3,key);
        	statement.setString(4,key);
        	statement.setString(5,key);
        	statement.setString(6,key);
        	
		resultSet=statement.executeQuery();
            while(resultSet.next()){
                list.add(new Client(resultSet.getLong("id_client"),resultSet.getString("prenom"),resultSet.getString("nom"),resultSet.getString("email"),resultSet.getString("adresse"),resultSet.getInt("tel")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }


}
