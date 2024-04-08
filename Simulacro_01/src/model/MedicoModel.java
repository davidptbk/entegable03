package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) obj;

        try {
            String sql = "INSERT INTO medico (nombre, apellidos, id_especialidad) VALUES( ?, ?, ?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objMedico.getNombre());
            objPrepare.setString(2, objMedico.getApellidos());
            objPrepare.setInt(3, objMedico.getIdEspecialidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objMedico.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Medico creado correctamente");
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listMedicos = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON especialidad.id = medico.id_especialidad;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Medico objMedico = new Medico();
                Especialidad objEspecialidad = new Especialidad();

                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setIdEspecialidad(objResult.getInt("medico.id_especialidad"));

                objEspecialidad.setNombre(objResult.getString("especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("especialidad.descripcion"));
                objEspecialidad.setId(objResult.getInt("especialidad.id"));

                objMedico.setObjEspecialidad(objEspecialidad);

                listMedicos.add(objMedico);

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listMedicos;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE medico SET nombre = ?, apellidos = ?, id_especialidad = ? WHERE id_medico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objMedico.getNombre());
            objPrepare.setString(2, objMedico.getApellidos());
            objPrepare.setInt(3, objMedico.getIdEspecialidad());
            objPrepare.setInt(4, objMedico.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El medico se actualizo correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }


    @Override
    public boolean delete(Object obj) {
        Medico objMedico = (Medico) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM medico WHERE id_medico = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objMedico.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "El medico se elimino correctamente");
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return isDelete;
    }
    public Medico findById (int id){

        Connection objConnection = ConfigDB.openConnection();

        Medico objMedico = null;

        try{

            String sql = "SELECT * FROM medico WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objMedico = new Medico();
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId(objResult.getInt("id"));
                objMedico.setIdEspecialidad(objResult.getInt("id_especialidad"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objMedico;
    }

    public List<Object> findMedicosByEspecialidad(int especialidad_id) {

        List<Object> listMedicos = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON especialidad.id = ? WHERE medico.id_especialidad = especialidad.id;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, especialidad_id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Medico objMedico = new Medico();
                Especialidad objEspecialidad = new Especialidad();

                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setIdEspecialidad(objResult.getInt("medico.id_especialidad"));

                objEspecialidad.setNombre(objResult.getString("especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("especialidad.descripcion"));
                objEspecialidad.setId(objResult.getInt("especialidad.id"));

                objMedico.setObjEspecialidad(objEspecialidad);

                listMedicos.add(objMedico);
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return listMedicos;
    }

}
