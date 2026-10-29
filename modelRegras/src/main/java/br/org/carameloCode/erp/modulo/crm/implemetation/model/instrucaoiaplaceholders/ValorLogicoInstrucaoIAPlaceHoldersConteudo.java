package br.org.carameloCode.erp.modulo.crm.implemetation.model.instrucaoiaplaceholders;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt.UtilMauticSinc;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.instrucaoiaplaceholders.ValorLogicoInstrucaoIAPlaceHolders;
import br.org.carameloCode.erp.modulo.crm.api.model.instrucaoiaplaceholders.ValoresLogicosInstrucaoIAPlaceHolders;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIAPlaceHolders;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoInstrucaoIAPlaceHolders(calculo = ValoresLogicosInstrucaoIAPlaceHolders.CONTEUDO)
public class ValorLogicoInstrucaoIAPlaceHoldersConteudo
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoInstrucaoIAPlaceHoldersConteudo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (!isCacheAtivado()) {
            StringBuilder conteudo = new StringBuilder();
            conteudo.append("Utilize SOMENTE ESSES  placeholders para referir aos dados do usuário do modelo, não utilize fisrtname direto,"
                    + " os placeholders que acessam os dados do contato são apenas esses,"
                    + " e serão transcripts ao serem enviados via api do mautic ou outras plataformas"
                    + " [contatoPrincipal.] são referente ao contato, [dados.] são dados extras , e direto como [nome] são referente ao nome da empresa cliente");
            UtilMauticSinc.getPlaceHolders().getpalavrasChave().stream().
                    map(cont -> "," + cont).
                    forEach(conteudo::append);
            getInstrucaoIAPlaceHolders().setConteudo(conteudo.toString());
            setCacheSegundosPadrao(999999999);
        }
        return getInstrucaoIAPlaceHolders().getConteudo();
    }

    public InstrucaoIAPlaceHolders getInstrucaoIAPlaceHolders() {
        return (InstrucaoIAPlaceHolders) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
