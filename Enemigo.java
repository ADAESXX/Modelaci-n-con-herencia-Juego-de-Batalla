import java.util.ArrayList;

public class Enemigo extends Personaje{
    //atributos
    private ArrayList<String> jefes;
    private ArrayList<String> normal;
    //constructores
    public Enemigo(){
        super();
        jefes = new ArrayList<>();
        normal = new ArrayList<>();
        //se agregan los enemigos, segun a lo que corresponden
        normal.add("Gato Siamés");
        normal.add("Víbora de Pastizal");

        jefes.add("Gato Sphynx");
        jefes.add("Yarará");
    }
    public Enemigo(int puntosV, HabilidadE habilidad, String nombre, String tipoAtaque, int poderAtaque) {
        super(puntosV, habilidad, nombre,tipoAtaque, poderAtaque);
        jefes = new ArrayList<>();
        normal = new ArrayList<>();
        //se agregan los enemigos, segun a lo que corresponden
        normal.add("Gato Siamés");
        normal.add("Víbora de Pastizal");

        jefes.add("Gato Sphynx");
        jefes.add("Yarará");
    }
    //getters y setters

    public String getTipo() {
        if (jefes.contains(getNombre())) {
            return "Jefe";
        } else if (normal.contains(getNombre())) {
            return "Normal";
        }
        return "Desconocido";
    }

    @Override
    public String toString() {
        return "Enemigo: " + super.toString();
    }
}
