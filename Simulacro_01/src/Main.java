import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option = "";
        String option1 = "";
        String option2 = "";
        String option3 = "";
        String option4 = "";

        do {
            option = JOptionPane.showInputDialog("""
                    MENU DE OPCIONES
                    
                    1. Menu de especialidades
                    2. Menu de medicos
                    3. Menu de pacientes
                    4. Menu de citas
                    5. Salir
                    
                    Elige una opción
                    """);

            switch (option){
                case "1":
                    do {
                        option1 = JOptionPane.showInputDialog("""
                                Menu de especialidades
                                
                                1. Añadir especialidad
                                2. Listar las especialidades existentes
                                3. Actualizar especialidad
                                4. Eliminar una especialidad
                                5. Salir
                                
                                Elige una opción
                                """);

                        switch (option1){
                            case "1":
                                EspecialidadController.create();
                                break;
                            case "2":
                                EspecialidadController.getAll();
                                break;
                            case "3":
                                EspecialidadController.update();
                                break;
                            case "4":
                                EspecialidadController.delete();
                                break;
                        }

                    }while (!option1.equals("5"));
                    break;

                case "2":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                Menu de medicos
                                
                                1. Añadir medico
                                2. Listar todos los medicos
                                3. Listar los medicos de una especialidad
                                4. Actualizar un medico
                                5. Eliminar un medico
                                6. Salir
                                
                                Elige una opción
                                """);

                        switch (option2){
                            case "1":
                                MedicoController.create();
                                break;
                            case "2":
                                MedicoController.getAll();
                                break;
                            case "3":
                                MedicoController.FindByEspecialidad();
                                break;
                            case "4":
                                MedicoController.update();
                                break;
                            case "5":
                                MedicoController.delete();
                                break;
                        }

                    }while (!option2.equals("6"));
                    break;

                case "3":
                    do {
                        option3 = JOptionPane.showInputDialog("""
                                Menu de pacientes
                                
                                1. Añadir paciente
                                2. Listar pacientes existentes
                                3. Buscar pacientes por documento de identidad
                                4. Actualizar un paciente
                                5. Eliminar paciente
                                6. Salir
                                
                                Elige una opción
                                """);

                        switch (option3){
                            case "1":
                                PacienteController.create();
                                break;
                            case "2":
                                PacienteController.getAll();
                                break;
                            case "3":
                                PacienteController.findByDocument();
                                break;
                            case "4":
                                PacienteController.update();
                                break;
                            case "5":
                                PacienteController.delete();
                                break;
                        }

                    }while (!option3.equals("6"));
                    break;

                case "4":
                    do {
                        option4 = JOptionPane.showInputDialog("""
                                Menu de citas
                                
                                1. Añadir citas
                                2. Listar las citas existentes
                                3. Buscar citas por fecha
                                4. Actulizar cita
                                5. Eliminar cita
                                6. Salir
                                
                                Elige una opción
                                """);

                        switch (option4){
                            case "1":
                                CitaController.create();
                                break;
                            case "2":
                                CitaController.getAll();
                                break;
                            case "3":
                                CitaController.FindCitasByFecha();
                                break;
                            case "4":
                                CitaController.update();
                                break;
                            case "5":
                                CitaController.delete();
                                break;
                        }

                    }while (!option4.equals("6"));
                    break;
            }

        }while (!option.equals("5"));
    }
}