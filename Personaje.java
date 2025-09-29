public class Personaje {
    private int puntosV;
    private HabilidadE habilidad;
    private String nombre;
    private String tipoAtaque;
    private int poderAtaque;

    //constructores
    public Personaje(){
        this.puntosV = 100;
        this.habilidad = (habilidad != null) ? habilidad : new HabilidadE();
        this.nombre = "";
        this.tipoAtaque = "";
        this.poderAtaque = 0;
    }
    public Personaje(int puntosV, HabilidadE habilidad, String nombre, String tipoAtaque, int poderAtaque) {
        this.puntosV = puntosV;
        this.habilidad = habilidad;
        this.nombre = nombre;
        this.tipoAtaque = tipoAtaque;
        this.poderAtaque = poderAtaque;
    }

    public void setPuntosV(int newpuntosV) {
        this.puntosV = newpuntosV;
    }

    //setters y getters
    public int getPuntosV() {
        return puntosV;
    }

    public void setHabilidad(HabilidadE newhabilidad) {
        this.habilidad = newhabilidad;
    }

    public HabilidadE getHabilidad() {
        return habilidad;
    }

    public void setNombre(String newnombre) {
        this.nombre = newnombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTipoAtaque(String newtipoAtaque) {
        this.tipoAtaque = newtipoAtaque;
    }

    public String getTipoAtaque() {
        return tipoAtaque;
    }

    public void setPoderAtaque(int newpoderAtaque) {
        this.poderAtaque = newpoderAtaque;
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Nombre= " + nombre + " | PV=" + puntosV + " | ATQ=" + poderAtaque;
    }
    
}
