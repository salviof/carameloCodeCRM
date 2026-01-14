/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.ControleEmailRecebido;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

/**
 *
 * @author salviofurbino
 * @since 11/05/2019
 * @version 1.0
 */
public class ListenerContatoProspecto extends ListenerEntidadePadrao {

    private String emailAnterior;

    private boolean isPrecisarevisarEmailsServidor(ComoEntidadeSimples pEntidade) {
        if (pEntidade instanceof ContatoProspecto) {
            ContatoProspecto c = (ContatoProspecto) pEntidade;
            if (UtilCRCStringValidador.isNuloOuEmbranco(emailAnterior) && !UtilCRCStringValidador.isNuloOuEmbranco(((ContatoProspecto) pEntidade).getEmail())) {
                return true;
            }
            if (!UtilCRCStringValidador.isNuloOuEmbranco(emailAnterior) && !UtilCRCStringValidador.isNuloOuEmbranco(((ContatoProspecto) pEntidade).getEmail())) {
                return !emailAnterior.equals(((ContatoProspecto) pEntidade).getEmail());

            }
            return false;
        }
        return false;
    }

    @Override
    @PostPersist
    public void acaoAposPersistir(ComoEntidadeSimples pEntidade) {
        super.acaoAposPersistir(pEntidade);
        atualizarControleEmail(pEntidade);
    }

    private void atualizarControleEmail(ComoEntidadeSimples pEntidadeAposAtualizacaoEInsersao) {
        if (pEntidadeAposAtualizacaoEInsersao instanceof ContatoProspecto) {
            ContatoProspecto c = (ContatoProspecto) pEntidadeAposAtualizacaoEInsersao;
            if (isPrecisarevisarEmailsServidor(pEntidadeAposAtualizacaoEInsersao)) {
                if (!UtilSBPersistencia.executaSQL("delete from " + ControleEmailRecebido.class.getSimpleName() + " where remetente like '" + ((ContatoProspecto) pEntidadeAposAtualizacaoEInsersao).getEmail() + "'")) {
                    throw new UnsupportedOperationException("Erro atualizando Controle de email recebido");
                }
            }
        }
    }

    @Override
    @PostUpdate
    public void acaoAposAtualizar(ComoEntidadeSimples emp) {
        super.acaoAposAtualizar(emp); //chamada super do metodo (implementação classe pai)
        atualizarControleEmail(emp);
    }

    @Override
    @PostLoad
    public void objetoEstadoAnterior(ComoEntidadeSimples pEntidade) {

        if (pEntidade instanceof ContatoProspecto) {
            ContatoProspecto c = (ContatoProspecto) pEntidade;
            if (c.getId() != 0) {
                emailAnterior = c.getEmail();
            }
        }

    }

}
