package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrmlinkintegracao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao.ValidadorTipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao.ValidadoresTipoDadoCrmLinkIntegracao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

@ValidadorTipoDadoCrmLinkIntegracao(validador = ValidadoresTipoDadoCrmLinkIntegracao.NOMECLASSELOGICA)
public class ValidacaoTipoDadoCrmLinkIntegracaoNomeClasseLogica extends ValidacaoGenerica<TipoDadoCrmLinkIntegracao> {

    public ValidacaoTipoDadoCrmLinkIntegracaoNomeClasseLogica(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        String pNome = (String) pValor;

        String enderecoClasse = "org.coletivoJava.fw.projetos.crm.plugin.valorLogicoLinkIntegracao.ValorLogicoLink" + pNome;
        try {
            SBCore.getClasseLoaderAplicacao().loadClass(enderecoClasse);
        } catch (ClassNotFoundException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "falha procurando pela classe de implementação " + enderecoClasse, ex);
            throw new ErroValidacao("A implementação para " + pNome + " não foi encontrada");
        }
        getTipoDadoCrmLinkIntegracao().setValorPadrao(enderecoClasse + ".class");

        return new ArrayList();
    }

    public TipoDadoCrmLinkIntegracao getTipoDadoCrmLinkIntegracao() {
        return getObjetoDoAtributo();
    }
}
