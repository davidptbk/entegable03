package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Pasajero;
import entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {
    @Override
    public Object create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = (Pasajero) obj;

        try {
            String sql= "INSERT INTO pasajero (nombre,apellido,documento_identidad) VALUES (?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellido());
            objPrepare.setString(3,objPasajero.getDocumento_identidad());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPasajero.setId(objResult.getInt(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listPasajeros = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM pasajero;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Pasajero objPasajero = new Pasajero();

                objPasajero.setId(objResult.getInt(1));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPasajeros.add(objPasajero);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPasajeros;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = (Pasajero) obj;

        try{

            String sql = "UPDATE pasajero SET nombre=?,apellido=?,documento_identidad=? WHERE id=?;";

            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            objprepare.setString(1,objPasajero.getNombre());
            objprepare.setString(2,objPasajero.getApellido());
            objprepare.setString(3,objPasajero.getDocumento_identidad());
            objprepare.setInt(4,objPasajero.getId());

            int totalRowsAfected = objprepare.executeUpdate();


            if (totalRowsAfected > 0){
                JOptionPane.showMessageDialog(null,"El vuelo se ha actualizado correctamente" +objPasajero.toString());
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }finally {
            ConfigDB.closeConnection();
        }
        return true;
    }

    @Override
    public boolean delete(Object obj) {
        Pasajero objPasajero = (Pasajero) obj;

        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;
        try {
            String sql = "DELETE * FROM pasajero WHERE id=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objPasajero.getId());

            int totalAfected = objPrepare.executeUpdate();

            if (totalAfected >0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Pasajero eliminado con exito");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    public Pasajero findById (int id){

        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = null;

        try{

            String sql = "SELECT * FROM pasajero WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objPasajero = new Pasajero();
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                objPasajero.setId(objResult.getInt("id"));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }

    public  List<Pasajero> findByNombre (String nombre){
        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = null;
        List<Pasajero> listPasajero = new ArrayList<>();

        try{

            String sql = "SELECT * FROM pasajero WHERE  nombre LIKE ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+nombre+"%");

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                objPasajero = new Pasajero();
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                objPasajero.setId(objResult.getInt("id"));

                listPasajero.add(objPasajero);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPasajero;
    }
}
