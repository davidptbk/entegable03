package controller;

import entity.Cita;
import entity.Medico;
import entity.Paciente;
import model.CitaModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;
import java.util.List;

public class CitaController {
    public static void getAll(){
        CitaModel objCitaModel = new CitaModel();
        List<Object> listCitas = objCitaModel.findAll();

        if (listCitas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay citas para listar");
        } else {
            String listCitasText = "Lista de citas \n";
            for (Object iterador : listCitas) {
                Cita objCita = (Cita) iterador;
                listCitasText += objCita.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, listCitasText);
        }
    }
    public static String getAllString(){
        CitaModel objCitaModel = new CitaModel();
        String listCitas = "Lista de citas \n";

        for (Object iterador : objCitaModel.findAll()){
            Cita objCita = (Cita) iterador;
            listCitas += objCita.toString() + "\n";
        }
        return listCitas;
    }

    public static void create(){


        CitaModel objCitaModel = new CitaModel();

        int paciente_id = Integer.parseInt(JOptionPane.showInputDialog(PacienteController.getAllString() + "\n Ingrese el id de el paciente que tomara la cita"));
        int medico_id = Integer.parseInt(JOptionPane.showInputDialog(MedicoController.getAllString() + "\n Ingrese el id de el medico que tomara la cita"));
        String fecha_cita = JOptionPane.showInputDialog("Inserte la fecha en la que se agendara la cita (YYYY-MM-DD): ");
        String hora_cita = JOptionPane.showInputDialog("Inserte la hora en la agendara la cita en formato 24 horas, con segundos (HH:MM:SS): ");
        String motivo = JOptionPane.showInputDialog("Inserte el motivo de la cita: ");

        PacienteModel objPacienteModel = new PacienteModel();
        Paciente objPaciente = objPacienteModel.findById(paciente_id);

        MedicoModel objMedicoModel = new MedicoModel();
        Medico objMedico = objMedicoModel.findById(medico_id);

        if (objPaciente == null){
            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
        }else if (objMedico == null) {
            JOptionPane.showMessageDialog(null, "Medico no encontrado");
        } else{
            Cita objCita = new Cita();
            objCita.setIdPaciente(paciente_id);
            objCita.setIdMedico(medico_id);
            objCita.setFechaCita(fecha_cita);
            objCita.setHoraCita(hora_cita);
            objCita.setMotivo(motivo);

            objCita = (Cita) objCitaModel.insert(objCita);

            JOptionPane.showMessageDialog(null, objCita.toString());
        }
    }

    public static void delete(){
        CitaModel objCitaModel = new CitaModel();
        String listCitas = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCitas + "\n Ingresa el id de la cita a eliminar"));
        Cita objCita = objCitaModel.findCitaById(idDelete);

        if (objCita == null){
            JOptionPane.showMessageDialog(null, "Cita no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de borrar esta cita? \n" + objCita.toString());
            if (confirm == 0){
                objCitaModel.delete(objCita);
            }
        }
    }

    public static void update(){
        CitaModel objCitaModel = new CitaModel();
        String listCitas = getAllString();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCitas + "\n Ingresa el id de la cita a actualizar"));

        Cita objCita = objCitaModel.findCitaById(idUpdate);

        if (objCita == null){
            JOptionPane.showMessageDialog(null, "Cita no encontrada");
        }else {
                int paciente_id = Integer.parseInt(JOptionPane.showInputDialog(PacienteController.getAllString() + "\n Ingrese el id de el nuevo paciente que tomara la cita: "));
                int medico_id = Integer.parseInt(JOptionPane.showInputDialog(MedicoController.getAllString() + "\n Ingrese el id de el nuevo medico que tomara la cita: "));
                String fecha_cita = JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de la cita (YYYY-MM-DD): ");
                String hora_cita = JOptionPane.showInputDialog(null, "Ingrese la nueva hora de la cita en formato 24 horas,(HH:MM:SS): ");
                String motivo_cita = JOptionPane.showInputDialog(null, "Ingrese el nuevo motivo de la cita: ");

                PacienteModel objPacienteModel = new PacienteModel();
                Paciente objPaciente = objPacienteModel.findById(paciente_id);

                MedicoModel objMedicoModel = new MedicoModel();
                Medico objMedico = objMedicoModel.findById(medico_id);

                if (objPaciente == null) {
                    JOptionPane.showMessageDialog(null, "Paciente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (objMedico == null) {
                    JOptionPane.showMessageDialog(null, "Medico no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                objCita.setId(paciente_id);
                objCita.setIdMedico(medico_id);
                objCita.setFechaCita(fecha_cita);
                objCita.setHoraCita(hora_cita);
                objCita.setMotivo(motivo_cita);

                objCitaModel.update(objCita);
            }
    }

    public static void FindCitasByFecha() {
        CitaModel objCitaModel = new CitaModel();

        try {
            String fecha_cita = JOptionPane.showInputDialog("Ingrese la fecha a buscar (YYYY-MM-DD): ");
            List<Object> citas = objCitaModel.findCitaByFecha(fecha_cita);

            if (citas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay citas asignadas para esta fecha.");
                return;
            }

            String listCitas = "";
            for (Object iterador : citas) {
                Cita objCita = (Cita) iterador;
                listCitas += objCita.toString() +"\n";
            }
            JOptionPane.showMessageDialog(null, listCitas.toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
