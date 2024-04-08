import controller.AvionController;
import controller.PasajeroController;
import controller.ReservacionController;
import controller.VueloController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String optionAviones = "";
        String optionMenu = "";
        String optionPasajeros = "";
        String optionReservaciones ="";
        String optionVuelos = "";

        do {
            optionMenu = JOptionPane.showInputDialog("""
                    1. Aviones
                    2. Vuelos
                    3. Pasajeros
                    4. Reservas
                    5. Exit
                    
                    Ingresa una opcion:
                    """);

            switch (optionMenu) {
                case "1":
                    optionAviones = JOptionPane.showInputDialog("""
                    1. Mostrar Aviones
                    2. Crear Avion
                    3. Editar Avion
                    4. Eliminar Avion
                    
                    
                    Ingresa una opcion:
                    """);

                    switch (optionAviones){
                        case "1":
                            AvionController.getAllAviones();
                            break;
                        case "2":
                            AvionController.createAvion();
                            break;
                        case "3":
                            AvionController.updateAvion();
                            break;
                        case "4":
                            AvionController.deleteAvion();
                            break;
                    }
                    break;
                case "2":
                    optionVuelos = JOptionPane.showInputDialog("""
                            1. Mostrar Vuelos
                            2. Mostrar Vuelos por destino
                            3. Crear Vuelo
                            4. Editar Vuelo
                            5. Eliminar Vuelo
                            
                            Ingresa una opcion:
                            """);
                    switch (optionVuelos){
                        case "1":
                            VueloController.getAllVuelos();
                            break;
                        case "2":
                            VueloController.getVueloDestino();
                            break;
                        case "3":
                            VueloController.createVuelo();
                            break;
                        case "4":
                            VueloController.updateVuelo();
                            break;
                        case "5":
                            VueloController.deleteVuelo();
                            break;
                    }
                    break;
                case "3":
                    optionPasajeros = JOptionPane.showInputDialog("""
                            1. Mostrar Pasajeros
                            2. Mostrar Pasajeros por nombre
                            3. Crear Pasajero
                            4. Editar Pasajero
                            5. Eliminar Pasajero
                            
                            Ingresa una opcion:
                            """);
                    switch (optionPasajeros){
                        case "1":
                            PasajeroController.getAllPasajeros();
                            break;
                        case "2":
                            PasajeroController.getPasajeroNombre();
                            break;
                        case "3":
                            PasajeroController.createPasajero();
                            break;
                        case "4":
                            PasajeroController.updatePasajero();
                            break;
                        case "5":
                            PasajeroController.deletePasajero();
                            break;
                    }
                    break;
                case "4":
                    optionReservaciones = JOptionPane.showInputDialog("""
                            1. Mostrar Reservas
                            2. Crear Reservas
                            3. Editar Reservas
                            4. Eliminar una Reserva
                            5. Mostrar todas las reservas de un vuelo
                            
                            Ingresa una opcion:
                            """);
                    switch (optionReservaciones){
                        case "1":
                            ReservacionController.getAllReservas();
                            break;
                        case "2":
                            ReservacionController.createReservas();
                            break;
                        case "3":
                            ReservacionController.updateReservacion();
                            break;
                        case "4":
                            ReservacionController.deleteReserva();
                            break;
                        case "5":
                            ReservacionController.getAllReservasVuelo();
                            break;
                    }
                    break;
            }
        }while (!optionMenu.equals("5"));
    }
}