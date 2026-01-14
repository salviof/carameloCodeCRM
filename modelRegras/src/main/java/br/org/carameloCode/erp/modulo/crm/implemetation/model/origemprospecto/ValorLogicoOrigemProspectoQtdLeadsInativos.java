package br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospecto.ValorLogicoOrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospecto.ValoresLogicosOrigemProspecto;

@ValorLogicoOrigemProspecto(calculo = ValoresLogicosOrigemProspecto.QTDLEADSINATIVOS)
public class ValorLogicoOrigemProspectoQtdLeadsInativos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoOrigemProspectoQtdLeadsInativos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    boolean quantidadeDefinida;

    @Override
    public Object getValor(Object... pEntidade) {

        if (!quantidadeDefinida) {
            EntityManager emTotalOrigens = UtilSBPersistencia.getEMDoContexto();
            ConsultaDinamicaDeEntidade consutla = new ConsultaDinamicaDeEntidade(Pessoa.class, emTotalOrigens).addCondicaoManyToOneIgualA(getOrigem());
            consutla.addCondicaoNegativo("ativo");
            long quantidade = consutla.resultadoSomarQuantidade();
            setValorPorReflexao(quantidade);
            quantidadeDefinida = true;
        }

        return getOrigem().getQtdLeadsInativos();
    }

    public OrigemProspecto getOrigem() {
        return (OrigemProspecto) getCampoInst().getObjetoDoAtributo();
    }
}
