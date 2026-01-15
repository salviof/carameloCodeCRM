/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;

import java.util.HashMap;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
public class ControleGeracaoDocumento {

    public static class Geradordocumento extends Thread {

        public Geradordocumento(AtividadeCRM pAtividade) {
            atividade = pAtividade;
        }

        private final AtividadeCRM atividade;
        private boolean processoEmAndamento = true;
        private ItfResposta resposta;

        @Override
        public void run() {
            processoEmAndamento = true;
            ItfResposta resp = ModuloCRMEmail.gerarDocumentos(atividade, atividade.getUsuarioInsersao());
            System.out.println("Resposta=" + resp.isSucesso());
            resposta = resp;
            finalizar();

        }

        public ItfResposta getResposta() {
            return resposta;
        }

        private synchronized void finalizar() {
            processoEmAndamento = false;
        }

        public synchronized boolean isProcessoEmAndamento() {
            return processoEmAndamento;
        }

    }

    private static Map<String, Geradordocumento> geradores = new HashMap<>();

    public static void iniciarProcessamento(AtividadeCRM atividade) {
        geradores.remove(SBCore.getUsuarioLogado().getEmail());
        geradores.put(SBCore.getUsuarioLogado().getEmail(), new Geradordocumento(atividade));
        geradores.get(SBCore.getUsuarioLogado().getEmail()).start();
    }

    public static Geradordocumento processoEmAndamento() {
        try {
            Geradordocumento gerador = geradores.get(SBCore.getUsuarioLogado().getEmail());
            if (gerador == null) {
                System.out.println("Gerador nao encontrado");
                return null;
            }
            if (!gerador.processoEmAndamento) {
                geradores.remove(SBCore.getUsuarioLogado().getEmail());
            }
            return gerador;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo informaçeõs de processamento do documento", t);
            return null;
        }
    }

}
