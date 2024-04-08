package entity;

public class Vuelo {
    private  int id;
    private  String destino;
    private String fecha_salida;
    private String hora_salida;
    private int id_avion;

    public Vuelo() {
    }

    public Vuelo(int id, String destino, String fecha_salida, String hora_salida, int id_avion) {
        this.id = id;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.id_avion = id_avion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id=" + id +
                ", destino='" + destino + '\'' +
                ", fecha_salida='" + fecha_salida + '\'' +
                ", hora_salida='" + hora_salida + '\'' +
                ", id_avion=" + id_avion +
                '}';
    }
}
