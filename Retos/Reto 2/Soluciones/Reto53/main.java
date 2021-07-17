/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto53;

import java.util.Scanner;

/**
 *
 * @author acer nitro5
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int num;
        CuerpoDeAgua[] datos = new CuerpoDeAgua[100];
        Scanner sc = new Scanner(System.in);
        num = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < num; i++) {
            String[] lines = sc.nextLine().split(" ");
            datos[i] = new CuerpoDeAgua(lines[0], Double.parseDouble(lines[1]),
                    lines[2], Double.parseDouble(lines[3]));
        }

        //Nombre de cada cuerpo de agua        
        String nombre;
        for (int i = 0; i < num; i++) {
            nombre = datos[i].getNombre();
            System.out.println(nombre);
        }

        //Numero de cuerpos de agua con nivel de riesgo etre BAJO y MEDIO     
        double cont = 0;
        for (int i = 0; i < num; i++) {
            if (datos[i].getIrca() > 5 && datos[i].getIrca() <= 35) {
                cont++;
            }

        }
        System.out.println(String.format("%.2f", cont));

        //Nombre de cuerpos de agua con nivel de riesgo BAJO
        double contm = 0;
        for (int i = 0; i < num; i++) {
            if (datos[i].getIrca() > 5 && datos[i].getIrca() <= 14) {
                System.out.print(datos[i].getNombre() + " ");
                contm++;
            }
        }
        if (contm == 0) {
            System.out.print("NA");
        }

        //irca promedio
        double promedio;
        double suma = 0;
        for (int i = 0; i < num; i++) {
            suma += datos[i].getIrca();
        }
        promedio = suma / num;
        System.out.println("\n" + String.format("%.2f", promedio));

    }

}
