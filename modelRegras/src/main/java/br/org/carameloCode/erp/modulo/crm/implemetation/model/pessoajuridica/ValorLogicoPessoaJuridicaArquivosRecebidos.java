package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.ARQUIVOSRECEBIDOS)
public class ValorLogicoPessoaJuridicaArquivosRecebidos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaJuridicaArquivosRecebidos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        try {
            EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
            //  ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ArquivoAnexadoEmailEmConteudo.class, em)
            //        .addCondicaoManyToOneIgualA(getProspecto());
            //   getProspecto().setArquivosRecebidos(consulta.gerarResultados());
        } catch (Throwable t) {

        }

        return null;//getProspecto().getArquivosRecebidos();
    }

    public PessoaJuridica getProspecto() {
        return (PessoaJuridica) getCampoInst().getObjetoDoAtributo();
    }
}
