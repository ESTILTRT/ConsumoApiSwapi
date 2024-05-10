package com.David.Desafio5Oracle.Interfaces;

public interface IConvertirDatos {
    <T> T convertirDatos(String json, Class<T> clase);
}
