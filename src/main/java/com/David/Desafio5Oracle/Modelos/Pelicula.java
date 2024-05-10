package com.David.Desafio5Oracle.Modelos;

import com.David.Desafio5Oracle.Service.ConsumoAPi;
import com.David.Desafio5Oracle.Service.ConvertirDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Pelicula {
    //instancias
    private  ConsumoAPi consumoAPi = new ConsumoAPi();
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //Varables Globales
    private String titulo;
    private String director;
    private LocalDate fechaSalida;
    private List<DatosPersonajes> personajes;

    public Pelicula(DatosPelicula datosDePelicula) {
        this.titulo = datosDePelicula.titulo();
        this.director = datosDePelicula.director();
        this.fechaSalida = LocalDate.parse(datosDePelicula.fechaSalida());
        this.personajes = setPersonajes(datosDePelicula.urlPersonaje());
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public List<DatosPersonajes> getPersonajes() {
        return personajes;
    }

    private List<DatosPersonajes> setPersonajes(List<String> urlpersonajes) {
        List<DatosPersonajes> personajes = new ArrayList<>();
        for (String url:urlpersonajes){
            var json = consumoAPi.obtenerDatos(url);
            var datos = convertirDatos.convertirDatos(json , DatosPersonajes.class);
            personajes.add(datos);
        }
        return personajes;
    }

    @Override
    public String toString() {
        return "Nombre: "+this.titulo+
                "\nDirector: "+this.director+
                "\nFecha de salida: "+this.fechaSalida.format(dtf)+
                "\nCantidad de personajes de la película: "+ this.personajes.size();
    }
}
