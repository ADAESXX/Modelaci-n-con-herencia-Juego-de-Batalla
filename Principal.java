/**
 * @author Allyson Escobar
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.Random;

public class Principal {

    private JFrame frame;
    private JTextArea textAreaEstado;
    private JButton btnAccion;
    private Juego juego;
    private ArrayList<Jugador> jugadores;
    private int turnoIndice; // Índice del turno dentro del equipo

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Principal window = new Principal();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Principal() {
        juego = new Juego();
        jugadores = new ArrayList<>();
        turnoIndice = 0;

        crearEquipoJugadores();
        crearEnemigos();
        juego.setTurnoActual(jugadores.get(turnoIndice));
        initialize();
        actualizarEstado();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 750, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        textAreaEstado = new JTextArea();
        textAreaEstado.setEditable(false);
        textAreaEstado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textAreaEstado);
        scrollPane.setBorder(new TitledBorder("Estado del Juego"));
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);

        btnAccion = new JButton("Realizar Acción");
        btnAccion.addActionListener(e -> turnoActual());
        panelBotones.add(btnAccion);
    }

    // Crear equipo de jugadores
    private void crearEquipoJugadores() {
        String[] perros = {"🐕 Firulais", "🐕 Golden Retriever", "🐕 French", "🐕 Yorkshire",
                           "🐕 Pomerania", "🐕 Pastor Alemán", "🐕 Grandanes"};
        int numJugadores = 0;
        while (numJugadores < 1 || numJugadores > 4) {
            String input = JOptionPane.showInputDialog("¿Cuántos jugadores habrá en tu equipo? (1-4)");
            try {
                numJugadores = Integer.parseInt(input);
            } catch (Exception e) { numJugadores = 0; }
        }

        for (int i = 0; i < numJugadores; i++) {
            int seleccion = JOptionPane.showOptionDialog(null, "Jugador " + (i+1) + ": Elige tu perro",
                    "Elección de Perro", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, perros, perros[0]);

            if (seleccion < 0) seleccion = 0;
            String nombre = perros[seleccion];
            jugadores.add(new Jugador(100, new HabilidadE(), nombre, "Arañazo", 15));
        }

        Equipo equipoJugador = new Equipo();
        for (Jugador j : jugadores) {
            equipoJugador.setMiembros(j);
        }
        juego.setEquipoC(equipoJugador);
    }

    // Crear equipo enemigo
    private void crearEnemigos() {
        Enemigo e1 = new Enemigo(80, new HabilidadE(), "🐱 Gato Siamés", "Arañazo", 10);
        Enemigo e2 = new Enemigo(120, new HabilidadE(), "🐍 Yarará", "Devorar", 25);
        Equipo equipoEnemigo = new Equipo();
        equipoEnemigo.setMiembros(e1);
        equipoEnemigo.setMiembros(e2);
        juego.setEquipoE(equipoEnemigo);
    }

    // Turno actual
    private void turnoActual() {
        Personaje turno = juego.getTurnoActual();

        if (turno instanceof Jugador) {
            Jugador j = (Jugador) turno;
            String[] opciones = {"🐾 Ataque", "🎁 Usar Item"};
            int seleccion = JOptionPane.showOptionDialog(frame, "Turno de " + j.getNombre(), 
                    "Acción", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            Personaje objetivo = juego.seleccionarObjetivo(turno);
            if (seleccion == 1) {
                // Usar item
                if (objetivo != null) {
                    j.setItem(true, new Random().nextInt(3)); // Elegir un item al azar
                    juego.ejecutarAccion(j, objetivo);
                }
            } else {
                // Ataque normal
                if (objetivo != null) {
                    juego.ejecutarAccion(j, objetivo);
                }
            }
        } else if (turno instanceof Enemigo) {
            // Turno enemigo automático
            Personaje objetivo = juego.seleccionarObjetivo(turno);
            if (objetivo != null) {
                juego.ejecutarAccion(turno, objetivo);
            }
        }

        juego.jugarTurno();
        actualizarEstado();
        revisarFinJuego();
        actualizarTurnoIndicador();
    }

    private void actualizarEstado() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Equipos ===\n");
        for (Personaje p : juego.getEquipoC().getMiembros()) {
            sb.append("👑 ").append(p.getNombre()).append(" PV: ").append(p.getPuntosV()).append("\n");
        }
        for (Personaje e : juego.getEquipoE().getMiembros()) {
            sb.append("💀 ").append(e.getNombre()).append(" PV: ").append(e.getPuntosV()).append("\n");
        }
        sb.append("\nÚltimas acciones:\n");
        for (String a : juego.getRegistro().ultimasAcciones()) {
            sb.append(a).append("\n");
        }
        sb.append("===================\n");
        textAreaEstado.setText(sb.toString());
    }

    private void actualizarTurnoIndicador() {
        Personaje turno = juego.getTurnoActual();
        String mensaje;
        if (turno instanceof Jugador) {
            mensaje = "➡️ Turno de jugador: " + turno.getNombre();
        } else {
            mensaje = "➡️ Turno del enemigo: " + turno.getNombre();
        }
        textAreaEstado.append("\n" + mensaje + "\n");
    }

    private void revisarFinJuego() {
        if (juego.juegoTerminado()) {
            Equipo ganador = juego.determinarGanador();
            JOptionPane.showMessageDialog(frame, "🎉 ¡El juego terminó! Ganador: " + 
                    (ganador != null ? "Equipo " + ganador : "Empate"));
            btnAccion.setEnabled(false);
        }
    }
}
