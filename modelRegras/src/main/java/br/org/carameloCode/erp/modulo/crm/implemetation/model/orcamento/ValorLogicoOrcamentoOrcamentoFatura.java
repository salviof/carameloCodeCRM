package br.org.carameloCode.erp.modulo.crm.implemetation.model.orcamento;

import br.org.coletivoJava.fw.api.erp.erpintegracao.contextos.ERPIntegracaoSistemasApi;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import br.org.coletivoJava.integracoes.restInterprestfull.api.FabIntApiRestIntegracaoERPRestfull;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.ValorLogicoOrcamento;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.ValoresLogicosOrcamento;

@ValorLogicoOrcamento(calculo = ValoresLogicosOrcamento.ORCAMENTOFATURA)
public class ValorLogicoOrcamentoOrcamentoFatura
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoOrcamentoOrcamentoFatura(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (false) {
            ERPIntegracaoSistemasApi.RESTFUL.getImplementacaoDoContexto();
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            List<SistemaERPConfiavel> sistemas = UtilSBPersistencia.getListaTodos(SistemaERPConfiavel.class, em);
            SistemaERPConfiavel sistema = sistemas.stream().filter(st -> st.getDominio().startsWith("fatura")).findFirst().get();

            String jsonOrcamento = "";
            ItfAcaoApiRest gestaoToken = FabIntApiRestIntegracaoERPRestfull.ACOES_EXECUTAR_ATUALIZAR_ENTIDADE.getAcao(SBCore.getUsuarioLogado(), sistema.getDominio(),
                    getOrcamento().getId(), jsonOrcamento);
            ItfResposta resposta = gestaoToken.getResposta();
            if (resposta.isSucesso()) {

            }
        }

        return null;
    }

    public Orcamento getOrcamento() {
        return (Orcamento) getCampoInst().getObjetoDoAtributo();
    }
}
