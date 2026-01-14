/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoLinkIntegracao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.integracao.IntegracaoManual;

/**
 *
 * @author salvio
 */
public abstract class ValorLogicoLinkPadrao extends
        ValorLogicoCalculoGenerico {

    private final String NOME_INTEGRACAO;
    boolean valorDefinido = false;

    public ValorLogicoLinkPadrao(String pNomeIntegracao, ItfCampoInstanciado pCampo) {
        super(pCampo);
        this.NOME_INTEGRACAO = pNomeIntegracao
                + getDadoLinkIntDinamico().getTipoDadoCRM().getId();
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getDadoLinkIntDinamico().getProspectoEmpresa() == null) {
            return getDadoLinkIntDinamico().getValorEnpacotado();
        }
        if (!valorDefinido) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            try {
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(IntegracaoManual.class, em);
                consulta.addcondicaoCampoIgualA("nomeIntegracao", NOME_INTEGRACAO);
                consulta.addcondicaoCampoIgualA("codigoPessoa", getDadoLinkIntDinamico().getProspectoEmpresa().getId());
                List<IntegracaoManual> resultado = consulta.resultadoRegistros();
                if (!resultado.isEmpty()) {
                    getDadoLinkIntDinamico().setValorEmpacotado(resultado.get(0).getValor());
                }
            } finally {
                UtilSBPersistencia.fecharEM(em);
                valorDefinido = true;
            }
        }

        return getDadoLinkIntDinamico().getValorEnpacotado();
    }

    public final DadoCRM getDadoLinkIntDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }

};
