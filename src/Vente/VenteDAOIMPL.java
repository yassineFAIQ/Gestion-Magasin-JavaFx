package Vente;

import DataAccess.*;
import Date.DateGenerator;

import Vente.Vente;
import Ligne.Ligne;

import Ligne.LigneDAOIMPL;
import Paiement.Paiement;
import Paiement.PaiementDAOIMPL;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;




import Cheque.ChequeDAOIMPL;
import Client.Client;

public class VenteDAOIMPL implements VenteDAO<Vente>{
    private PreparedStatement statement;
    private ResultSet resultSet;
    private Connection conn;
    public VenteDAOIMPL(){
        conn = DataConnection.getDataConnection().getConnection();
    }
   
    @Override
    public Vente getOne(long id) {
   
      String sql = "SELECT id_vente , v.id_client , c.prenom , c.nom , total , date from vente v , client c where v.id_client = c.id_client and id_vente = ?" ;      
      
      try {
      	statement = conn.prepareStatement(sql);
      	statement.setLong(1, id);

		 resultSet = statement.executeQuery();
		

		 while(resultSet.next()){
			 LocalDateTime date = DateGenerator.toLocalDateTime(resultSet.getTimestamp("date"));
			 Client client = new Client(resultSet.getLong("id_client"), resultSet.getString("prenom"),resultSet.getString("nom"));
			
              return new Vente(resultSet.getLong("id_vente"),client,resultSet.getDouble("total"),date);
          }
      }
      catch(SQLException e){
            e.printStackTrace();
            
        }
      return null ;
    }
     /*
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
    */
    @Override
    public boolean create(Vente obj) {
    	LigneDAOIMPL LigneIMPL = new LigneDAOIMPL();
       PaiementDAOIMPL paiementDao = new PaiementDAOIMPL();
        ChequeDAOIMPL chequeDao = new ChequeDAOIMPL();
       

        String sql = "INSERT INTO `vente` VALUES (NULL,?,?,now())" ;
        try {
        	
        	 statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        	statement.setLong(1, obj.getClient().getId_client());
         	statement.setDouble(2, obj.getTotal());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) obj.setId_vente(generatedKeys.getLong(1));


            for (Ligne ligne: obj.getLignes()) {
                ligne.setVente(obj);
                LigneIMPL.create(ligne);
            }

            
            for (Paiement paiement : obj.getPaiements()) {
                paiement.setVente(obj);
                if(paiement.getCheque() != null) {
                    paiement.getCheque().setId_cheque(chequeDao.create(paiement.getCheque()));
                }
                paiementDao.create(paiement);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    return false;
    }

    /*
    @Override
    public boolean update(Vente obj) {
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

*/
    @Override
    public List<Vente> getAll() {
      List<Vente> list = new ArrayList<Vente>();

      String sql = "SELECT id_vente , v.id_client , c.prenom , c.nom , total , date from vente v , client c where v.id_client = c.id_client" ;      
      
      try {
      	statement = conn.prepareStatement(sql);

		 resultSet = statement.executeQuery();
		 

		 while(resultSet.next()){
			 LocalDateTime date = DateGenerator.toLocalDateTime(resultSet.getTimestamp("date"));
			 Client client = new Client(resultSet.getLong("id_client"), resultSet.getString("prenom"),resultSet.getString("nom"));
              list.add(new Vente(resultSet.getLong("id_vente"),client,resultSet.getDouble("total"),date));
          }
      }
      catch(SQLException e){
            e.printStackTrace();
            
        }
        if(list.size() == 0) return null;
        return list;
    }

    /*
    @Override
    public List<Ligne> getAll(String key) {
             List<Ligne> list = new ArrayList<Ligne>();

        String sql = "SELECT * from ligne l , produit p , vente v where l.id_produit = p.id_ligne and l.id_vente = v.id_vente and p.designation LIKE ?";
      
        try{
        	statement=conn.prepareStatement(sql);
        	statement.setString(1,key);
    
        	
		resultSet=statement.executeQuery();
		 Produit produit = new Produit(resultSet.getLong("id_produit"), resultSet.getString("designation"));
		 Vente vente = new Vente(resultSet.getLong("id_vente"));
	
            while(resultSet.next()){
                list.add(new Ligne(resultSet.getLong("id_ligne"),produit,vente,resultSet.getInt("qte_ligne"),resultSet.getDouble("sousTotal")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }


    
    */

}
/*
 *  public void create(Order order) {
        OrderLineDaoImpl orderLineDao = new OrderLineDaoImpl();
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        ChequeDaoImpl chequeDao = new ChequeDaoImpl();
        PreparedStatement pstsmt;

        String query = "INSERT INTO orders VALUES (NULL, ?, ?, now())";
        try {
            pstsmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstsmt.setLong(1, order.getCustomer().getId());
            pstsmt.setDouble(2, order.getTotal());
            pstsmt.execute();

            ResultSet generatedKeys = pstsmt.getGeneratedKeys();
            if(generatedKeys.next()) order.setId(generatedKeys.getLong(1));
            else throw new SQLException("Creating order failed");

            for (OrderLine orderLine: order.getOrderLines()) {
                orderLine.setOrder(order);
                orderLineDao.create(orderLine);
            }

            for (Payment payment : order.getPayments()) {
                payment.setOrder(order);
                if(payment.getCheque() != null) {
                    payment.getCheque().setId(chequeDao.create(payment.getCheque()));
                }
                paymentDao.create(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */
