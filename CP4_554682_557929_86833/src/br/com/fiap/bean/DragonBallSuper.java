//Carolina Santana Ferraz - RM86833
//Evellyn Valencia Choque - RM557929
//Milena Codinhoto da Silva - RM554682

package br.com.fiap.bean;

import java.io.*;

/**
 * Representa um personagem de Dragon Ball Super.
 *
 * <p>Atributos:
 * <ul>
 *   <li>nome (String)</li>
 *   <li>ki (int)</li>
 *   <li>tecnicas (int)</li>
 *   <li>velocidade (int)</li>
 *   <li>transformacoes (int)</li>
 * </ul>
 *
 * <p>Persistência:
 * <ul>
 *   <li><b>ler(path)</b>: lê o arquivo {nome}.txt que está em 'path' e retorna um objeto.
 *       Delegamos o tratamento de erro para IOException (ou seja, este método declara 'throws IOException').</li>
 *   <li><b>gravar(path)</b>: grava em {nome}.txt dentro de 'path' e retorna mensagem de sucesso/erro.</li>
 * </ul>
 *
 * <p>Formato do arquivo (uma informação por linha):
 * <pre>
 * nome
 * ki
 * tecnicas
 * velocidade
 * transformacoes
 * </pre>
 */
public class DragonBallSuper implements IDBSuper {

    private String nome;
    private int ki;
    private int tecnicas;
    private int velocidade;
    private int transformacoes;

    /** Construtor vazio */
    public DragonBallSuper() {}

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getKi() { return ki; }
    public void setKi(int ki) { this.ki = ki; }

    public int getTecnicas() { return tecnicas; }
    public void setTecnicas(int tecnicas) { this.tecnicas = tecnicas; }

    public int getVelocidade() { return velocidade; }
    public void setVelocidade(int velocidade) { this.velocidade = velocidade; }

    public int getTransformacoes() { return transformacoes; }
    public void setTransformacoes(int transformacoes) { this.transformacoes = transformacoes; }

    /**
     * Lê o arquivo {nome}.txt em 'path' e retorna um objeto DragonBallSuper.
     * @param path Caminho da pasta onde o arquivo se encontra.
     * @return Objeto DragonBallSuper populado.
     * @throws IOException Se o arquivo não existir, não puder ser lido ou se valores numéricos forem inválidos.
     */
    @Override
    public DragonBallSuper ler(String path) throws IOException {
        if (nome == null || nome.isBlank()) {
            throw new IOException("Nome do personagem não informado para leitura.");
        }

        File file = new File(path, this.nome + ".txt");
        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo não encontrado: " + file.getAbsolutePath());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            DragonBallSuper personagem = new DragonBallSuper();

            String linhaNome = br.readLine();
            String linhaKi = br.readLine();
            String linhaTecnicas = br.readLine();
            String linhaVelocidade = br.readLine();
            String linhaTransformacoes = br.readLine();

            if (linhaNome == null || linhaKi == null || linhaTecnicas == null
                    || linhaVelocidade == null || linhaTransformacoes == null) {
                throw new IOException("Arquivo com formato inválido (linhas faltando).");
            }

            personagem.setNome(linhaNome.trim());
            try {
                personagem.setKi(Integer.parseInt(linhaKi.trim()));
                personagem.setTecnicas(Integer.parseInt(linhaTecnicas.trim()));
                personagem.setVelocidade(Integer.parseInt(linhaVelocidade.trim()));
                personagem.setTransformacoes(Integer.parseInt(linhaTransformacoes.trim()));
            } catch (NumberFormatException nfe) {
                // Encapsula como IOException (delegando o tratamento para quem chamou)
                throw new IOException("Arquivo contém valores numéricos inválidos.", nfe);
            }

            return personagem;
        }
    }

    /**
     * Grava os dados do personagem em {nome}.txt dentro de 'path'.
     * @param path Caminho da pasta onde o arquivo será salvo.
     * @return Mensagem de sucesso ou falha (com motivo).
     */
    @Override
    public String gravar(String path) {
        if (nome == null || nome.isBlank()) {
            return "Falha ao salvar: nome do personagem não informado.";
        }

        File pasta = new File(path);
        if (!pasta.exists()) {
            if (!pasta.mkdirs()) {
                return "Falha ao salvar: não foi possível criar a pasta " + pasta.getAbsolutePath();
            }
        } else if (!pasta.isDirectory()) {
            return "Falha ao salvar: o caminho informado não é uma pasta.";
        }

        File file = new File(pasta, this.nome + ".txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(this.nome + System.lineSeparator());
            bw.write(this.ki + System.lineSeparator());
            bw.write(this.tecnicas + System.lineSeparator());
            bw.write(this.velocidade + System.lineSeparator());
            bw.write(this.transformacoes + System.lineSeparator());
            return "Personagem salvo com sucesso em: " + file.getAbsolutePath();
        } catch (IOException e) {
            return "Falha ao salvar arquivo: " + e.getMessage();
        }
    }
}
