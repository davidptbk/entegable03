package controller;

import entity.Avion;
import entity.Reservacion;
import model.AvionModel;
import model.ReservacionModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ReservacionController {

    public static void getAllReservas(){

        ReservacionModel objReservasModel = new ReservacionModel();

        String listRerservaciones = "LISTA DE RESERVAS \n";

        for (Object Reserva:  objReservasModel.findAll()){

            Reservacion objReservacion = (Reservacion) Reserva;
            listRerservaciones += objReservacion.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listRerservaciones);
    }

    public static String getAll(){

        ReservacionModel objReservasModel = new ReservacionModel();

        String listRerservaciones = "LISTA DE RESERVAS \n";

        for (Object Reserva:  objReservasModel.findAll()){

            Reservacion objReservacion = (Reservacion) Reserva;
            listRerservaciones += objReservacion.toString()+"\n";
        }
        return listRerservaciones;
    }

    public static void createReservas(){

        ReservacionModel objReservasModel = new ReservacionModel();

        int id_pasajero =Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del pasajero que usara la reserva"));
        int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del vuelo para la reserva"));
        String fecha = JOptionPane.showInputDialog("Ingrese la fecha de la reservacion");
        String asiento = JOptionPane.showInputDialog("Ingrese el asiento de la reservacion");


        Reservacion objReservacion = new Reservacion();

        objReservacion.setId_pasajero(id_pasajero);
        objReservacion.setId_vuelo(id_vuelo);
        objReservacion.setFecha_reservacion(fecha);
        objReservacion.setAsiento(asiento);

        objReservacion = (Reservacion) objReservasModel.create(objReservacion);

        JOptionPane.showMessageDialog(null,objReservacion.toString());

    }

    public static void deleteReserva(){

        ReservacionModel objReservasModel = new ReservacionModel();

        String listReservaciones = getAll();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listReservaciones+"\n Ingresa el id de la reserva que vas a eliminar\n"));

        Reservacion objReservacion = objReservasModel.findById(idDelete);


        if (objReservacion == null){
            JOptionPane.showMessageDialog(null, "Avion no encpntrado");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar este avion?\n" + objReservacion.toString());
            if (confirm == 0) {
                objReservasModel.delete(objReservacion);
            }
        }
    }
    public static void updateReservacion(){

        ReservacionModel objReservasModel = new ReservacionModel();

        String listReservaciones = getAll();

        int update = Integer.parseInt(JOptionPane.showInputDialog(listReservaciones+"\nIngresa el ID de la reservacion que deseas modificar: \n"));

        Reservacion objReserva = objReservasModel.findById(update);

        if (objReserva == null){
            JOptionPane.showMessageDialog(null,"No se encontro una reserva con ese id");
        }else {
            JOptionPane.showMessageDialog(null,objReserva.toString());
            int id_pasajero = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el id del pasajero",objReserva.getId_pasajero()));
            int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el id del vuelo para la reserva",objReserva.getId_vuelo()));
            String fecha_reserva = JOptionPane.showInputDialog(null,"Ingresa la nueva fecha de reserva",objReserva.getFecha_reservacion());
            String asiento = JOptionPane.showInputDialog(null,"Ingresa el nuevo asiento ",objReserva.getAsiento());


            objReserva.setId_pasajero(id_pasajero);
            objReserva.setId_vuelo(id_vuelo);
            objReserva.setFecha_reservacion(fecha_reserva);
            objReserva.setAsiento(asiento);
            objReservasModel.update(objReserva);
        }
    }

    public static void getAllReservasVuelo(){

        ReservacionModel objReservasModel = new ReservacionModel();

        String listRerservaciones = "LISTA DE RESERVAS \n";
        int vuelo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del vuelo"));
        for (Object Reserva:  objReservasModel.findAllReservacionesVuelo(vuelo)){

            Reservacion objReservacion = (Reservacion) Reserva;
            listRerservaciones += objReservacion.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listRerservaciones);
    }
}
