package org.example;

import org.example.Model.Campamento;
import org.example.Model.Paciente;
import org.example.Service.SistemaServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Campamento campamento1 = new Campamento(1);
        Campamento campamento2 = new Campamento(2);
        Campamento campamento3 = new Campamento(3);

        List<Campamento> campamentos = new ArrayList<>();

        campamentos.add(campamento1);
        campamentos.add(campamento2);
        campamentos.add(campamento3);

        Random random = new Random();

        for (int i = 1; i <= 25; i++) {

            int preferencia = random.nextInt(2);

            Paciente paciente = new Paciente(
                    "CC" + i,
                    preferencia,
                    "ABC"
            );

            campamento1.agregarPaciente(paciente);
        }

        SistemaServiceImpl sistema = new SistemaServiceImpl();

        sistema.ejecutar(campamentos);
    }
}
