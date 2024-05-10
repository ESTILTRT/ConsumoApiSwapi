package com.David.Desafio5Oracle.Modelos;

import java.util.IntSummaryStatistics;

public class Personaje {
    private String nombre;
    private Integer alturaEnCm;
    private Character genero;

    public Personaje(DatosPersonajes p) {
        this.nombre = p.nombre();
        this.alturaEnCm = setAlturaEnCm(p.alturaEnCM());
        this.genero = setGenero(p.genero());
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getAlturaEnCm() {
        return alturaEnCm;
    }

    private Integer setAlturaEnCm(String alturaEnCm) {
        try {
            return Integer.valueOf(alturaEnCm);
        }catch (NumberFormatException e){
             return null;
        }
    }

    public Character getGenero() {
        return genero;
    }

    public Character setGenero(String genero) {
        if (genero.equalsIgnoreCase("male")){
            return 'M';
        }
        else if (genero.equalsIgnoreCase("female")) {
            return 'F';
        }
        else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + this.nombre + '\'' +
                ", alturaEnCm=" + this.alturaEnCm +
                ", genero=" + this.genero +
                '}';
    }
}
