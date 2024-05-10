package com.David.Desafio5Oracle.Modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Struct;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPersonajes(
        @JsonAlias("name") String nombre,
        @JsonAlias("height") String alturaEnCM,
        @JsonAlias("gender") String genero
) {

}
