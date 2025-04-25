package com.ifbaiano.estagioinclusivo.utils.validation;

public class ErroCampo {
    private String nomeCampo;
    private Object valorInserido;
    private String mensagemErro;



    public ErroCampo(String nomeCampo, Object valorInserido, String mensagemErro) {
        this.nomeCampo = nomeCampo;
        this.valorInserido = valorInserido;
        this.mensagemErro = mensagemErro;
    }

    public Object getValorInserido() {
        return valorInserido;
    }

    public void setValorInserido(Object valorInserido) {
        this.valorInserido = valorInserido;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public void setNomeCampo(String nomeCampo) {
        this.nomeCampo = nomeCampo;
    }


}
