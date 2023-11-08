public class mon {
    private String nombre; 
    private Integer poder;
    private String atributo;
    private Integer nivel;

    public mon(String nombre, Integer nivel, String atributo, Integer poder) {
        this.nombre = nombre;
        this.poder = poder;
        this.atributo = atributo;
        this.nivel = nivel;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    public Integer getPoder(){
        return this.poder;
    }
    public String getAtributo(){
        return this.atributo;
    }
    public Integer getNivel(){
        return this.nivel;
    }
}
