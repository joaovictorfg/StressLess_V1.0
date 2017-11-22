package com.stressless.stressless.Users;

/**
 * Created by João Victor Firmino on 21/11/2017.
 */

public class SensoresEnt {

    private String numeroBrinco;
    private String temperaturaRetal;
    private String frequenciaRespiratoria;

    public SensoresEnt() {
    }

    public String getNumeroBrinco() {
        return numeroBrinco;
    }

    public void setNumeroBrinco(String numeroBrinco) {
        this.numeroBrinco = numeroBrinco;
    }

    public String getTemperaturaRetal() {
        return temperaturaRetal;
    }

    public void setTemperaturaRetal(String temperaturaRetal) {
        this.temperaturaRetal = temperaturaRetal;
    }

    public String getFrequenciaRespiratoria() {
        return frequenciaRespiratoria;
    }

    public void setFrequenciaRespiratoria(String frequenciaRespiratoria) {
        this.frequenciaRespiratoria = frequenciaRespiratoria;
    }

    @Override
    public String toString() {
        return "Número Brinco: " + numeroBrinco + "\n" +
                "Temperatura Retal: " + temperaturaRetal + "\n" +
                "Frequência Respiratoria: " + frequenciaRespiratoria;
    }
}
