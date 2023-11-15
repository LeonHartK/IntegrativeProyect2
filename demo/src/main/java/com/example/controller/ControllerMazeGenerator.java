package com.example.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ControllerMazeGenerator {

    public static final int nMax = 51; // impar
    public static final int mMax = 51; // impar
    public static final int BLOQUE = 0;
    public static final int LIBRE = 1;
    public int[][] laberinto;

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

    public static void obtenerParOrdenadoRandom(int[][] a, int[] k, int[] x, int[] y) {
        if (k[0] == 0) {
            // No hay elementos en el arreglo, no se puede obtener uno aleatorio.
            x[0] = -1;
            y[0] = -1;
        } else {
            Random rand = new Random();
            int index = rand.nextInt(k[0]);
            x[0] = a[index][0];
            y[0] = a[index][1];

            // Eliminar el elemento seleccionado y reducir la longitud efectiva del arreglo
            for (int i = index; i < k[0] - 1; i++) {
                a[i][0] = a[i + 1][0];
                a[i][1] = a[i + 1][1];
            }
            k[0]--;
        }
    }

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

    public static void mostrarLaberinto(int[][] M, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == BLOQUE) {
                    System.out.print("#"); // Representa una pared o bloque
                } else if (M[i][j] == LIBRE) {
                    System.out.print(" "); // Representa un espacio libre
                } else if (M[i][j] == 2) {
                    System.out.print("S"); // Punto de inicio
                } else if (M[i][j] == 3) {
                    System.out.print("F"); // Punto de final
                }
            }
            System.out.println(); // Salto de línea al final de cada fila
        }
    }

    public static void marcarFinalLaberinto(int[][] M, int m, int n, int x, int y) {
        // Marcar el punto final en la matriz M en la posición (x, y)
        M[x][y] = 3;
    }

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
