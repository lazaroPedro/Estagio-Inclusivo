package com.ifbaiano.estagioinclusivo.model;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;

import java.time.LocalDateTime;

public class CandidatoVaga {
    @NotNull
    private Candidato candidato;
    @NotNull
    private Vaga vaga;
    @NotNull
    private LocalDateTime data;




    public CandidatoVaga() {
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
