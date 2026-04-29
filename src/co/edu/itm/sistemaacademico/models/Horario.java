package co.edu.itm.sistemaacademico.models;

public class Horario {
    private String dias;
    private int horaInicio;
    private int horaFin;

    public Horario(String dias, int horaInicio, int horaFin) {
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getDias() {
        return dias;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

}