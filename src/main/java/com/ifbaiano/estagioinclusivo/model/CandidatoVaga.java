package com.ifbaiano.estagioinclusivo.model;

import java.time.LocalDateTime;

public class CandidatoVaga {
    private Candidato candidato;
    private Vaga vaga;
    private LocalDateTime data;

    public CandidatoVaga(Candidato candidato, Vaga vaga) {
        this.candidato = candidato;
        this.vaga = vaga;
        this.data = LocalDateTime.now();
    }

    public CandidatoVaga(Candidato candidato, Vaga vaga, LocalDateTime data) {
        this.candidato = candidato;
        this.vaga = vaga;
        this.data = data;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
