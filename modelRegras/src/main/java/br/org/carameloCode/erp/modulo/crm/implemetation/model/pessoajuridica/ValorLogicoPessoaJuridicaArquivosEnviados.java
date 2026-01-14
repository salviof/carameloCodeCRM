package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.ARQUIVOSENVIADOS)
public class ValorLogicoPessoaJuridicaArquivosEnviados
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaJuridicaArquivosEnviados(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        //ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ArquivoAnexado.class,
        //      UtilSBPersistencia.getEMDoContexto())
        //    .addCondicaoManyToOneIgualA(getProspecto());
        return null;//getProspecto().getArquivosEnviados();
    }

    public PessoaJuridica getProspecto() {
        return (PessoaJuridica) getCampoInst().getObjetoDoAtributo();
    }
}
