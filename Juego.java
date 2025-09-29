import java.util.ArrayList;
import java.util.Random;

public class Juego{
    //atributos
    private Equipo equipoC;
    private Equipo equipoE;
    private Personaje turnoActual;
    private Registro registro;

    //constructor
    public Juego() {
        this.equipoC = new Equipo();
        this.equipoE = new Equipo();
        this.turnoActual = new Personaje();
        this.registro = new Registro();
    }

    //setters y getters
    public void setEquipoC(Equipo newequipoC) {
        this.equipoC = newequipoC;
    }

    public Equipo getEquipoC() {
        return equipoC;
    }

    public void setEquipoE(Equipo newequipoE) {
        this.equipoE = newequipoE;
    }

    public Equipo getEquipoE() {
        return equipoE;
    }


    public void setTurnoActual(Personaje newturnoActual) {
        this.turnoActual = newturnoActual;
    }

    public Personaje getTurnoActual() {
        return turnoActual;
    }

    public Registro getRegistro() {
        return registro;
    }

    //logica del juego
    public void jugarTurno() {
        registro.registrar(turnoActual.getNombre() + " realiza su acción.");
        // Seleccionar objetivo
        Personaje objetivo = seleccionarObjetivo(turnoActual);
        if (objetivo != null) {
            // Ejecutar acción sobre el objetivo
            ejecutarAccion(turnoActual, objetivo);
        }
        // Cambiar turno
        cambiarTurno();
        }

    public void cambiarTurno() {
        ArrayList<Personaje> equipoActual, equipoSiguiente;
        if (equipoC.getMiembros().contains(turnoActual)) {
            equipoActual = convertirEquipo(equipoC);
            equipoSiguiente = convertirEquipo(equipoE);
        } else {
            equipoActual = convertirEquipo(equipoE);
            equipoSiguiente = convertirEquipo(equipoC);
        }

        // Cambiar al siguiente miembro vivo del otro equipo
        if (!equipoSiguiente.isEmpty()) {
            turnoActual = equipoSiguiente.get(0);
        }
    }
    private ArrayList<Personaje> convertirEquipo(Equipo e) {
        ArrayList<Personaje> lista = new ArrayList<>();
        for (Object o : e.getMiembros()) {
            lista.add((Personaje) o);
        }
        return lista;
    }

    public boolean juegoTerminado() {
        return equipoC.getMiembros().isEmpty() || equipoE.getMiembros().isEmpty();
    }

    public Equipo determinarGanador() {
        if (equipoC.getMiembros().isEmpty()) {
            return equipoE;
        } else if (equipoE.getMiembros().isEmpty()) {
            return equipoC;
        } else {
            return null;
        }
    }

    public void ejecutarAccion(Personaje atacante, Personaje objetivo) {
        String accion = "";

        if (atacante instanceof Jugador) {
            Jugador jugador = (Jugador) atacante;
            if (jugador.getItem() != null) {
                jugador.usarItem(objetivo);
                accion = jugador.getNombre() + " usó un item sobre " + objetivo.getNombre();
            } else {
                int daño = atacante.getPoderAtaque();
                objetivo.setPuntosV(objetivo.getPuntosV() - daño);
                accion = atacante.getNombre() + " ataca a " + objetivo.getNombre() + " causando " + daño + " de daño";
            }
        } else {
            HabilidadE hab = atacante.getHabilidad();
            if (hab == null) hab = new HabilidadE();
            String tipo = hab.habilidadAleatoria();
            int daño = hab.usarHabilidad(tipo, atacante, objetivo);
            accion = atacante.getNombre() + " usa " + tipo + " sobre " + objetivo.getNombre() + " causando " + daño + " de daño";
        }

        // Registrar acción
        registro.registrar(accion);

        if (objetivo.getPuntosV() <= 0) {
            eliminarPersonaje(objetivo);
            registro.registrar(objetivo.getNombre() + " ha sido derrotado!");
        }
    }



    private void eliminarPersonaje(Personaje p) {
        if (equipoC.getMiembros().contains(p)) {
            equipoC.getMiembros().remove(p);
        } else if (equipoE.getMiembros().contains(p)) {
            equipoE.getMiembros().remove(p);
        }
    }

    public Personaje seleccionarObjetivo(Personaje atacante) {
        ArrayList<Personaje> enemigos;
        if (equipoC.getMiembros().contains(atacante)) {
            enemigos = convertirEquipo(equipoE);
        } else {
            enemigos = convertirEquipo(equipoC);
        }

        if (enemigos.isEmpty()) return null;

        Random rand = new Random();
        return enemigos.get(rand.nextInt(enemigos.size()));
    }
    public String mostrarEstado() {
        String cadena="=== Estado de los Equipos ===\n"+equipoC+"\n"+equipoE+"\n"+"Últimas acciones:";
        for (String a : registro.ultimasAcciones()) {
            cadena+=a+"\n";
        }
        cadena+="==============================\n";
        return cadena;
    }
}