/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.integracao.primefaces;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.calculosListagens.ItfListasJPA;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.ScheduleEvent;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author desenvolvedor
 */
public enum ListasAgenda implements ItfListasJPA {

    EVENTOS;

    @Override
    public List getLista(Object... pObjeto) {

        switch (this) {
            case EVENTOS:
                List<ScheduleEvent> eventos;
                if (!SBCore.getCentralDeSessao().getSessaoAtual().isIdentificado()) {
                    return new ArrayList();
                }
                ComoUsuario usuarioLogado = SBCore.getUsuarioLogado();
                if (usuarioLogado instanceof UsuarioCRM) {
                    eventos = new ArrayList();

                    UsuarioCRM usr = (UsuarioCRM) usuarioLogado;
                    usr.getAtividadeAgendada().forEach(atv
                            -> {
                        try {
                            eventos.add(new EventoDeAgenda(atv));
                        } catch (Throwable t) {

                        }
                    });
                    return eventos;
                }
                return new ArrayList();

            default:
                throw new AssertionError(this.name());
        }

    }

    @Override
    public Class
            getClasse() {
        return Agenda.class;

    }

}
