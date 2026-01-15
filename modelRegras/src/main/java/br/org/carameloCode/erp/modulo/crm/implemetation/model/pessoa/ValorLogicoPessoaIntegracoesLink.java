package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.IntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.INTEGRACOESLINK)
public class ValorLogicoPessoaIntegracoesLink
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaIntegracoesLink(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean foiDefinidoUmValor;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!foiDefinidoUmValor) {
            List<IntegracaoLink> integracoes = new ArrayList();
            EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
            try {

                List<TipoDadoCrmLinkIntegracao> tipoLinksIntegracao = UtilSBPersistencia.getListaTodos(TipoDadoCrmLinkIntegracao.class, em);
                for (TipoDadoCrmLinkIntegracao tipoLinkIntegracao : tipoLinksIntegracao) {
                    DadoCRM dadoPessoa = new DadoCRM(getPessoa());
                    dadoPessoa.setTipoDadoCRM(tipoLinkIntegracao);
                    String url = dadoPessoa.getValor();

                    IntegracaoLink integracador = new IntegracaoLink();
                    integracador.setImagem(tipoLinkIntegracao.getImgPequena());
                    //integracador.setUrl(url);
                    integracador.setTipoDado(tipoLinkIntegracao);
                    integracador.setCodigoPessoa(getPessoa().getId());
                    integracador.setNome(tipoLinkIntegracao.getNome());
                    integracador.setUrl((String) integracador.getCPinst("url").getValor());
                    integracoes.add(integracador);

                }

            } finally {
                getPessoa().setIntegracoesLink(integracoes);
                UtilSBPersistencia.fecharEM(em);
                foiDefinidoUmValor = true;
            }
        }

        return getPessoa().getIntegracoesLink();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
