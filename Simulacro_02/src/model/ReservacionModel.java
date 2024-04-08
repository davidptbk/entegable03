package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Reservacion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModel implements CRUD {

    //funcion para validar con true o false si ya esta un asiento reservado
    public Boolean findByAsiento (String asiento){
        Connection objConnection = ConfigDB.openConnection();
        try{

            String sql = "SELECT * FROM reservacion WHERE asiento = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,asiento);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                return true;
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return false;
    }
    @Override
    public Object create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Reservacion objReservacion= (Reservacion) obj;

        try {
            String sql= "INSERT INTO reservacion (id_pasajero,id_vuelo,fecha_reservacion,asiento) VALUES (?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objReservacion.getId_pasajero());
            objPrepare.setInt(2,objReservacion.getId_vuelo());
            objPrepare.setString(3,objReservacion.getFecha_reservacion());
            objPrepare.setString(4, objReservacion.getAsiento());
            Boolean validar = findByAsiento(objReservacion.getAsiento());

            if (!validar){
                objPrepare.execute();
                ResultSet objResult = objPrepare.getGeneratedKeys();

                while (objResult.next()){
                    objReservacion.setId(objResult.getInt(1));
                }
            }else {
                JOptionPane.showMessageDialog(null,"EL asiento ya se encuentra reservado");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objReservacion;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listReservaciones = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM reservacion;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Reservacion objReservacion = new Reservacion();

                objReservacion.setId(objResult.getInt("id"));
                objReservacion.setId_pasajero(objResult.getInt("id_pasajero"));
                objReservacion.setId_vuelo(objResult.getInt("id_vuelo"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));

                listReservaciones.add(objReservacion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservaciones;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Reservacion objReservacion = (Reservacion) obj;

        try{

            String sql = "UPDATE reservacion SET id_pasajero=?,id_vuelo=?,fecha_reservacion=?,asiento=? WHERE id=?;";

            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            objprepare.setInt(1,objReservacion.getId_pasajero());
            objprepare.setInt(2,objReservacion.getId_vuelo());
            objprepare.setString(3,objReservacion.getFecha_reservacion());
            objprepare.setString(4,objReservacion.getAsiento());
            objprepare.setInt(5,objReservacion.getId());

            int totalRowsAfected = objprepare.executeUpdate();


            if (totalRowsAfected > 0){
                JOptionPane.showMessageDialog(null,"El vuelo se ha actualizado correctamente" +objReservacion.toString());
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
        Reservacion objReservacion = (Reservacion) obj;

        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;
        try {
            String sql = "DELETE * FROM reservacion WHERE id=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objReservacion.getId());

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

    public Reservacion findById (int id){

        Connection objConnection = ConfigDB.openConnection();

        Reservacion objReserva = null;

        try{

            String sql = "SELECT * FROM reservacion WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objReserva = new Reservacion();
                objReserva.setId_pasajero(objResult.getInt("id_pasajero"));
                objReserva.setId_vuelo(objResult.getInt("id_vuelo"));
                objReserva.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReserva.setAsiento(objResult.getString("asiento"));
                objReserva.setId(objResult.getInt("id"));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return objReserva;
    }

    public List<Object> findAllReservacionesVuelo(int vuelo) {
        List<Object> listReservaciones = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM reservacion \n"+
            "INNER JOIN vuelo ON vuelo.id = reservacion.id_vuelo WHERE id_vuelo = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, vuelo);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Reservacion objReservacion = new Reservacion();

                objReservacion.setId(objResult.getInt("id"));
                objReservacion.setId_pasajero(objResult.getInt("id_pasajero"));
                objReservacion.setId_vuelo(objResult.getInt("id_vuelo"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));

                listReservaciones.add(objReservacion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservaciones;
    }

}
