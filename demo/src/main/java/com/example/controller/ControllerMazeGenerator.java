package com.example.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ControllerMazeGenerator {

    // Constants for maze dimensions and cell types

    public static final int nMax = 51; // impar
    public static final int mMax = 51; // impar
    public static final int BLOQUE = 0;
    public static final int LIBRE = 1;
    public int[][] laberinto;

    // Method to add a coordinate pair (a, b) to the array A if it's not already
    // present

    public static void agregarParOrdenado(int[][] A, int[] k, int a, int b) {
        boolean Esta = false;
        for (int i = 0; i < k[0]; i++) {
            if (a == A[i][0] && b == A[i][1]) {
                Esta = true;
                break;
            }
        }
        if (Esta == false) {
            A[k[0]][0] = a;
            A[k[0]][1] = b;
            k[0]++;
        }
    }

    // Method to get a random coordinate pair (x, y) from array a, and remove it

    public static void obtenerParOrdenadoRandom(int[][] a, int[] k, int[] x, int[] y) {
        if (k[0] == 0) {
            // No elements in the array, cannot get a random one.
            x[0] = -1;
            y[0] = -1;
        } else {
            Random rand = new Random();
            int index = rand.nextInt(k[0]);
            x[0] = a[index][0];
            y[0] = a[index][1];

            // Remove the selected element and reduce the effective length of the array
            for (int i = index; i < k[0] - 1; i++) {
                a[i][0] = a[i + 1][0];
                a[i][1] = a[i + 1][1];
            }
            k[0]--;
        }
    }

    // Method to create a maze using a modified version of the Prim's algorithm

    public static void crearLaberinto(int[][] M, int m, int n) {
        int[][] ID_VECINOS_BLOQUEADOS = new int[m + n][2];
        int[][] ID_VECINOS_DESBLOQUEADOS = new int[4][2];
        int[] kb = new int[1];
        int[] kd = new int[1];
        int maxBlock = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = BLOQUE;
            }
        }
        int[] Oi = new int[1];
        int[] Oj = new int[1];
        int[] Ii = new int[1];
        int[] Ij = new int[1];
        Oi[0] = 1;
        Oj[0] = 1;
        M[Oi[0]][Oj[0]] = LIBRE;
        if (Oj[0] - 2 > 0 && M[Oi[0]][Oj[0] - 2] == BLOQUE) {
            agregarParOrdenado(ID_VECINOS_BLOQUEADOS, kb, Oi[0], Oj[0] - 2);
        }
        if (Oj[0] + 2 < n && M[Oi[0]][Oj[0] + 2] == BLOQUE) {
            agregarParOrdenado(ID_VECINOS_BLOQUEADOS, kb, Oi[0], Oj[0] + 2);
        }
        if (Oi[0] - 2 > 0 && M[Oi[0] - 2][Oj[0]] == BLOQUE) {
            agregarParOrdenado(ID_VECINOS_BLOQUEADOS, kb, Oi[0] - 2, Oj[0]);
        }
        if (Oi[0] + 2 < m && M[Oi[0] + 2][Oj[0]] == BLOQUE) {
            agregarParOrdenado(ID_VECINOS_BLOQUEADOS, kb, Oi[0] + 2, Oj[0]);
        }
        int count = 0;
        while (kb[0] > 0) {
            if (maxBlock < kb[0]) {
                maxBlock = kb[0];
            }
            obtenerParOrdenadoRandom(ID_VECINOS_BLOQUEADOS, kb, Oi, Oj); // a) y g)
            // agregando vecinos desbloqueados
            kd[0] = 0;
            if (Oj[0] - 2 > 0 && M[Oi[0]][Oj[0] - 2] == LIBRE) {
                agregarParOrdenado(ID_VECINOS_DESBLOQUEADOS, kd, Oi[0], Oj[0] - 2);
            }
            if (Oj[0] + 2 < n && M[Oi[0]][Oj[0] + 2] == LIBRE) {
                agregarParOrdenado(ID_VECINOS_DESBLOQUEADOS, kd, Oi[0], Oj[0] + 2);
            }
            if (Oi[0] - 2 > 0 && M[Oi[0] - 2][Oj[0]] == LIBRE) {
                agregarParOrdenado(ID_VECINOS_DESBLOQUEADOS, kd, Oi[0] - 2, Oj[0]);
            }
            if (Oi[0] + 2 < m && M[Oi[0] + 2][Oj[0]] == LIBRE) {
                agregarParOrdenado(ID_VECINOS_DESBLOQUEADOS, kd, Oi[0] + 2, Oj[0]);
            }
            obtenerParOrdenadoRandom(ID_VECINOS_DESBLOQUEADOS, kd, Ii, Ij); // c)
            if (Ii[0] == Oi[0]) { // d)
                if (Ij[0] < Oj[0]) {
                    M[Ii[0]][Ij[0] + 1] = LIBRE;
                } else {
                    M[Ii[0]][Oj[0] + 1] = LIBRE;
                }
            } else {
                if (Ii[0] < Oi[0]) {
                    M[Ii[0] + 1][Ij[0]] = LIBRE;
                } else {
                    M[Oi[0] + 1][Ij[0]] = LIBRE;
                }
            }
            M[Oi[0]][Oj[0]] = LIBRE; // f)
            // agregando vecinos bloqueados
            if (Oj[0] - 2 > 0 && M[Oi[0]][Oj[0] - 2] == BLOQUE) {
                agregarParOrdenado(ID_VECINOS_BLOQUEADOS, kb, Oi[0], Oj[0] - 2);
            }
            if (Oj[0] + 2 < n && M[Oi[0]][Oj[0] + 2] == BLOQUE) {
                agregarParOrdenado(ID_VECINOS_BLOQUEADOS, kb, Oi[0], Oj[0] + 2);
            }
            if (Oi[0] - 2 > 0 && M[Oi[0] - 2][Oj[0]] == BLOQUE) {
                agregarParOrdenado(ID_VECINOS_BLOQUEADOS, kb, Oi[0] - 2, Oj[0]);
            }
            if (Oi[0] + 2 < m && M[Oi[0] + 2][Oj[0]] == BLOQUE) {
                agregarParOrdenado(ID_VECINOS_BLOQUEADOS, kb, Oi[0] + 2, Oj[0]);
            }
            count++;
        }
    }

    // Method to mark the final point in the maze

    public static void marcarFinalLaberinto(int[][] M, int m, int n, int x, int y) {
        // Marcar el punto final en la matriz M en la posición (x, y)
        M[x][y] = 3;
    }

    // Method to save the maze to a file specified by the given path

    public static void guardarLaberintoEnArchivo(int[][] M, int m, int n, String rutaCompleta) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + rutaCompleta);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (M[i][j] == BLOQUE) {
                        bufferedWriter.write("0 ");
                    } else if (M[i][j] == LIBRE) {
                        bufferedWriter.write("1 ");
                    } else if (M[i][j] == 2) {
                        bufferedWriter.write("1 "); // Punto de inicio
                    } else if (M[i][j] == 3) {
                        bufferedWriter.write("0 "); // Punto de final
                    }
                }
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Main method to demonstrate maze generation and saving

    public static void main(String[] args) {
        int m = mMax;
        int n = nMax;
        int[][] laberinto = new int[m][n];

        crearLaberinto(laberinto, m, n);

        // Marcar el punto de inicio (por ejemplo, en la esquina superior izquierda)
        laberinto[1][1] = 2;

        // Marcar el punto final (por ejemplo, en la esquina inferior derecha)
        marcarFinalLaberinto(laberinto, m, n, m - 2, n - 2);

        // mostrarLaberinto(laberinto, m, n);

        guardarLaberintoEnArchivo(laberinto, m, n,
                "/demo/src/main/java/com/example/resources/Map/laberinto.txt");

    }

}
