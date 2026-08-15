package org.example.Model;

import java.util.Objects;

public class Paciente {

    private String CC;
    private int preferenciaRacion;
    private String recetaMedica;
    private int intentosRestantes;
    private String estado;

    public Paciente(String CC, int preferenciaRacion, String recetaMedica) {
        this.CC = CC;
        this.preferenciaRacion = preferenciaRacion;
        this.recetaMedica = recetaMedica;
        this.intentosRestantes = 3;
        this.estado = "EN ESPERA";
    }

    public String getCC() {
        return CC;
    }

    public int getPreferenciaRacion() {
        return preferenciaRacion;
    }

    public String getRecetaMedica() {
        return recetaMedica;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public String getEstado() {
        return estado;
    }

    public void perderIntento() {
        intentosRestantes--;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "CC='" + CC + '\'' +
                ", preferenciaRacion=" + preferenciaRacion +
                ", recetaMedica='" + recetaMedica + '\'' +
                ", intentosRestantes=" + intentosRestantes +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(CC, paciente.CC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CC);
    }
}