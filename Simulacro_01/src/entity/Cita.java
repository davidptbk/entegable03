package entity;

public class Cita {
    private int id;
    private String fechaCita;
    private String horaCita;
    private String motivo;
    private int idPaciente;
    private int idMedico;
    private Paciente objPaciente;
    private Medico objMedico;

    public Cita() {
    }

    public Cita(int id, String fechaCita, String horaCita, String motivo, int idPaciente, int idMedico) {
        this.id = id;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.motivo = motivo;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public Paciente getObjPaciente() {
        return objPaciente;
    }

    public void setObjPaciente(Paciente objPaciente) {
        this.objPaciente = objPaciente;
    }

    public Medico getObjMedico() {
        return objMedico;
    }

    public void setObjMedico(Medico objMedico) {
        this.objMedico = objMedico;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", fechaCita='" + fechaCita + '\'' +
                ", horaCita='" + horaCita + '\'' +
                ", motivo='" + motivo + '\'' +
                ", Medico =" + objPaciente.getNombre() + " " +objPaciente.getApellidos()+
                ", Paciente =" + objMedico.getNombre() +" "+ objMedico.getApellidos() +
                '}';
    }
}
