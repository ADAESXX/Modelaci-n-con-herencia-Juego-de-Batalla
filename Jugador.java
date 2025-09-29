import java.util.ArrayList;

public class Jugador extends Personaje {
    //atributos
    private boolean item;
    private int seleccionItem;
    //contructores
    public Jugador(){
        super();
        item=false;
    }

    public Jugador(int puntosV, HabilidadE habilidad, String nombre, String tipoAtaque, int poderAtaque) {
        super(puntosV, habilidad, nombre,tipoAtaque, poderAtaque);
        item=false;
    }

    //getters y setters
    public void setItem(boolean newitem, int opcion) {
        item=true;
        seleccionItem=opcion;
        
    }

    public String getItem() {
        if (item){
            Item items=new Item();
            ArrayList<String> lista=items.getItems();
            String itemausar= lista.get(seleccionItem);
            return itemausar;
        } 
        else {
            return null;
        }
    }
    public void usarItem(Personaje objetivo) {
        if (!item) return;

        Item itemObj = new Item();
        String tipoEfecto = itemObj.getEfectos().get(seleccionItem);
        int poder = 20 + this.getPoderAtaque(); // base de poder

        switch(tipoEfecto) {
            case "DAÑO":
                objetivo.setPuntosV(objetivo.getPuntosV() - poder);
                break;
            case "VENENO":
                objetivo.setPuntosV(objetivo.getPuntosV() - poder);
                // Podrías implementar daño por turno adicional
                break;
            case "CURAR":
                this.setPuntosV(this.getPuntosV() + poder);
                break;
        }

        
        // Reiniciar item
        item = false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Jugador: " + super.toString();
    }
}
