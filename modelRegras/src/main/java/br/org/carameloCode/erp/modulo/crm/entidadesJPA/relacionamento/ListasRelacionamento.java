/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.listas.ItfListas;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.LazyInitializationException;

/**
 *
 * @author desenvolvedor
 */
public enum ListasRelacionamento implements ItfListas {

    TOTAL,
    ATIVIDADES_DO_TIPO_POR_USUARIO;

    @Override
    public List getLista(Object... pParametros) {

        TipoRelacionamento tipoRelacionamento = (TipoRelacionamento) pParametros[0];

        try {
            switch (this) {
                case TOTAL:
                    break;
                case ATIVIDADES_DO_TIPO_POR_USUARIO:
                    UsuarioCRM usuario = (UsuarioCRM) pParametros[1];

                    List<AtividadeCRM> atividades = new ArrayList<>();
                    tipoRelacionamento.getAtividades().stream()
                            .filter((atividade)
                                    -> (atividade.getUsuarioAtividade().getId().equals(usuario.getId()))).forEachOrdered((atividade) -> {
                        atividades.add(atividade);
                    });
                    return atividades;

                default:

                    throw new AssertionError("Não existe um caso para o tipo:" + this.name());

            }
        } catch (LazyInitializationException t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O entitymanager do prospecto ou atividade foi finalizado, impossível obter os dados", t);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo lista" + this.toString(), t);

        }
        return null;
    }

    @Override
    public Class getClasse() {
        return TipoRelacionamento.class;
    }

}
