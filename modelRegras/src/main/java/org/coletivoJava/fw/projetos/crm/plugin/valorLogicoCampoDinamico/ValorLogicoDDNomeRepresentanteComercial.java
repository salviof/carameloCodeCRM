package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;

/**
 *
 * @author salvio
 */
public class ValorLogicoDDNomeRepresentanteComercial extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDDNomeRepresentanteComercial(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getDadoDinamico().getProspectoEmpresa() != null) {
            if (!isCacheAtivado()) {
                EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
                try {
                    Pessoa pessoa = UtilSBPersistencia.loadEntidade(getDadoDinamico().getProspectoEmpresa(), em);
                    if (pessoa.getCPinst(CPPessoa.usuarioresponsavel).getValor() != null) {
                        getDadoDinamico().setValorEmpacotado(pessoa.getUsuarioResponsavel().getNome());
                    }
                } finally {
                    UtilSBPersistencia.fecharEM(em);
                    setCacheSegundosPadrao(30);
                }
            }
        }

        return getDadoDinamico().getValorEnpacotado();
    }

    public DadoCRM getDadoDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }
}
