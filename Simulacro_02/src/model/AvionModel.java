package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD {

    @Override
    public Object create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Avion objAvion = (Avion) obj;

        try {
            String sql= "INSERT INTO avion (modelo,capacidad) VALUES (?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objAvion.getModelo());
            objPrepare.setInt(2,objAvion.getCapacidad());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objAvion.setId(objResult.getInt(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAvion;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listAviones = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM avion;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Avion objAvion = new Avion();

                objAvion.setId(objResult.getInt(1));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));

                listAviones.add(objAvion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listAviones;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        Avion objAvion = (Avion) obj;

        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;
        try {
            String sql = "DELETE * FROM avion WHERE id=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objAvion.getId());

            int totalAfected = objPrepare.executeUpdate();

            if (totalAfected >0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Avion eliminado con exito");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    public Avion findById (int id){

        Connection objConnection = ConfigDB.openConnection();

        Avion objAvion = null;

        try{

            String sql = "SELECT * FROM avion WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objAvion = new Avion();
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
                objAvion.setId(objResult.getInt("id"));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAvion;
    }
}
