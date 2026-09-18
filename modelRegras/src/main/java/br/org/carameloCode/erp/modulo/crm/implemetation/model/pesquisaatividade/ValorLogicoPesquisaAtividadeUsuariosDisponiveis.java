package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValorLogicoPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValoresLogicosPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariosb.CPUsuarioSB;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import javax.persistence.EntityManager;

@ValorLogicoPesquisaAtividade(calculo = ValoresLogicosPesquisaAtividade.USUARIOSDISPONIVEIS)
public class ValorLogicoPesquisaAtividadeUsuariosDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaAtividadeUsuariosDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (!getPesquisa().getUsuariosDisponiveis().isEmpty()) {
            EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
            try {
                ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(UsuarioCRM.class, em);
                novaConsulta.addCondicaoPositivo(CPUsuarioSB.ativo);

                getPesquisa().setUsuariosDisponiveis(novaConsulta.gerarResultados());
            } finally {
                UtilSBPersistencia.fecharEM(em);
            }
        }
        return getPesquisa().getUsuariosDisponiveis();

    }

    public PesquisaAtividade getPesquisa() {
        return (PesquisaAtividade) getCampoInst().getObjetoRaizDoAtributo();

    }

}
