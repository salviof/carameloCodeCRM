package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposCRMCaramelo;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaStatusComPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvio
 */
public enum FabStatusSolicitacao implements ComoFabricaStatusComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 1, cor = "#E67E22", nomeObjeto = "Cliente buscando arquivo", icone = "fa fa-file-o")
    CLIENTE_DEVENDO_ARQUIVO,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 2, cor = "#D68910", nomeObjeto = "Falta atividade do cliente", icone = "fa fa-tasks")
    CLIENTE_DEVENDO_ATIVIDADE,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 3, cor = "#F39C12", nomeObjeto = "Falta confirmação do cliente", icone = "fa fa-question-circle")
    CLIENTE_DEVENDO_CONFIRMACAO,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 4, cor = "#2E86C1", nomeObjeto = "Falta arquivo da equipe", icone = "fa fa-file-text-o")
    EQUIPE_DEVENDO_ARQUIVO_A_EQUIPE,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 9, cor = "#3498DB", nomeObjeto = "Falta arquivo para cliente", icone = "fa fa-upload")
    EQUIPE_DEVENDO_ARQUIVO_A_CLIENTE,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 5, cor = "#7D3C98", nomeObjeto = "Falta orçamento para equipe", icone = "fa fa-paper-plane")
    EQUIPE_DEVENDO_ORCAMENTO_A_EQUIPE,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 6, cor = "#8E44AD", nomeObjeto = "Falta orçamento para cliente", icone = "fa fa-dollar")
    EQUIPE_DEVENDO_ORCAMENTO_AO_CLIENTE,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 7, cor = "#2874A6", nomeObjeto = "Falta atividade da equipe", icone = "fa fa-cogs")
    EQUIPE_DEVENDO_ATIVIDADE,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 8, cor = "#1F618D", nomeObjeto = "Falta abertura de chamado", icone = "fa fa-ticket")
    EQUIPE_DEVENDO_ABERTURA_CHAMADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 10, cor = "#48C9B0", nomeObjeto = "Falta liberação de acesso", icone = "fa fa-key")
    EQUIPE_ACESSO_PESSOA,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 11, cor = "#5DADE2", nomeObjeto = "Falta confirmação ao cliente", icone = "fa fa-comments-o")
    EQUIPE_DEVENDO_CONFIRMACAO_CLIENTE,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 12, cor = "#21618C", nomeObjeto = "Falta confirmação interna", icone = "fa fa-users")
    EQUIPE_DEVENDO_CONFIRMACAO_EQUIPE,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 13, cor = "#27AE60", nomeObjeto = "Finalizado", icone = "fa fa-check-circle")
    FINALIZADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 14, cor = "#C0392B", nomeObjeto = "Recusado", icone = "fa fa-times-circle")
    RECUSADO;

    @Override
    public StatusSolicitacao getRegistro() {
        return (StatusSolicitacao) ComoFabricaStatusComPersistencia.super.getRegistro();
    }

    @Override
    public List<ComoAcaoDoSistema> opcoesPorStatus() {
        List<ComoAcaoDoSistema> acoes = new ArrayList<>();
        if (CarameloCode.getUsuarioLogado().getGrupo().equals(FabGruposCRMCaramelo.CRM_CLIENTE.getRegistro())) {
            acoes.add(FabAcaoCRMCliente.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getRegistro());
        } else {
            switch (this) {
                case FINALIZADO:
                case RECUSADO:
                    return acoes;

                default:
                    acoes.add(FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getRegistro());
                    acoes.add(FabAcaoCRMAtendimento.SOLICITACAO_FRM_REVISAR_SOLICITACAO.getRegistro());
            }

        }
        return acoes;
    }

}
