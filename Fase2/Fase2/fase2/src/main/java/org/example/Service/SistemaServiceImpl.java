package org.example.Service;

import org.apache.commons.collections4.Bag;
import org.example.Interface.SistemaService;
import org.example.Model.Campamento;
import org.example.Model.Paciente;

import java.util.List;
import java.util.Random;

public class SistemaServiceImpl implements SistemaService {

    private int sanados;
    private int muertos;
    private Random random;

    public SistemaServiceImpl() {
        sanados = 0;
        muertos = 0;
        random = new Random();
    }

    public void aprovisionar(Campamento campamento) {

        for (int i = 0; i < 15; i++) {
            campamento.getRaciones().push(random.nextInt(2));
        }

        for (int i = 0; i < 30; i++) {
            int numero = random.nextInt(3);

            if (numero == 0) {
                campamento.getMedicamentos().add("A");
            } else if (numero == 1) {
                campamento.getMedicamentos().add("B");
            } else {
                campamento.getMedicamentos().add("C");
            }
        }
    }

    @Override
    public boolean puedeSanar(Paciente paciente, Campamento campamento) {

        if (campamento.getRaciones().isEmpty()) {
            return false;
        }

        if (campamento.getRaciones().peek() != paciente.getPreferenciaRacion()) {
            return false;
        }

        Bag<String> medicamentos = campamento.getMedicamentos();

        int cantidadA = 0;
        int cantidadB = 0;
        int cantidadC = 0;

        for (int i = 0; i < paciente.getRecetaMedica().length(); i++) {

            String medicamento = String.valueOf(paciente.getRecetaMedica().charAt(i));

            if (medicamento.equals("A")) {
                cantidadA++;
            } else if (medicamento.equals("B")) {
                cantidadB++;
            } else if (medicamento.equals("C")) {
                cantidadC++;
            }
        }

        if (medicamentos.getCount("A") < cantidadA) {
            return false;
        }

        if (medicamentos.getCount("B") < cantidadB) {
            return false;
        }

        if (medicamentos.getCount("C") < cantidadC) {
            return false;
        }

        return true;
    }

    private boolean tieneMedicamentos(Paciente paciente, Campamento campamento) {

        Bag<String> medicamentos = campamento.getMedicamentos();

        int cantidadA = 0;
        int cantidadB = 0;
        int cantidadC = 0;

        for (int i = 0; i < paciente.getRecetaMedica().length(); i++) {

            String medicamento = String.valueOf(paciente.getRecetaMedica().charAt(i));

            if (medicamento.equals("A")) {
                cantidadA++;
            } else if (medicamento.equals("B")) {
                cantidadB++;
            } else if (medicamento.equals("C")) {
                cantidadC++;
            }
        }

        return medicamentos.getCount("A") >= cantidadA
                && medicamentos.getCount("B") >= cantidadB
                && medicamentos.getCount("C") >= cantidadC;
    }

    private void sacarMedicamentos(Paciente paciente, Campamento campamento) {

        for (int i = 0; i < paciente.getRecetaMedica().length(); i++) {

            String medicamento = String.valueOf(paciente.getRecetaMedica().charAt(i));

            campamento.getMedicamentos().remove(medicamento, 1);
        }
    }

    private void trasladar(Paciente paciente, Campamento siguiente) {

        paciente.perderIntento();

        if (paciente.getIntentosRestantes() <= 0) {
            paciente.setEstado("MUERTO");
            muertos++;
            System.out.println("Paciente " + paciente.getCC() + " murio");
        } else {
            paciente.setEstado("TRASLADADO");
            siguiente.agregarPaciente(paciente);

            System.out.println("Paciente " + paciente.getCC()
                    + " trasladado al Campamento " + siguiente.getId()
                    + " | Intentos: " + paciente.getIntentosRestantes());
        }
    }

    private void bloquearCampamento(Campamento actual, Campamento siguiente) {

        while (!actual.getPacientes().isEmpty()) {

            Paciente paciente = actual.getPacientes().poll();

            System.out.println("Bloqueo en Campamento " + actual.getId());

            trasladar(paciente, siguiente);
        }
    }

    private void atenderCampamento(Campamento actual, Campamento siguiente) {

        while (!actual.getPacientes().isEmpty()) {

            int cantidad = actual.getPacientes().size();
            boolean huboExito = false;
            boolean huboTraslado = false;

            for (int i = 0; i < cantidad && !actual.getPacientes().isEmpty(); i++) {

                Paciente paciente = actual.getPacientes().poll();

                System.out.println("Campamento " + actual.getId()
                        + " atiende paciente " + paciente.getCC());

                if (!tieneMedicamentos(paciente, actual)) {

                    System.out.println("Escasez de medicamentos");

                    trasladar(paciente, siguiente);
                    huboTraslado = true;

                } else if (puedeSanar(paciente, actual)) {

                    actual.getRaciones().pop();
                    sacarMedicamentos(paciente, actual);

                    paciente.setEstado("SANADO");
                    sanados++;
                    huboExito = true;

                    System.out.println("Paciente " + paciente.getCC() + " SANADO");

                } else {

                    actual.getPacientes().offer(paciente);

                    System.out.println("Paciente " + paciente.getCC()
                            + " espera y vuelve al final de la cola");
                }
            }

            if (!huboExito && !huboTraslado && !actual.getPacientes().isEmpty()) {
                bloquearCampamento(actual, siguiente);
            }
        }
    }

    @Override
    public void ejecutar(List<Campamento> campamentos) {

        for (Campamento campamento : campamentos) {
            aprovisionar(campamento);
        }

        System.out.println("ESTADO INICIAL");

        for (Campamento campamento : campamentos) {
            System.out.println(campamento);
        }

        while (!campamentos.get(0).getPacientes().isEmpty()
                || !campamentos.get(1).getPacientes().isEmpty()
                || !campamentos.get(2).getPacientes().isEmpty()) {

            atenderCampamento(campamentos.get(0), campamentos.get(1));
            atenderCampamento(campamentos.get(1), campamentos.get(2));
            atenderCampamento(campamentos.get(2), campamentos.get(0));
        }

        System.out.println();
        System.out.println("RESULTADOS FINALES");
        System.out.println("Pacientes sanados: " + sanados);
        System.out.println("Pacientes muertos: " + muertos);

        for (Campamento campamento : campamentos) {
            System.out.println("Estado final Campamento "
                    + campamento.getId() + ": " + campamento);
        }
    }

    @Override
    public int getSanados() {
        return sanados;
    }

    @Override
    public int getMuertos() {
        return muertos;
    }
}