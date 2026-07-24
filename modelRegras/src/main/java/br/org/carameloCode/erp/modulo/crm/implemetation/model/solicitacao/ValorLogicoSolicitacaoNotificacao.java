package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.ValorLogicoSolicitacao;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.ValoresLogicosSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.notificacao.NotificacaoSB;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.notificacaosb.CPNotificacaoSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import javax.persistence.EntityManager;

@ValorLogicoSolicitacao(calculo = ValoresLogicosSolicitacao.NOTIFICACAO)
public class ValorLogicoSolicitacaoNotificacao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoNotificacao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            ConsultaDinamicaDeEntidade novaCOnsulta = new ConsultaDinamicaDeEntidade(NotificacaoSB.class, em);
            novaCOnsulta.addcondicaoCampoIgualA(CPNotificacaoSB.codigoselocomunicacao, getSolicitacao().getCodigoSelo());
            List<NotificacaoSB> notificacoes = novaCOnsulta.gerarResultados();
            if (!notificacoes.isEmpty()) {
                getSolicitacao().setNotificacao(notificacoes.get(0));
                getSolicitacao().getNotificacao().getDisparos().size();
            }
        } finally {

        }

        return getSolicitacao().getNotificacao();
    }

    public Solicitacao getSolicitacao() {
        return (Solicitacao) getCampoInst().getObjetoRaizDoAtributo();
    }
}
