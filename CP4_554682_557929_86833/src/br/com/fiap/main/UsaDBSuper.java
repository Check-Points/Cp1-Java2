//Carolina Santana Ferraz - RM86833
//Evellyn Valencia Choque - RM557929
//Milena Codinhoto da Silva - RM554682

package br.com.fiap.main;

import javax.swing.*;
import br.com.fiap.bean.DragonBallSuper;
import java.io.IOException;

/**
 * Classe com método main que interage com o usuário via JOptionPane.
 * - Exibe menu (cadastrar/consultar).
 * - Pede caminho da pasta.
 * - Executa a operação escolhida.
 * - Pergunta se deseja continuar.
 */
public class UsaDBSuper {

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            try {
                String opcao = JOptionPane.showInputDialog(
                        "Escolha uma opção:\n1 - Cadastrar personagem\n2 - Consultar personagem");
                if (opcao == null) { // cancelou
                    JOptionPane.showMessageDialog(null, "Operação cancelada. Até logo! 👋");
                    break;
                }

                String path = JOptionPane.showInputDialog("Informe o caminho da pasta:");
                if (path == null || path.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Caminho inválido. Tente novamente.");
                    continue;
                }

                DragonBallSuper personagem = new DragonBallSuper();

                if ("1".equals(opcao.trim())) {
                    // Cadastrar
                    String nome = JOptionPane.showInputDialog("Nome do personagem:");
                    if (nome == null || nome.isBlank()) {
                        JOptionPane.showMessageDialog(null, "Nome não informado.");
                        continue;
                    }
                    personagem.setNome(nome.trim());

                    Integer ki = lerInteiro("KI:");
                    if (ki == null) continue; // usuário cancelou
                    personagem.setKi(ki);

                    Integer tecnicas = lerInteiro("Técnicas:");
                    if (tecnicas == null) continue;
                    personagem.setTecnicas(tecnicas);

                    Integer velocidade = lerInteiro("Velocidade:");
                    if (velocidade == null) continue;
                    personagem.setVelocidade(velocidade);

                    Integer transformacoes = lerInteiro("Transformações:");
                    if (transformacoes == null) continue;
                    personagem.setTransformacoes(transformacoes);

                    // gravação com try/catch embutido no método (retorna mensagem)
                    String msg = personagem.gravar(path);
                    JOptionPane.showMessageDialog(null, msg);

                } else if ("2".equals(opcao.trim())) {
                    // Consultar
                    String nomeConsulta = JOptionPane.showInputDialog("Nome do personagem para consultar:");
                    if (nomeConsulta == null || nomeConsulta.isBlank()) {
                        JOptionPane.showMessageDialog(null, "Nome não informado.");
                        continue;
                    }
                    personagem.setNome(nomeConsulta.trim());

                    try {
                        DragonBallSuper lido = personagem.ler(path);
                        JOptionPane.showMessageDialog(null,
                                "Nome: " + lido.getNome() +
                                        "\nKI: " + lido.getKi() +
                                        "\nTécnicas: " + lido.getTecnicas() +
                                        "\nVelocidade: " + lido.getVelocidade() +
                                        "\nTransformações: " + lido.getTransformacoes()
                        );
                    } catch (IOException io) {
                        JOptionPane.showMessageDialog(null, "Erro ao ler arquivo: " + io.getMessage());
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                }

                int resp = JOptionPane.showConfirmDialog(null, "Deseja continuar?");
                if (resp != JOptionPane.YES_OPTION) {
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "Até logo! 👋");
                }

            } catch (Exception e) {
                // segurança extra para qualquer exceção inesperada
                JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
            }
        }
    }

    /**
     * Lê um inteiro do usuário com tratamento de erro e opção de cancelar.
     * Retorna null se o usuário cancelar.
     */
    private static Integer lerInteiro(String prompt) {
        while (true) {
            String s = JOptionPane.showInputDialog(prompt);
            if (s == null) return null;
            try {
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Valor inválido. Digite um número inteiro.");
            }
        }
    }
}