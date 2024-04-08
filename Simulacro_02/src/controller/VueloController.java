package controller;

import entity.Avion;
import entity.Vuelo;
import model.AvionModel;
import model.VueloModel;

import javax.swing.*;
import java.util.List;

public class VueloController {
    public static void getAllVuelos(){

        VueloModel objVueloModel = new VueloModel();

        String listVuelos = "LISTA DE VUELOS\n";

        for (Object Vuelo:  objVueloModel.findAll()){

           Vuelo objVuelo = (Vuelo) Vuelo;
            listVuelos += objVuelo.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listVuelos);
    }

    public static void getVueloDestino(){

        VueloModel objVueloModel = new VueloModel();

        String listVuelos = "LISTA DE VUELOS\n";

        String destino = JOptionPane.showInputDialog("Ingrese el destino por el cual va a buscar");

        listVuelos += objVueloModel.findByDestino(destino);

        JOptionPane.showMessageDialog(null, listVuelos);

    }

    public static String getAll(){

        VueloModel objVueloModel = new VueloModel();

        String listVuelos = "LISTA DE VUELOS\n";

        for (Object Vuelo:  objVueloModel.findAll()){

            Vuelo objVuelo = (Vuelo) Vuelo;
            listVuelos += objVuelo.toString()+"\n";
        }
        return listVuelos;
    }

    public static void createVuelo(){

        VueloModel objVueloModel = new VueloModel();

        String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo");
        String fecha = JOptionPane.showInputDialog("Ingrese la fecha del vuelo AÑO/MES/DIA");
        String hora = JOptionPane.showInputDialog("Ingrese el destino del vuelo HORAS:MINUTOS:SEGUNDOS ");
        int id_avion =Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del avion"));

        Vuelo objVuelo = new Vuelo();

        objVuelo.setDestino(destino);
        objVuelo.setFecha_salida(fecha);
        objVuelo.setHora_salida(hora);
        objVuelo.setId_avion(id_avion);

        objVuelo = (Vuelo) objVueloModel.create(objVuelo);

        JOptionPane.showMessageDialog(null,objVuelo.toString());

    }

    public static void deleteVuelo(){

        VueloModel objVueloModel = new VueloModel();

        String listVuelos = getAll();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVuelos+"\n Ingresa el id del vuelo que desea eliminar\n"));

        Vuelo objVuelo = objVueloModel.findById(idDelete);


        if (objVuelo == null){
            JOptionPane.showMessageDialog(null, "Vuelo no encpntrado");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar este vuelo?\n" + objVuelo.toString());
            if (confirm == 0) {
                objVueloModel.delete(objVuelo);
            }
        }
    }
    public static void updateVuelo(){

        VueloModel objVueloModel = new VueloModel();

        String listVuelo = getAll();

        int update = Integer.parseInt(JOptionPane.showInputDialog(listVuelo+"\nIngresa el ID del vuelo que deseas modificar: \n"));

        Vuelo objVuelo = objVueloModel.findById(update);

        if (objVuelo == null){
            JOptionPane.showMessageDialog(null,"No se encontro un Vuelo con ese id");
        }else {
            JOptionPane.showMessageDialog(null, objVuelo.toString());

            String destino = JOptionPane.showInputDialog(null,"Ingresa el nuevo destino del vuelo",objVuelo.getDestino());
            String fecha = JOptionPane.showInputDialog(null,"Ingresa la nueva fecha de salida",objVuelo.getFecha_salida());
            String hora = JOptionPane.showInputDialog(null,"Ingresa la nueva hota de salida",objVuelo.getHora_salida());
            int id_avion = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el id del avion para este vuelo",objVuelo.getId_avion()));

            objVuelo.setDestino(destino);
            objVuelo.setFecha_salida(fecha);
            objVuelo.setHora_salida(hora);
            objVuelo.setId_avion(id_avion);

            objVueloModel.update(objVuelo);
        }
    }

    public static void finByDestino(){

        String destino = JOptionPane.showInputDialog("Ingresa el destino a buscar: ");
        VueloModel objVueloModel = new VueloModel();

        String lisaDestinos = "DESTNOS ENCONTRADOS \n";

        for (Object vuelo: objVueloModel.findByDestino(destino)){
            lisaDestinos += vuelo.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,lisaDestinos);
    }
}
