package controller;

import entity.Avion;
import entity.Pasajero;
import model.AvionModel;
import model.PasajeroModel;
import model.VueloModel;

import javax.swing.*;

public class PasajeroController {
    public static void getAllPasajeros(){

        PasajeroModel objPasajeroModel = new PasajeroModel();

        String listPasajeros = "LISTA DE PASAJEROS \n";

        for (Object Pasajero:  objPasajeroModel.findAll()){

            Pasajero objPasajeros = (Pasajero) Pasajero;
            listPasajeros += objPasajeros.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listPasajeros);
    }

    public static String getAll(){

        PasajeroModel objPasajeroModel = new PasajeroModel();

        String listPasajeros = "LISTA DE PASAJEROS\n";

        for (Object Pasajero:  objPasajeroModel.findAll()){

            Pasajero objPasajero = (Pasajero) Pasajero;
            listPasajeros += objPasajero.toString()+"\n";
        }
        return listPasajeros;
    }

    public static void createPasajero(){

        PasajeroModel objPasajeroModel = new PasajeroModel();

        String nombre = JOptionPane.showInputDialog("Ingrese los nombres del pasajero");
        String apellido = JOptionPane.showInputDialog("Ingrese los apelllidos del pasajero");
        String documento = JOptionPane.showInputDialog("Ingrese el documento de identidad del pasajero");


        Pasajero objPasajero = new Pasajero();

        objPasajero.setNombre(nombre);
        objPasajero.setApellido(apellido);
        objPasajero.setDocumento_identidad(documento);

        objPasajero = (Pasajero) objPasajeroModel.create(objPasajero);

        JOptionPane.showMessageDialog(null,objPasajero.toString());

    }

    public static void deletePasajero(){

        PasajeroModel objPasajeroModel = new PasajeroModel();

        String listPasajero = getAll();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPasajero+"\n Ingresa el id del pasajero que vas a eliminar\n"));

        Pasajero objPasajero = objPasajeroModel.findById(idDelete);


        if (objPasajero == null){
            JOptionPane.showMessageDialog(null, "Pasajero no encontrado");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar este pasajero?\n" + objPasajero.toString());
            if (confirm == 0) {
                objPasajeroModel.delete(objPasajero);
            }
        }
    }
    public static void updatePasajero(){

        PasajeroModel objPasajeroModel = new PasajeroModel();

        String listPasajero = getAll();

        int update = Integer.parseInt(JOptionPane.showInputDialog(listPasajero+"\nIngresa el ID del avion que deseas modificar: \n"));

        Pasajero objPasajero = objPasajeroModel.findById(update);

        if (objPasajero == null){
            JOptionPane.showMessageDialog(null,"No se encontro un Avion con ese id");
        }else {
            JOptionPane.showMessageDialog(null, objPasajero.toString());
            String nombres = JOptionPane.showInputDialog(null,"Ingresa los nombres del pasajero: ",objPasajero.getNombre());
            String apellidos = JOptionPane.showInputDialog(null,"Ingresa los apellidos del pasajero: ",objPasajero.getApellido());
            String documento = JOptionPane.showInputDialog(null,"Ingresa el documento de identidad el pasajero: ",objPasajero.getDocumento_identidad());

            objPasajero.setNombre(nombres);
            objPasajero.setApellido(apellidos);
            objPasajero.setDocumento_identidad(documento);
            objPasajeroModel.update(objPasajero);
        }
    }
    public static void getPasajeroNombre(){

        PasajeroModel objPasajeroModel = new PasajeroModel();

        String listPasajeros = "LISTA DE VUELOS\n";

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre por el cual va a buscar");

        listPasajeros += objPasajeroModel.findByNombre(nombre);

        JOptionPane.showMessageDialog(null, listPasajeros);

    }

}
