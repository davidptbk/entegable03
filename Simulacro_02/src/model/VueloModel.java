package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {
    @Override
    public Object create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Vuelo objVuelo= (Vuelo) obj;

        try {
            String sql= "INSERT INTO vuelo (destino,fecha_salida,hora_salida,id_avion) VALUES (?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setString(2,objVuelo.getFecha_salida());
            objPrepare.setString(3,objVuelo.getHora_salida());
            objPrepare.setInt(4, objVuelo.getId_avion());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objVuelo.setId(objResult.getInt(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listVuelos = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM vuelo;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vuelo objVuelo = new Vuelo();

                objVuelo.setId(objResult.getInt(1));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion(objResult.getInt("id_avion"));

                listVuelos.add(objVuelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelos;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Vuelo objVuelo = (Vuelo) obj;

        try{

            String sql = "UPDATE vuelo SET destino=?,fecha_salida=?,hora_salida=?,id_avion=? WHERE id=?;";

            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            objprepare.setString(1,objVuelo.getDestino());
            objprepare.setString(2,objVuelo.getFecha_salida());
            objprepare.setString(3,objVuelo.getHora_salida());
            objprepare.setInt(4,objVuelo.getId_avion());
            objprepare.setInt(5,objVuelo.getId());

            int totalRowsAfected = objprepare.executeUpdate();


            if (totalRowsAfected > 0){
                JOptionPane.showMessageDialog(null,"El vuelo se ha actualizado correctamente" +objVuelo.toString());
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
        Vuelo objVuelo = (Vuelo) obj;

        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;
        try {
            String sql = "DELETE * FROM vuelo WHERE id=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objVuelo.getId());

            int totalAfected = objPrepare.executeUpdate();

            if (totalAfected >0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Vuelo eliminado con exito");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    public Vuelo findById (int id){

        Connection objConnection = ConfigDB.openConnection();

        Vuelo objVuelo = null;

        try{

            String sql = "SELECT * FROM vuelo WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objVuelo = new Vuelo();
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId(objResult.getInt("id"));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }

    public  List<Vuelo> findByDestino (String destino){
        List<Vuelo> listVuelo = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{

            String sql = "SELECT * FROM vuelo WHERE  destino LIKE ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+destino+"%");

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                Vuelo objVuelo = new Vuelo();
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId(objResult.getInt("id"));

                listVuelo.add(objVuelo);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelo;
    }
}
