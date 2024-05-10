package com.David.Desafio5Oracle.Modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPelicula(
        @JsonAlias("title") String titulo,
        @JsonAlias("director") String director,
        @JsonAlias("release_date") String fechaSalida,
        @JsonAlias("characters") List<String> urlPersonaje
        ) {
}
