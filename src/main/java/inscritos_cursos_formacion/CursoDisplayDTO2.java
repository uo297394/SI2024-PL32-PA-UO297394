package inscritos_cursos_formacion;

public class CursoDisplayDTO2 {
    private int id;
    private String titulo_curso;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo_curso() {
        return titulo_curso;
    }
    public void setTitulo_curso(String titulo_curso) {
        this.titulo_curso = titulo_curso;
    }
    
    @Override
    public String toString() {
        return titulo_curso;  // Esto se mostrará en el combo
    }
}