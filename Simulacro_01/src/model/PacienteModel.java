package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;

        try {
            String sql = "INSERT INTO paciente (nombre, apellidos, fecha_nacimiento, documento_identidad) VALUES( ?, ?, ?, ?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            objPrepare.setString(3, objPaciente.getFechaNacimiento());
            objPrepare.setString(4, objPaciente.getDocumentoIdentidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPaciente.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Paciente agregado correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return objPaciente;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listPacientes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM paciente;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Paciente objPaciente = new Paciente();

                objPaciente.setId(objResult.getInt("id"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));

                listPacientes.add(objPaciente);
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return listPacientes;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE paciente SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, documento_identidad = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            objPrepare.setString(3, objPaciente.getFechaNacimiento());
            objPrepare.setString(4, objPaciente.getDocumentoIdentidad());
            objPrepare.setInt(5, objPaciente.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El paciente se actualizo correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Paciente objPaciente = (Paciente) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM paciente WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objPaciente.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "El paciente se elimino correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }
    public Paciente findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;

        try {
            String sql = "SELECT * FROM paciente WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objPaciente = new Paciente();
                objPaciente.setId(objResult.getInt("id"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));
            }
        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }
    public Paciente findByDocument(int document_paciente){
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;

        try {
            String sql = "SELECT * FROM paciente WHERE documento_identidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, document_paciente);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objPaciente = new Paciente();
                objPaciente.setId(objResult.getInt("id"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));
            }
        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }
}
