package com.David.Desafio5Oracle.Principal;

import com.David.Desafio5Oracle.Modelos.DatosPelicula;
import com.David.Desafio5Oracle.Modelos.Pelicula;
import com.David.Desafio5Oracle.Modelos.Personaje;
import com.David.Desafio5Oracle.Service.ConsumoAPi;
import com.David.Desafio5Oracle.Service.ConvertirDatos;

import java.net.URLEncoder;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner write = new Scanner(System.in);
    private ConsumoAPi consumoAPi = new ConsumoAPi();
    private ConvertirDatos convertirDatos = new ConvertirDatos();

    public void muestraMenu(){
        System.out.println("De la 1 a la 7 escoge el número de la película de " +
                "Star Wars que quieres ver.");
        String buscarPelicula = URLEncoder.encode(write.nextLine());

        //Obtengo el Json por medio de la url
        var json = consumoAPi.obtenerDatos("https://swapi.py4e.com/api/films/"+buscarPelicula+"/");
        //Convierto los datos del Json a tipo DatosPelicula
        var datosDePelicula = convertirDatos.convertirDatos(json, DatosPelicula.class);
        System.out.println(datosDePelicula);

        //Convierto el record en una clase de tipo Pelicula
        Pelicula pelicula = new Pelicula(datosDePelicula);

        //Hago la lista de personajes a la clase Personaje
        List<Personaje> personajes = pelicula.getPersonajes().stream()
                .map(p -> new Personaje(p))
                .collect(Collectors.toList());

        //Busqueda por personajes
        System.out.println("Coloca el nombre dle personaje a buscar: ");
        String buscarPersonaje = write.nextLine();

        Optional<Personaje> personajeAEncontrar = personajes.stream()
                .filter(p -> p.getNombre().toUpperCase().contains(buscarPersonaje.toUpperCase()))
                .findFirst();
        if (personajeAEncontrar.isPresent()){
            System.out.println("Personaje encontrado... Mostrando datos");
            System.out.println(personajeAEncontrar.get());
        }else {
            System.out.println("Personaje no encontrado en esta pelìcula...");
        }

        IntSummaryStatistics estAltura = personajes.stream()
                .filter(p -> p.getAlturaEnCm() > 0)
                .collect(Collectors.summarizingInt(Personaje::getAlturaEnCm));

        System.out.println("La aultura media es de: "+ estAltura.getAverage());
        System.out.println("EL personaje mas alto mide: "+estAltura.getMax());
        System.out.println("El personaje mas bajo es; "+estAltura.getMin());

        //Generar estadisticas de altura segun el genero
        System.out.println("Ingrese el genero del que quiere saber las estadisticas: \n" +
                "M = Masculino\n" +
                "F = Femenino");
        char gender = write.next().toUpperCase().charAt(0);
        IntSummaryStatistics estGenero = personajes.stream()
                .filter(p -> p.getGenero() != null)
                .filter(p -> p.getGenero().equals(gender))
                .peek(System.out::println)
                .filter(p -> p.getAlturaEnCm() > 0)
                .collect(Collectors.summarizingInt(Personaje::getAlturaEnCm));
        System.out.println("El promedio de los personajes "+ gender);
        System.out.println("La aultura media es de: "+ estGenero.getAverage());
        System.out.println("EL personaje mas alto mide: "+estGenero.getMax());
        System.out.println("El personaje mas bajo es; "+estGenero.getMin());
        System.out.println("Cantidad de personajes "+gender+" emcpmtradps "+estGenero.getCount());
    }

}
