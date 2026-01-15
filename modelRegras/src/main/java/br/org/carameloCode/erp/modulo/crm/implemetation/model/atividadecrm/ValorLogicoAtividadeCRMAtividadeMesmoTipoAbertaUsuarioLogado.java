package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.FabStatusAtividade;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.ATIVIDADEMESMOTIPOABERTAUSUARIOLOGADO)
public class ValorLogicoAtividadeCRMAtividadeMesmoTipoAbertaUsuarioLogado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMAtividadeMesmoTipoAbertaUsuarioLogado(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        AtividadeCRM atividade = getAtiviade();
        //Object[] parametros = {getAtiviade(), atividade.getProspectoEmpresa(), SBCore.getUsuarioLogado(), atividade.getTipoAtividade(), FabStatusAtividade.EM_ANDAMENTO.getRegistro()};
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(AtividadeCRM.class, em)
                    .addCondicaoManyToOneIgualA(CPAtividadeCRM.statusatividade, FabStatusAtividade.EM_ANDAMENTO.getRegistro())
                    .addCondicaoManyToManyContendoObjeto("usuarioAtividade", SBCore.getUsuarioLogado())
                    .addCondicaoManyToOneIgualA(CPAtividadeCRM.tipoatividade, atividade.getTipoAtividade());

            List<AtividadeCRM> atividadesEncontradas = (List) consulta.resultadoRegistros();
            if (atividadesEncontradas == null || atividadesEncontradas.isEmpty()) {
                getAtiviade().setAtividadeMesmoTipoAbertaUsuarioLogado(null);
            } else {
                AtividadeCRM atividadeEncontrada = atividadesEncontradas.get(0);
                if (atividadeEncontrada.getId() == atividade.getId()) {
                    getAtiviade().setAtividadeMesmoTipoAbertaUsuarioLogado(null);
                } else {
                    getAtiviade().setAtividadeMesmoTipoAbertaUsuarioLogado(atividadeEncontrada);
                }
            }
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

        return getAtiviade().getAtividadeMesmoTipoAbertaUsuarioLogado();

    }

    public AtividadeCRM getAtiviade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
