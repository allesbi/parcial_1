package org.example;

import org.example.Model.Campamento;
import org.example.Model.Paciente;
import org.example.Service.SistemaServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaServiceTest {

    @Test
    public void pacienteEmpiezaConTresIntentos() {

        Paciente paciente = new Paciente("123", 0, "ABC");

        assertEquals(3, paciente.getIntentosRestantes());
    }

    @Test
    public void pacientePuedeSanarse() {

        Paciente paciente = new Paciente("123", 0, "ABC");
        Campamento campamento = new Campamento(1);

        campamento.getRaciones().push(0);
        campamento.getMedicamentos().add("A");
        campamento.getMedicamentos().add("B");
        campamento.getMedicamentos().add("C");

        SistemaServiceImpl sistema = new SistemaServiceImpl();

        assertTrue(sistema.puedeSanar(paciente, campamento));
    }

    @Test
    public void pacienteNoPuedeSanarseSinMedicamentos() {

        Paciente paciente = new Paciente("123", 0, "ABC");
        Campamento campamento = new Campamento(1);

        campamento.getRaciones().push(0);
        campamento.getMedicamentos().add("A");
        campamento.getMedicamentos().add("B");

        SistemaServiceImpl sistema = new SistemaServiceImpl();

        assertFalse(sistema.puedeSanar(paciente, campamento));
    }

    @Test
    public void campamentoAgregaPaciente() {

        Campamento campamento = new Campamento(1);
        Paciente paciente = new Paciente("123", 0, "ABC");

        campamento.agregarPaciente(paciente);

        assertEquals(1, campamento.getPacientes().size());
    }

    @Test
    public void pacienteEsIgualPorCC() {

        Paciente paciente1 = new Paciente("123", 0, "ABC");
        Paciente paciente2 = new Paciente("123", 1, "BCC");

        assertEquals(paciente1, paciente2);
    }
}