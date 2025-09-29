import java.util.ArrayList;
import java.util.List;

public class Registro {
    //atributos
     private List<String> lista;
    private int capacidad;
    //constructores
    public Registro() {
        lista = new ArrayList<>();
        capacidad=3;
    }
    // métodos de lógica de registro
    public void registrar(String newaccion) {
        if (lista.size() == capacidad) {
            // elimina la más antigua
            lista.remove(0);
        }
        lista.add(newaccion);

    }

    public List<String> ultimasAcciones() {
        return lista;
    }
}
