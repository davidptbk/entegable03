package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Cita;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;

        try {
            String sql = "INSERT INTO cita (fecha_cita, hora_cita, motivo, id_paciente, id_medico ) VALUES( ?, ?, ?, ?, ?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);


            objPrepare.setString(1, objCita.getFechaCita());
            objPrepare.setString(2, objCita.getHoraCita());
            objPrepare.setString(3, objCita.getMotivo());
            objPrepare.setInt(4, objCita.getIdPaciente());
            objPrepare.setInt(5, objCita.getIdMedico());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCita.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Cita agregada correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return objCita;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listCitas = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM cita " +
                    "INNER JOIN medico ON medico.id = cita.id_medico " +
                    "INNER JOIN paciente ON paciente.id = cita.id_paciente;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Cita objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();

                objCita.setId(objResult.getInt("cita.id"));
                objCita.setIdPaciente(objResult.getInt("cita.id_paciente"));
                objCita.setIdMedico(objResult.getInt("cita.id_medico"));
                objCita.setFechaCita(objResult.getString("cita.fecha_cita"));
                objCita.setHoraCita(objResult.getString("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));

                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setIdEspecialidad(objResult.getInt("medico.id_especialidad"));

                objPaciente.setId(objResult.getInt("paciente.id"));
                objPaciente.setNombre(objResult.getString("paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("paciente.apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("paciente.fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("paciente.documento_identidad"));

                objCita.setObjPaciente(objPaciente);
                objCita.setObjMedico(objMedico);

                listCitas.add(objCita);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listCitas;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE cita SET fecha_cita = ?, hora_cita = ?, motivo = ?, id_paciente = ?, id_medico = ?  WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);


            objPrepare.setString(1, objCita.getFechaCita());
            objPrepare.setString(2, objCita.getHoraCita());
            objPrepare.setString(3, objCita.getMotivo());
            objPrepare.setInt(4, objCita.getIdPaciente());
            objPrepare.setInt(5, objCita.getIdMedico());
            objPrepare.setInt(6, objCita.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "La cita se actualizo correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Cita objCita = (Cita) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM cita WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objCita.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "La cita se elimino correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return isDelete;
    }
    public Cita findCitaById(int id_cita){
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;

        try {
            String sql = "SELECT * FROM cita " +
                    "INNER JOIN paciente ON paciente.id = cita.id_paciente " +
                    "INNER JOIN medico ON medico.id = cita.id_medico " +
                    "WHERE cita.id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id_cita);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();

                objCita.setId(objResult.getInt("cita.id"));
                objCita.setIdPaciente(objResult.getInt("cita.id_paciente"));
                objCita.setIdMedico(objResult.getInt("cita.id_medico"));
                objCita.setFechaCita(objResult.getString("cita.fecha_cita"));
                objCita.setHoraCita(objResult.getString("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));

                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setIdEspecialidad(objResult.getInt("medico.id_especialidad"));

                objPaciente.setId(objResult.getInt("paciente.id"));
                objPaciente.setNombre(objResult.getString("paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("paciente.apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("paciente.fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("paciente.documento_identidad"));

                objCita.setObjPaciente(objPaciente);
                objCita.setObjMedico(objMedico);
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        ConfigDB.closeConnection();
        return objCita;
    }
    public List<Object> findCitaByFecha(String fecha_cita) {

        List<Object> listCitas = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT cita.*, medico.*, paciente.* FROM cita " +
                    "INNER JOIN medico ON medico.id = cita.id_medico " +
                    "INNER JOIN paciente ON paciente.id = cita.id_paciente " +
                    "WHERE cita.fecha_cita = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, fecha_cita);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Cita objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();

                objCita.setId(objResult.getInt("cita.id"));
                objCita.setIdPaciente(objResult.getInt("cita.id_paciente"));
                objCita.setIdMedico(objResult.getInt("cita.id_medico"));
                objCita.setFechaCita(objResult.getString("cita.fecha_cita"));
                objCita.setHoraCita(objResult.getString("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));

                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setIdEspecialidad(objResult.getInt("medico.id_especialidad"));

                objPaciente.setId(objResult.getInt("paciente.id"));
                objPaciente.setNombre(objResult.getString("paciente.nombre"));
                objPaciente.setApellidos(objResult.getString("paciente.apellidos"));
                objPaciente.setFechaNacimiento(objResult.getString("paciente.fecha_nacimiento"));
                objPaciente.setDocumentoIdentidad(objResult.getString("paciente.documento_identidad"));

                objCita.setObjPaciente(objPaciente);
                objCita.setObjMedico(objMedico);

                listCitas.add(objCita);
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return listCitas;
    }
}
