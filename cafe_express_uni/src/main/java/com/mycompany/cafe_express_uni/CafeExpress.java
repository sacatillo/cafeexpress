/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cafe_express_uni;

/**
 *
 * @author Martin Montes
 */

import java.util.Scanner;

class Cafe {
    private String tamaño;
    private String agregado;
    private double precio;
    private double costoAgregado;

    // Precios de los cafés,AMMM
    private static final double PRECIO_PEQUEÑO = 45.0;
    private static final double PRECIO_MEDIANO = 56.0;
    private static final double PRECIO_GRANDE = 65.0;

    // Costos de los agregados
    private static final double COSTO_LECHE = 5.0;
    private static final double COSTO_CREMORA = 8.0;
    private static final double COSTO_SENCILLO = 0.0;

    public Cafe(String tamaño, String agregado) {
        this.tamaño = tamaño;
        this.agregado = agregado;
        this.precio = calcularPrecio();
        this.costoAgregado = calcularCostoAgregado();
    }

    private double calcularPrecio() {
        switch (tamaño.toLowerCase()) {
            case "pequeño":
                return PRECIO_PEQUEÑO;
            case "mediano":
                return PRECIO_MEDIANO;
            case "grande":
                return PRECIO_GRANDE;
            default:
                return 0.0;
        }
    }

    private double calcularCostoAgregado() {
        switch (agregado.toLowerCase()) {
            case "leche":
                return COSTO_LECHE;
            case "cremora":
                return COSTO_CREMORA;
            case "sencillo":
                return COSTO_SENCILLO;
            default:
                return 0.0;
        }
    }

    public double calcularTotal(boolean esMayorEdad) {
        double total = precio + costoAgregado;
        if (esMayorEdad) {
            total *= 0.9; // Aplicar un 10% de descuento
        }
        return total;
    }

    public String getTamaño() {
        return tamaño;
    }
}

public class CafeExpress {
    private static int totalClientes = 0;
    private static int[] cantidadPorTamaño = new int[3]; // 0: pequeño, 1: mediano, 2: grande
    private static double totalCaja = 0.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continuar;

        do {
            System.out.println("Ingrese el  del cafe ( 8OZ pequeño, 16Ozmediano, 24Ozgrande): ");
            String tamaño = scanner.nextLine();
            System.out.println("Ingrese el agregado (leche, cremora, sencillo): ");
            String agregado = scanner.nextLine();
            System.out.println(" Es tercera de edad (60 o mas) ? (si/no): ");
            boolean esMayorEdad = scanner.nextLine().equalsIgnoreCase("si");

            Cafe cafe = new Cafe(tamaño, agregado);
            double total = cafe.calcularTotal(esMayorEdad);
            totalCaja += total;
            totalClientes++;

            // Contar cafés vendidos por tamaño
            switch (tamaño.toLowerCase()) {
                case "pequeño":
                    cantidadPorTamaño[0]++;
                    break;
                case "mediano":
                    cantidadPorTamaño[1]++;
                    break;
                case "grande":
                    cantidadPorTamaño[2]++;
                    break;
            }

            System.out.printf("El total a pagar es: Lps. %.2f%n", total);
            System.out.println(" Desea continuar? (si/no): ");
            continuar = scanner.nextLine();

        } while (continuar.equalsIgnoreCase("si"));

        // Mostrar resumen del día
        System.out.println("Resumen del dia:");
        System.out.println("Clientes atendidos: " + totalClientes);
        System.out.println("Cafes vendidos - Pequeños: " + cantidadPorTamaño[0]);
        System.out.println("Cafes vendidos - Medianos: " + cantidadPorTamaño[1]);
        System.out.println("Cafes vendidos - Grandes: " + cantidadPorTamaño[2]);
        System.out.printf("Total en caja: Lps. %.2f%n", totalCaja);

        scanner.close();
    }
}