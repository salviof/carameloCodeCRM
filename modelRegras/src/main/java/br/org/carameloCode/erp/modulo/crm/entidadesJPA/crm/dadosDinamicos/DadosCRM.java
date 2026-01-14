/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sfurbino
 */
@InfoObjetoSB(tags = {"Dado dinâmico"}, plural = "Dados Dinâmicos")
@Deprecated
public class DadosCRM {

    public static List<PessoaJuridica> getProspectosPorAcaoEDescricao(String descricao, TipoRelacionamento pTipoRelacionamento, EntityManager em) {
        if (pTipoRelacionamento == null) {
            return new ArrayList<>();
        }

        return UtilSBPersistencia.getListaRegistrosByHQL("from ProspectoEmpresa where relacionamento.tipoRelacionamento.id=?", -1, em,
                pTipoRelacionamento.getId());
    }

    public static List<AtividadeCRM> getAtividadePorProspectoeTipo(TipoAtividadeCRM pTipoAtividade, PessoaJuridica pProspecto, EntityManager em) {
        if (pTipoAtividade == null) {
            return new ArrayList<>();
        }

        return UtilSBPersistencia.getListaRegistrosByHQL("from AtividadeCRM where prospecto.id=? and tipoAtividade.id=?", -1, em,
                pProspecto.getId(), pTipoAtividade.getId());

    }

    public List<PessoaJuridica> getClientesDoUsuario() {

        throw new UnsupportedOperationException("a busca de clientes do usuário ainda não foi implementada");

    }

    public List<PessoaJuridica> getProspectosDoUsuario() {

        throw new UnsupportedOperationException("a busca de clientes do usuário ainda não foi implementada");

    }

}
