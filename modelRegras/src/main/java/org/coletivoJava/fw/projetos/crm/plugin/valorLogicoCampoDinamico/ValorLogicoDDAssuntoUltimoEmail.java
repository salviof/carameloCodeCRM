/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListasObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class ValorLogicoDDAssuntoUltimoEmail
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDDAssuntoUltimoEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getDadoDinamico().getProspectoEmpresa() == null || getDadoDinamico().getProspectoEmpresa().getContatoPrincipal() == null) {
            return null;
        }
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            if (!isCacheAtivado()) {
                ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(EnvioEmail.class, em);

                novaConsulta.addCondicaoManyToManyContendoObjeto("contatos", getDadoDinamico().getProspectoEmpresa().getContatoPrincipal());
                List<EnvioEmail> emails = novaConsulta.resultadoRegistros();
                if (!emails.isEmpty()) {
                    UtilCRCListasObjeto.ordernarPorCampoReverso(emails, "datadisparo");
                    String asssunto = emails.get(0).getAssunto();
                    getDadoDinamico().setValorEmpacotado(asssunto);
                }
                setCacheSegundosPadrao(30);
            }
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

        return getDadoDinamico().getValorEnpacotado();
    }

    public DadoCRM getDadoDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }

}
