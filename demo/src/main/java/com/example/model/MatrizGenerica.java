package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class MatrizGenerica<K, V> {
    public Map<K, Map<K, V>> matriz;

    public MatrizGenerica() {
        matriz = new HashMap<>();
    }

    public void setValor(K fila, K columna, V valor) {
        if (!matriz.containsKey(fila)) {
            matriz.put(fila, new HashMap<>());
        }
        matriz.get(fila).put(columna, valor);
    }

    public V getValor(K fila, K columna) {
        if (matriz.containsKey(fila)) {
            Map<K, V> filaMap = matriz.get(fila);
            if (filaMap.containsKey(columna)) {
                return filaMap.get(columna);
            }
        }
        return null;
    }

    public boolean deleteValor(K fila, K columna) {
        if (matriz.containsKey(fila)) {
            Map<K, V> filaMap = matriz.get(fila);
            matriz.remove(fila);
            if (filaMap.containsKey(columna)) {
                matriz.remove(columna);
            }
        }
        return true;
    }
}
