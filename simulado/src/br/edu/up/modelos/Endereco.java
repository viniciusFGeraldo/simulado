package br.edu.up.modelos;

public class Endereco {
    private String rua;
    private String cidade;
    private int codigo;

    public Endereco(String rua, String cidade, int codigo) {
        this.rua = rua;
        this.cidade = cidade;
        this.codigo = codigo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Endereco [rua=" + rua + ", cidade=" + cidade + ", codigo=" + codigo + "]";
    }

    
}
