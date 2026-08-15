package org.example.Interface;

import org.example.Model.Campamento;
import org.example.Model.Paciente;

import java.util.List;

public interface SistemaService {

    void ejecutar(List<Campamento> campamentos);

    boolean puedeSanar(Paciente paciente, Campamento campamento);

    int getSanados();

    int getMuertos();
}