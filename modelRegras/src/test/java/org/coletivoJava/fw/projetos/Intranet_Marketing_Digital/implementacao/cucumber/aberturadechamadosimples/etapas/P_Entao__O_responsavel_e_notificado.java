package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import cucumber.api.java.pt.Entao;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;

public class P_Entao__O_responsavel_e_notificado {

    @Entao(EtapasAberturaDeChamadoSimples.ENTAO_O_RESPONSAVEL_E_NOTIFICADO)
    public void implementacaoEtapa() {
        ComoUsuario usuario = CarameloCode.getServicoPermissao().getUsuarioByEmail("salvio@casanovadigital.com.br");
        List<ComoDialogo> notificacoesResponsavel = CarameloCode.getServicoComunicacao().getNotificacoesAtivasMenu();
        for (ComoDialogo dialogo : notificacoesResponsavel) {
            if (dialogo.getTipoComunicacao() instanceof TiponotificacaoCRM) {
                TiponotificacaoCRM tipo = (TiponotificacaoCRM) dialogo.getTipoComunicacao();
                if (tipo.getId().equals(FabTipoNotificacao.NOTIFICACAO_RESPONSAVEIS_CHAMADO_ABERTO.getRegistro().getId())) {
                    System.out.println("Noificou");
                }
            }
        }
    }
}
