package br.edu.up;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.edu.up.modelos.Endereco;
import br.edu.up.modelos.PessoaComEndereco;

public class Programa {
    public static void main(String[] args) {
        GerenciadorDePessoa gerenciador = new GerenciadorDePessoa();
        gerenciador.combinarPessoasEEnderecos();
        gerenciador.gravarArquivo();
    }
}

class GerenciadorDePessoa {
    private String header = "codigo;nome;rua;cidade";
    private String nomeDoArquivoPessoas = "C:\\_ws\\segundo semestre\\vinicius_sofware\\desenv_software\\simulado\\simulado\\src\\br\\edu\\up\\daos\\Pessoas.csv";
    private String nomeDoArquivoEnderecos = "C:\\_ws\\segundo semestre\\vinicius_sofware\\desenv_software\\simulado\\simulado\\src\\br\\edu\\up\\daos\\Enderecos.csv";
    private String nomeDoArquivoPessoasComEndereco = "C:\\_ws\\segundo semestre\\vinicius_sofware\\desenv_software\\simulado\\simulado\\src\\br\\edu\\up\\daos\\PessoasComEndereco.csv";

    List<PessoaComEndereco> listaDePessoasComEnderecos = new ArrayList<>();

    public List<PessoaComEndereco> combinarPessoasEEnderecos() {
        Map<Integer, String> pessoas = new HashMap<>();
        List<Endereco> enderecos = new ArrayList<>();

        // Ler arquivo de pessoas
        try {
            File arquivoPessoas = new File(nomeDoArquivoPessoas);
            Scanner leitor = new Scanner(arquivoPessoas);
            if (leitor.hasNextLine()) {
                leitor.nextLine(); // Ignorar o cabeçalho
            }

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                Integer codigo = Integer.parseInt(dados[0]);
                String nome = dados[1];

                pessoas.put(codigo, nome);
            }
            leitor.close();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado (Pessoas)!");
        }

        // Ler arquivo de endereços
        try {
            File arquivoEnderecos = new File(nomeDoArquivoEnderecos);
            Scanner leitor = new Scanner(arquivoEnderecos);
            if (leitor.hasNextLine()) {
                leitor.nextLine(); // Ignorar o cabeçalho
            }

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                String rua = dados[0];
                String cidade = dados[1];
                Integer codigo = Integer.parseInt(dados[2]);

                Endereco endereco = new Endereco(rua, cidade, codigo);
                enderecos.add(endereco);
            }
            leitor.close();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado (Endereços)!");
        }

        // Combinar dados
        for (Endereco endereco : enderecos) {
            String nome = pessoas.get(endereco.getCodigo());
            if (nome != null) {
                PessoaComEndereco pessoaComEndereco = new PessoaComEndereco(endereco.getCodigo(), nome, endereco.getRua(), endereco.getCidade());
                listaDePessoasComEnderecos.add(pessoaComEndereco);
            }
        }

        return listaDePessoasComEnderecos;
    }

    public void gravarArquivo() {
        try {
            FileWriter arquivoGravar = new FileWriter(nomeDoArquivoPessoasComEndereco);
            PrintWriter gravador = new PrintWriter(arquivoGravar);
            gravador.println(header);

            for (PessoaComEndereco pessoaComEndereco : listaDePessoasComEnderecos) {
                gravador.println(pessoaComEndereco.toCSV());
            }
            gravador.close();
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o arquivo!");
        }
    }
}
