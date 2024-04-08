package controller;

import entity.Avion;
import model.AvionModel;

import javax.swing.*;

public class AvionController {
    public static void getAllAviones(){

        AvionModel objAvionModel = new AvionModel();

        String listAviones = "AVIONES LIST\n";

        for (Object Avion:  objAvionModel.findAll()){

            Avion objAvion = (Avion) Avion;
            listAviones += objAvion.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listAviones);
    }

    public static String getAll(){

        AvionModel objAvionModel = new AvionModel();

        String listAviones = "AVIONES LIST\n";

        for (Object Avion:  objAvionModel.findAll()){

            Avion objAutor = (Avion) Avion;
            listAviones += objAutor.toString()+"\n";
        }
        return listAviones;
    }

    public static void createAvion(){

        AvionModel objAvionModel = new AvionModel();

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del avion");
        int capacidad =Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad del avion"));

        Avion objAvion = new Avion();

        objAvion.setModelo(modelo);
        objAvion.setCapacidad(capacidad);

        objAvion = (Avion) objAvionModel.create(objAvion);

        JOptionPane.showMessageDialog(null,objAvion.toString());

    }

    public static void deleteAvion(){

        AvionModel objAvionModel = new AvionModel();

        String listAutors = getAll();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAutors+"\n Ingresa el id del avion que vas a eliminar\n"));

        Avion objAvion = objAvionModel.findById(idDelete);


        if (objAvion == null){
            JOptionPane.showMessageDialog(null, "Avion no encpntrado");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar este avion?\n" + objAvion.toString());
            if (confirm == 0) {
                objAvionModel.delete(objAvion);
            }
        }
    }
    public static void updateAvion(){

        AvionModel objAvionModel = new AvionModel();

        String listAvion = getAll();

        int update = Integer.parseInt(JOptionPane.showInputDialog(listAvion+"\nIngresa el ID del avion que deseas modificar: \n"));

        Avion objAvion = objAvionModel.findById(update);

        if (objAvion == null){
            JOptionPane.showMessageDialog(null,"No se encontro un Avion con ese id");
        }else {
            JOptionPane.showMessageDialog(null, objAvion.toString());
            String modelo = JOptionPane.showInputDialog(null,"Ingresa el nuevo modelo",objAvion.getModelo());
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la nueva capacidad",objAvion.getCapacidad()));

            objAvion.setModelo(modelo);
            objAvion.setCapacidad(capacidad);
            objAvionModel.update(objAvion);
        }
    }

}
