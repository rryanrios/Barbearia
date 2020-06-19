package com.projeto7semestre.barbearia.funcionario;


import androidx.annotation.NonNull;

import java.io.Serializable;

public class Funcionario implements Serializable {
    private int id;
    private String nome;
    private String horario;
    private String tempoServico;
    private String servicoEscolhido;
    private String preco;
    private String dia;
    private int checkbox;

    @NonNull
    @Override
    public String toString() {
        return "Profissional: " + this.nome + "\nServico: " + this.servicoEscolhido + "\nValor: R$ " + this.preco + "\nHorario marcado: " + this.horario + "\nTempo: " + this.tempoServico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        if (id == 1){
            setNome("Ricardo Roque");
        } else if (id == 2){
            setNome("Rian Rios");
        } else if (id == 3){
            setNome("Guilherme");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTempoServico() {
        return tempoServico;
    }

    public void setTempoServico(String tempoServico) {
        this.tempoServico = tempoServico;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getServicoEscolhido() {
        return servicoEscolhido;
    }

    public void setServicoEscolhido(String servicoEscolhido) {
        this.servicoEscolhido = servicoEscolhido;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(int checkbox) {
        this.checkbox = checkbox;
    }
}
