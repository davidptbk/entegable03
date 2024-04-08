package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD {


    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = (Especialidad) obj;

        try {
            String sql = "INSERT INTO especialidad (nombre, descripcion) VALUES( ?, ?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objEspecialidad.getNombre());
            objPrepare.setString(2, objEspecialidad.getDescripcion());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objEspecialidad.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Especialidad creada correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listEspecialidades = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM especialidad;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Especialidad objEspecialidad = new Especialidad();

                objEspecialidad.setId(objResult.getInt("id"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));

                listEspecialidades.add(objEspecialidad);
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());        }

        ConfigDB.closeConnection();
        return listEspecialidades;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = (Especialidad) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ? WHERE id_especialidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objEspecialidad.getNombre());
            objPrepare.setString(2, objEspecialidad.getDescripcion());
            objPrepare.setInt(3, objEspecialidad.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "La especialidad fue actualizada correctamente");
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Especialidad objEspecialidad = (Especialidad) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM especialidad WHERE id_especialidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objEspecialidad.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "La especialidad se elimino correctamente");
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return isDelete;
    }
    public Especialidad findById(int id_especialidad){
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = null;

        try {
            String sql = "SELECT * FROM especialidad WHERE id_especialidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id_especialidad);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objEspecialidad = new Especialidad();
                objEspecialidad.setId(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return objEspecialidad;
    }
}
