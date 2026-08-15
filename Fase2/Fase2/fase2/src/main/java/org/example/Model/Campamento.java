package org.example.Model;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

public class Campamento {

    private int id;
    private Queue<Paciente> pacientes;
    private Stack<Integer> raciones;
    private Bag<String> medicamentos;

    public Campamento(int id) {
        this.id = id;
        this.pacientes = new LinkedList<>();
        this.raciones = new Stack<>();
        this.medicamentos = new HashBag<>();
    }

    public int getId() {
        return id;
    }

    public Queue<Paciente> getPacientes() {
        return pacientes;
    }

    public Stack<Integer> getRaciones() {
        return raciones;
    }

    public Bag<String> getMedicamentos() {
        return medicamentos;
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.offer(paciente);
    }

    @Override
    public String toString() {
        return "Campamento{" +
                "id=" + id +
                ", pacientes=" + pacientes.size() +
                ", raciones=" + raciones +
                ", medicamentos=" + medicamentos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campamento that = (Campamento) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}