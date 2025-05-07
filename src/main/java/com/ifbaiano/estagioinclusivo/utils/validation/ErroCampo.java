package com.ifbaiano.estagioinclusivo.utils.validation;

public class ErroCampo {
    private final String classe;
    private final String nomeCampo;
    private final Object valorInserido;
    private final String mensagemErro;



    public ErroCampo(String nomeCampo, Object valorInserido, String mensagemErro, String classe) {
        this.nomeCampo = nomeCampo;
        this.valorInserido = valorInserido;
        this.mensagemErro = mensagemErro;
        this.classe = classe;
    }

    public Object getValorInserido() {
        return valorInserido;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }


    public String getNomeCampo() {
        return nomeCampo;
    }

    public String getClasse() {
        return classe;
    }

    @Override
    public String toString() {
        return "ErroCampo{" +
                "nomeCampo='" + nomeCampo + '\'' +
                ", valorInserido=" + valorInserido +
                ", mensagemErro='" + mensagemErro + '\'' +
                '}';
    }
}
