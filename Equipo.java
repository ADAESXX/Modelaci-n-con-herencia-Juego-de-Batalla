import java.util.ArrayList;

public class Equipo {
    //atributos
    private ArrayList<Personaje> miembros;
    //constructor
    public Equipo() {
        miembros = new ArrayList<>();
    }
    //getters y setters
    public void setMiembros(Personaje newmiembros) {
        miembros.add(newmiembros);
    }

    public ArrayList<Personaje> getMiembros() {
        return miembros;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        //ayuda de ia para hacer este toString
        StringBuilder sb = new StringBuilder("Equipo:\n");
        for (Personaje p : miembros) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}
