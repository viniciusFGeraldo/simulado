package br.edu.up.modelos;

public class Pessoa {
    private Integer codigo;
    private String nome;
    
    public Pessoa(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    

    public Integer getCodigo() {
        return codigo;
    }


    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    

    @Override
    public String toString() {
        return "Pessoa [codigo=" + codigo + ", nome=" + nome + "]";
    }


    public String toCSV() {
        return codigo + ";" + nome;
    }

}
