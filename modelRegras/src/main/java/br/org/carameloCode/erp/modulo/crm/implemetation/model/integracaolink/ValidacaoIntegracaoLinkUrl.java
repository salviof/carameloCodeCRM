package br.org.carameloCode.erp.modulo.crm.implemetation.model.integracaolink;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.IntegracaoLink;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.integracaolink.ValidadorIntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.api.model.integracaolink.ValidadoresIntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.integracao.IntegracaoManual;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

@ValidadorIntegracaoLink(validador = ValidadoresIntegracaoLink.URL)
public class ValidacaoIntegracaoLinkUrl extends ValidacaoGenerica<IntegracaoLink> {

    public ValidacaoIntegracaoLinkUrl(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (UtilCRCStringValidador.isNuloOuEmbranco(pValor)) {
            throw new ErroValidacao("não pode ser nulo");
        }
        String url = (String) pValor;
        if (!url.startsWith("https://")) {
            throw new ErroValidacao("url invalida");
        }
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            String NOMEINTEGRACAO = getIntegracaoLink().getTipoDado().getNomeClasseLogica() + getIntegracaoLink().getTipoDado().getId();
            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(IntegracaoManual.class, em);
            consulta.addcondicaoCampoIgualA("nomeIntegracao", NOMEINTEGRACAO);
            consulta.addcondicaoCampoIgualA("codigoPessoa", getIntegracaoLink().getCodigoPessoa());
            List<IntegracaoManual> integfracoes = consulta.resultadoRegistros();
            if (integfracoes.isEmpty()) {
                IntegracaoManual novaIntegracao = new IntegracaoManual();
                novaIntegracao.setNome(getIntegracaoLink().getTipoDado().getNome());
                novaIntegracao.setNomeIntegracao(NOMEINTEGRACAO);
                novaIntegracao.setCodigoPessoa(getIntegracaoLink().getCodigoPessoa());
                novaIntegracao.setValor(url);
                novaIntegracao = UtilSBPersistencia.mergeRegistro(novaIntegracao);
            } else {
                integfracoes.get(0).setValor(url);
                UtilSBPersistencia.mergeRegistro(integfracoes.get(0));
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando consulta" + t.getMessage(), t);
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return new ArrayList();
    }

    public IntegracaoLink getIntegracaoLink() {
        return getObjetoDoAtributo();
    }
}
