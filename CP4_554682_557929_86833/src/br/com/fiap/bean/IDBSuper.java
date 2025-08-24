package br.com.fiap.bean;

import java.io.IOException;

/**
 * Interface de persistência para DragonBallSuper.
 * - ler(path): lê um arquivo e retorna um objeto DragonBallSuper (propaga IOException).
 * - gravar(path): grava em arquivo e retorna uma mensagem de sucesso ou erro (não lança IOException).
 */
public interface IDBSuper {
    br.com.fiap.bean.DragonBallSuper ler(String path) throws IOException;
    String gravar(String path);
}