package com.stressless.stressless.Users;

/**
 * Created by João Victor Firmino on 21/11/2017.
 */

public class ManuaisEnt {

    private String numeroBrinco2;
    private String temperaturaRetal2;
    private String frequenciaRespiratoria2;

    public ManuaisEnt() {
    }

    public String getNumeroBrinco2() {
        return numeroBrinco2;
    }

    public void setNumeroBrinco2(String numeroBrinco) {
        this.numeroBrinco2 = numeroBrinco;
    }

    public String getTemperaturaRetal2() {
        return temperaturaRetal2;
    }

    public void setTemperaturaRetal2(String temperaturaRetal2) {
        this.temperaturaRetal2 = temperaturaRetal2;
    }

    public String getFrequenciaRespiratoria2() {
        return frequenciaRespiratoria2;
    }

    public void setFrequenciaRespiratoria2(String frequenciaRespiratoria2) {
        this.frequenciaRespiratoria2 = frequenciaRespiratoria2;
    }

    @Override
    public String toString() {
        return "Número Brinco: " + numeroBrinco2 + "\n" +
                "Temperatura Retal: " + temperaturaRetal2 + "\n" +
                "Frequência Respiratoria: " + frequenciaRespiratoria2;
    }
}
