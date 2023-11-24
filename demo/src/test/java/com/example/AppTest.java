package com.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.model.Graph;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :))))
     */

    @Test
    public void testAddVertex() {
        Graph grafo = new Graph<>(false);

        assertTrue(grafo.addVertex("A"));

        assertFalse(grafo.addVertex("A"));

        assertTrue(grafo.addVertex("B"));
    }
}
