package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Queue<Integer> refugiados = new LinkedList<>();
        Stack<Integer> Raciones = new Stack<>();


        refugiados.add(1);
        refugiados.add(1);
        refugiados.add(1);
        refugiados.add(0);
        refugiados.add(0);
        refugiados.add(1);

        Raciones.add(1);
        Raciones.add(0);
        Raciones.add(0);
        Raciones.add(0);
        Raciones.add(1);
        Raciones.add(1);


        SistemaRefugiados(refugiados,Raciones);

            }

            public static void SistemaRefugiados(Queue refugiados, Stack Raciones) {


                int Comieron = 0;
                int i = 0;
                int s = 0;


                while (!refugiados.isEmpty() && !Raciones.isEmpty()) {

                    if (refugiados.element() == Raciones.peek()) {
                        refugiados.remove();
                        Raciones.pop();
                        Comieron++;
                        i--;
                        s++;

                    } else {
                        refugiados.offer(refugiados.element());
                        refugiados.poll();
                        i++;
                    }

                    if (i >= refugiados.size() - s) {
                        System.out.printf("Ya no queda raciones que le gusten a los refugiados\n");
                        break;
                    }

                    System.out.println("Personas esperando su turno: " + refugiados);
                    System.out.println("Raciones aun disponibles " + Raciones);

                }
                    int noComieron = refugiados.size();
                    System.out.println("Personas que comieron: " + Comieron + "\nPersonas que se quedaron sin comer: " + noComieron);
            }
    }
