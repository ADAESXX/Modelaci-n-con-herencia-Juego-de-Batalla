
import java.util.ArrayList;
import java.util.Random;

public class HabilidadE {
    //attributos
    private ArrayList<String> tiposHabilidad;
    private ArrayList<Integer> poderHabilidad;

    //constructor
    public HabilidadE() {
        tiposHabilidad = new ArrayList<>();
        poderHabilidad = new ArrayList<>();

        //inicializar habilidades
        tiposHabilidad.add("Curar");
        poderHabilidad.add(15);

        tiposHabilidad.add("Mordida Venenosa");
        poderHabilidad.add(25);

        tiposHabilidad.add("Arañazo");
        poderHabilidad.add(20);

        tiposHabilidad.add("Devorar");
        poderHabilidad.add(30);
    }

    public int usarHabilidad(String tipo, Personaje atacante, Personaje objetivo) {
        switch(tipo) {
            case "Curar":
                atacante.setPuntosV(atacante.getPuntosV() + 15);
                return 0;
            case "Mordida Venenosa":
                objetivo.setPuntosV(objetivo.getPuntosV() - 25);
                return 25;
            case "Arañazo":
                objetivo.setPuntosV(objetivo.getPuntosV() - 20);
                return 20;
            case "Devorar":
                objetivo.setPuntosV(objetivo.getPuntosV() - 30);
                return 30;
            default:
                return 0;
        }
    }
    public String habilidadAleatoria() {
        Random rand = new Random();
        int i = rand.nextInt(tiposHabilidad.size());
        return tiposHabilidad.get(i);
    }
}
