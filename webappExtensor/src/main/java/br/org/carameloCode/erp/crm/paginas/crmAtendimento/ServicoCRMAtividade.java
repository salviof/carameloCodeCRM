package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@Named
@RequestScoped
public class ServicoCRMAtividade {

    @Inject
    private ItfPaginaAtual paginaAtual;

    public void agendarEmMeiaHora(AtividadeCRM pAtividade) {
        pAtividade.setDataHoraPrevisaoExecucao(UtilCRCDataHora.incrementaMinutos(new Date(), 30));
        ItfResposta resposta = ModuloCRMAtendimento.agendarAtividade(pAtividade);

        paginaAtual.getInfoPagina().getComoPaginaComEntityManager().renovarEntityManager();
    }

    public void agendar2Horas(AtividadeCRM pAtividade) {
        pAtividade.setDataHoraPrevisaoExecucao(UtilCRCDataHora.incrementaMinutos(new Date(), 120));
        ItfResposta resposta = ModuloCRMAtendimento.agendarAtividade(pAtividade);
        paginaAtual.getInfoPagina().getComoPaginaComEntityManager().renovarEntityManager();
    }

    public void agendarAmanha(AtividadeCRM pAtividade) {
        pAtividade.setDataHoraPrevisaoExecucao(UtilCRCDataHora.incrementaDias(new Date(), 1));
        ItfResposta resposta = ModuloCRMAtendimento.agendarAtividade(pAtividade);
        paginaAtual.getInfoPagina().getComoPaginaComEntityManager().renovarEntityManager();
    }

    public void cancelarRapido(AtividadeCRM pAtividade) {
        ItfRespostaAcaoDoSistema resp = ModuloCRMAtendimento.atividadecancelaAtividade(pAtividade);
        paginaAtual.getInfoPagina().getComoPaginaComEntityManager().renovarEntityManager();
    }

}
