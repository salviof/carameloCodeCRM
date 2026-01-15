package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValorLogicoContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValoresLogicosContatoProspecto;

@ValorLogicoContatoProspecto(calculo = ValoresLogicosContatoProspecto.PROSPECTOSDISPONIVEIS)
public class ValorLogicoContatoProspectoProspectosDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoContatoProspectoProspectosDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);

    }

    @Override
    public Object getValor(Object... pEntidade) {

        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        List<PessoaJuridica> todos = UtilSBPersistencia.getListaTodos(PessoaJuridica.class, em);
        setValorPorReflexao(todos);
        UtilSBPersistencia.fecharEM(em);
        return ((ContatoProspecto) getCampoInst().getObjetoDoAtributo()).getProspectosDisponiveis();
    }

}
