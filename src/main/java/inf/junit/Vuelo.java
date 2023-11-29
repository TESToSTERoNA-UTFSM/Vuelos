package inf.junit;

public class Vuelo {
	String origen;
    String destino;
    String horaSalida;
    int duracion;
    int asientosDisponibles;

    public Vuelo(String origen, String destino, String horaSalida, int duracion, int asientosDisponibles) {
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.duracion = duracion;
        this.asientosDisponibles = asientosDisponibles;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }
}
