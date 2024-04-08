package controller;

import entity.Paciente;
import model.PacienteModel;

import javax.swing.*;
import java.util.List;

public class PacienteController {
    public static void getAll() {
        PacienteModel objPacienteModel = new PacienteModel();
        List<Object> listaPacientes = objPacienteModel.findAll();

        if (listaPacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pacientes para listar");
        } else {
            String listPacientesText = "Lista de Pacientes \n";
            for (Object iterador : listaPacientes) {
                Paciente objPaciente = (Paciente) iterador;
                listPacientesText += objPaciente.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, listPacientesText);
        }
    }
    public static String getAllString() {
        PacienteModel objPacienteModel = new PacienteModel();
        String listPacientes = "Lista de pacientes \n";

        for (Object iterador : objPacienteModel.findAll()) {
            Paciente objPaciente = (Paciente) iterador;
            listPacientes += objPaciente.toString() + "\n";
        }
        return listPacientes;
    }
    public static void create(){
        PacienteModel objPacienteModel = new PacienteModel();

        String nombre = JOptionPane.showInputDialog("Inserte el nombre del paciente");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del paciente");
        String fechaNacimiento = JOptionPane.showInputDialog("Ingresa la fecha de nacimiento del paciente YYYY-MM-DD");
        String documentoIdentidad = JOptionPane.showInputDialog("Ingrese el documento de identidad del paciente");

        Paciente objPaciente = new Paciente();
        objPaciente.setNombre(nombre);
        objPaciente.setApellidos(apellidos);
        objPaciente.setFechaNacimiento(fechaNacimiento);
        objPaciente.setDocumentoIdentidad(documentoIdentidad);

        objPacienteModel.insert(objPaciente);

        JOptionPane.showMessageDialog(null, "Paciente agregado correctamente.");
    }
    public static void delete(){
        PacienteModel objPacienteModel = new PacienteModel();
        String listPacientes = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPacientes + "\n Ingresa el id de el paciente a eliminar"));
        Paciente objPaciente = objPacienteModel.findById(idDelete);
        int confirm = 1;

        if (objPaciente == null){
            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de borrar este paciente?"+ objPaciente.toString());
            if (confirm == 0){
                objPacienteModel.delete(objPaciente);
            }
        }
    }
    public static void update() {
        PacienteModel objPacienteModel = new PacienteModel();
        String listPacientes = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPacientes + "\nIngresa el id del paciente a actualizar"));
        Paciente objPaciente = objPacienteModel.findById(idUpdate);

        if (objPaciente == null) {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
        } else {
            String nombre = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del paciente:", objPaciente.getNombre());
            String apellidos = JOptionPane.showInputDialog(null, "Ingrese los nuevos apellidos del paciente:", objPaciente.getApellidos());
            String fechaNacimiento = JOptionPane.showInputDialog(null, "Ingrese la nueva fecha de nacimiento del paciente (YYYY-MM-DD):", objPaciente.getFechaNacimiento());
            String documentoIdentidad = JOptionPane.showInputDialog(null, "Ingrese el nuevo documento de identidad del paciente:", objPaciente.getDocumentoIdentidad());

            objPaciente.setNombre(nombre);
            objPaciente.setApellidos(apellidos);
            objPaciente.setFechaNacimiento(fechaNacimiento);
            objPaciente.setDocumentoIdentidad(documentoIdentidad);

            objPacienteModel.update(objPaciente);

            JOptionPane.showMessageDialog(null, "Paciente actualizado correctamente.");
        }
    }

    public static void findByDocument() {
        PacienteModel objPacienteModel = new PacienteModel();


        int documento = Integer.parseInt(JOptionPane.showInputDialog("Inserte el documento de identidad del paciente a buscar: "));

        Paciente objPaciente = objPacienteModel.findByDocument(documento);

        if (objPaciente != null) {
            JOptionPane.showMessageDialog(null, "El paciente con este documento es: \n" + objPaciente);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un paciente con el documento " + documento, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
