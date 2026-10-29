package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.instrucaoiaplaceholders;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt.UtilMauticSinc;
import br.org.carameloCode.erp.modulo.ia.implemetation.model.instrucaoiaplaceholders.ValorLogicoInstrucaoIAPlaceHoldersConteudo;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.instrucaoiaplaceholders.ValorLogicoInstrucaoIAPlaceHolders;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.instrucaoiaplaceholders.ValoresLogicosInstrucaoIAPlaceHolders;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIAPlaceHolders;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoInstrucaoIAPlaceHolders(calculo = ValoresLogicosInstrucaoIAPlaceHolders.CONTEUDO)
public class ValorLogicoExtErpcaramInstrucaoIAPlaceHoldersConteudo
        extends
        ValorLogicoInstrucaoIAPlaceHoldersConteudo {

    public ValorLogicoExtErpcaramInstrucaoIAPlaceHoldersConteudo(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }

    @Override
    protected MapaSubstituicao getPlaceHolders() {
        return UtilMauticSinc.getPlaceHolders();
    }

}
