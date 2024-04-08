package controller;

import entity.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;

public class EspecialidadController {

    public static void getAll(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidad = "Lista de especialidades \n";

        for (Object iterador : objEspecialidadModel.findAll()){
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listEspecialidad);
    }
    public static String getAllString(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidad = "Lista de especialidades \n";

        for (Object iterador : objEspecialidadModel.findAll()){
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }
        return listEspecialidad;
    }

    public static void create(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        String nombre = JOptionPane.showInputDialog("Inserte el nombre de la especialidad");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la especialidad");

        Especialidad objEspecialidad = new Especialidad();
        objEspecialidad.setNombre(nombre);
        objEspecialidad.setDescripcion(descripcion);

        objEspecialidad = (Especialidad) objEspecialidadModel.insert(objEspecialidad);

        JOptionPane.showMessageDialog(null, objEspecialidad.toString());
    }
    public static void delete(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidades = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidades + "\n Ingresa el id de la especialidad a eliminar"));
        Especialidad objEspecialidad = objEspecialidadModel.findById(idDelete);
        int confirm = 1;

        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null, "Especialidad no encontrada");
        }else{
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de borrar esta especialidad?. \n" +
                    "Ten en cuenta que se eliminaran todos los medicos que incluye esta especialidad y con el medico" +
                    " las citas de este. \n" + objEspecialidad.toString());

            if (confirm == 0){
                objEspecialidadModel.delete(objEspecialidad);
            }
        }
    }

    public static void update(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidad = getAllString();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidad + "\n Ingresa el id de la especialidad a actualizar"));

        Especialidad objEspecialidad = objEspecialidadModel.findById(idUpdate);

        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null, "Especialidad no encontrada");
        }else {
            String nombre = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre de la especialidad: ");
            String descripcion = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la especialidad: ");

            objEspecialidad.setNombre(nombre);
            objEspecialidad.setDescripcion(descripcion);

            objEspecialidadModel.update(objEspecialidad);
        }
    }
}
