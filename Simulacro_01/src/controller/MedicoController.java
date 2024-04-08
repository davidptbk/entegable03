package controller;

import entity.Especialidad;
import entity.Medico;
import model.EspecialidadModel;
import model.MedicoModel;

import javax.swing.*;
import java.util.List;

public class MedicoController {
    public static void getAll(){
        MedicoModel objMedicoModel = new MedicoModel();
        List<Object> listMedicos = objMedicoModel.findAll();

        if (listMedicos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay medicos creado aun");
        } else {
            String listMedicosText = "Lista de medicos \n";
            for (Object iterador : listMedicos) {
                Medico objMedico = (Medico) iterador;
                listMedicosText += objMedico.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, listMedicosText);
        }
    }

    public static String getAllString(){
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedicos = "Lista de medicos \n";

        for (Object iterador : objMedicoModel.findAll()){
            Medico objMedico = (Medico) iterador;
            listMedicos += objMedico.toString() + "\n";
        }
        return listMedicos;
    }

    public static void  create(){
        MedicoModel objMedicoModel = new MedicoModel();

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del medico: ");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del medico");
        int idEspecialidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la especialidad del medico"));

        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        Especialidad objEspecialidad = objEspecialidadModel.findById(idEspecialidad);
        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null,"Especialdiad no encontrada");
        }else {
            Medico objMedico = new Medico();

            objMedico.setNombre(nombre);
            objMedico.setApellidos(apellidos);
            objMedico.setIdEspecialidad(idEspecialidad);
            objMedico.setObjEspecialidad(objEspecialidadModel.findById(idEspecialidad));

            JOptionPane.showMessageDialog(null, objMedico.toString());
        }
    }

    public static void delete(){
        MedicoModel objMedicoModel = new MedicoModel();

        String listMedicos = getAllString();

        int isDelete = Integer.parseInt(JOptionPane.showInputDialog(listMedicos+"\n Ingresa el id del meidco a eliminar: "));

        Medico objMedico = objMedicoModel.findById(isDelete);

        if (objMedico == null){
            JOptionPane.showMessageDialog(null, "Medico no encontrado");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro con eliminar este medico? "+objMedico.toString());
            if (confirm==0){
                objMedicoModel.delete(objMedico);
            }
        }
    }

    public static void update(){
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedicos = getAllString();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listMedicos+"\n Ingresa el id de el medico a actualizar: "));

        Medico objMedico = objMedicoModel.findById(idUpdate);

        if (objMedico == null){
            JOptionPane.showMessageDialog(null, "Medico no encontrado");
        }else {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del medico: ");
            String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del medico: ");
            int idEspecialidad = Integer.parseInt(JOptionPane.showInputDialog(EspecialidadController.getAllString() +"\nIngrese el id de la nueva especialidad"));

            EspecialidadModel objEspecialidadModel = new EspecialidadModel();
            Especialidad objEspecialidad = objEspecialidadModel.findById(idEspecialidad);

            if (objEspecialidad == null) {
                JOptionPane.showMessageDialog(null, "Especialidad no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            objMedico.setNombre(nombre);
            objMedico.setApellidos(apellidos);
            objMedico.setIdEspecialidad(idEspecialidad);

            objMedicoModel.update(objMedico);
        }
    }

    public static void FindByEspecialidad(){
        MedicoModel objMedicoModel = new MedicoModel();
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        try {
            String listMedicos = "Lista de medicos \n";
            int especialidad_id = Integer.parseInt(JOptionPane.showInputDialog(EspecialidadController.getAllString() + "\n Ingrese el id de la especialidad por la que buscara los medicos: "));

            Especialidad objEspecialidad = objEspecialidadModel.findById(especialidad_id);

            if (objEspecialidad != null){
                for (Object iterador : objMedicoModel.findMedicosByEspecialidad(especialidad_id)){
                    Medico objMedico = (Medico) iterador;
                    listMedicos += objMedico.toString() + "\n";
                }
                JOptionPane.showMessageDialog(null, "Los medicos con esta especialidad son: \n" + listMedicos + "\n");
            }else {
                JOptionPane.showMessageDialog(null, "Especialidad no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            }


        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id de la especialidad debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
