package org.coletivoJava.fw.projetos.integracao.implemetation.model.conversao.mktFaturamento;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.conversor.ConversorERPResfullToJsonAbs;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfSistemaERP;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.CPOrcamento;
import br.org.carameloCode.erp.modulo.crm.api.model.servicooferecido.CPServicoOferecido;

public class OrcamentoToJson extends ConversorERPResfullToJsonAbs
        implements
        com.super_bits.modulosSB.SBCore.modulos.erp.conversao.ItfConversorERRestfullToJson {

    private final SistemaERPConfiavel sistema;

    public OrcamentoToJson(ItfSistemaERP pSistema) {
        sistema = (SistemaERPConfiavel) pSistema;
    }

    @Override
    public JsonObject getJson(ComoEntidadeSimples pBeanSimples) {

        adicionarMapeamentoCampo("id", "@id");
        adicionarMapeamentoCampo(CPOrcamento.pessoa, "cliente");
        adicionarMapeamentoCampo(CPOrcamento.itenssazonais + "[]." + CPServicoOferecido.valorsetup, "itens[].valorNegociado");
        adicionarMapeamentoCampo(CPOrcamento.itenssazonais + "[]." + CPServicoOferecido.tiposervico, "itens[].tipoServico");
        adicionarMapeamentoCampo(CPOrcamento.itemrecorrente + "[]." + CPServicoOferecido.valormensal, "itensRecorrentes[].valorNegociado");
        adicionarMapeamentoCampo(CPOrcamento.itemrecorrente + "[]." + CPServicoOferecido.tiposervico, "itensRecorrentes[].tipoServico");
        JsonObjectBuilder builder = buildObjeto(getMapeamentoTodosCapos(), pBeanSimples);
        return builder.build();
    }

    @Override
    public ItfSistemaERP getSistemaRemoto() {
        return sistema;
    }

}
