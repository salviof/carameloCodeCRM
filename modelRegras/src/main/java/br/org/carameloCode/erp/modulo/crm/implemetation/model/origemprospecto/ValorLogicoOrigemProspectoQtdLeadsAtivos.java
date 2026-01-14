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

@ValorLogicoOrigemProspecto(calculo = ValoresLogicosOrigemProspecto.QTDLEADSATIVOS)
public class ValorLogicoOrigemProspectoQtdLeadsAtivos
        extends
        ValorLogicoCalculoGenerico {

    boolean quantidadeDefinida;

    public ValorLogicoOrigemProspectoQtdLeadsAtivos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (!quantidadeDefinida) {
            EntityManager emTotalOrigens = UtilSBPersistencia.getEMDoContexto();
            ConsultaDinamicaDeEntidade consutla = new ConsultaDinamicaDeEntidade(Pessoa.class, emTotalOrigens).addCondicaoManyToOneIgualA(getOrigem());
            consutla.addCondicaoPositivo("ativo");
            long quantidade = consutla.resultadoSomarQuantidade();
            setValorPorReflexao(quantidade);
            quantidadeDefinida = true;
        }

        return getOrigem().getQtdLeadsAtivos();
    }

    public OrigemProspecto getOrigem() {
        return (OrigemProspecto) getCampoInst().getObjetoDoAtributo();
    }
}
