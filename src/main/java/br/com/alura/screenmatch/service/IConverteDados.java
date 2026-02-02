package br.com.alura.screenmatch.service;

public interface IConverteDados {
    // T entre setas é como se fosse "generico"
    <T> T obterDados(String json, Class <T> classe);
}
