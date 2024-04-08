package entity;

public class Medico {
    private int id;
    private String nombre;
    private String apellidos;
    private int idEspecialidad;
    private Especialidad objEspecialidad;

    public Medico() {
    }

    public Medico(String nombre, String apellidos, int idEspecialidad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.idEspecialidad = idEspecialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Especialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(Especialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

    @Override
    public String toString() {
        return "Medico{" +
                ", Nombre:" + nombre + '\'' +
                ", Apellidos:" + apellidos + '\'' +
                ", Especialidad:" + objEspecialidad.getNombre() +
                '}';
    }
}
