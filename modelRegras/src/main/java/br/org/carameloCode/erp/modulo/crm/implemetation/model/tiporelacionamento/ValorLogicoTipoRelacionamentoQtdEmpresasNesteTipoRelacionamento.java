package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiporelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.ValorLogicoTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.ValoresLogicosTipoRelacionamento;

@ValorLogicoTipoRelacionamento(calculo = ValoresLogicosTipoRelacionamento.QTDEMPRESASNESTETIPORELACIONAMENTO)
public class ValorLogicoTipoRelacionamentoQtdEmpresasNesteTipoRelacionamento
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoRelacionamentoQtdEmpresasNesteTipoRelacionamento(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    boolean pesquisaRealizada = false;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!pesquisaRealizada) {
            ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(PessoaJuridica.class, UtilSBPersistencia.getEMDoContexto());
            novaConsulta.addCondicaoManyToOneIgualA(CPPessoa.relacionamento, getTipoRelacionamento());
            getTipoRelacionamento().setQtdEmpresasNesteTipoRelacionamento(new Long(novaConsulta.resultadoSomarQuantidade()).intValue());

        }
        return getTipoRelacionamento().getQtdEmpresasNesteTipoRelacionamento();
    }

    public TipoRelacionamento getTipoRelacionamento() {
        return (TipoRelacionamento) getCampoInst().getObjetoDoAtributo();
    }
}
