import java.util.ArrayList;

public class Item {
    //atributos
    private ArrayList<String> items;
    private ArrayList<String> efectos;
    private int cantidadItems;

    //construcotr
    public Item() {
        items = new ArrayList<>();
        efectos = new ArrayList<>();
        // inicializar items
        items.add("Pelota explosiva");
        efectos.add("DAÑO");

        items.add("Peluche con veneno");
        efectos.add("VENENO");


        items.add("Frisby curativo");
        efectos.add("CURAR");

        cantidadItems = items.size();
    }

    //getters y setters
    public ArrayList<String> getItems() {
        return items;
    }

    public ArrayList<String> getEfectos() {
        return efectos;
    }

    public int getCantidadItems() {
        return cantidadItems;
    }
}
