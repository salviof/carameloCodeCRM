package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoAcessoCard;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.CPSolicitacao;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.USUARIOLOGADOSOLICITOUACESSO)
public class ValorLogicoPessoaUsuarioLogadoSolicitouAcesso
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaUsuarioLogadoSolicitouAcesso(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean valorObtido;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!valorObtido) {
            EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
            try {
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(SolicitacaoAcessoCard.class, em);
                consulta.addCondicaoNegativo(CPSolicitacao.foifinalizada);
                consulta.addCondicaoManyToOneIgualA(CPSolicitacao.pessoa, getPessoa());
                consulta.addCondicaoManyToOneIgualA(CPSolicitacao.usuariosolicitante, SBCore.getUsuarioLogado());
                getPessoa().setUsuarioLogadoSolicitouAcesso(!consulta.resultadoRegistros().isEmpty());

            } catch (Throwable t) {
                UtilSBPersistencia.fecharEM(em);
            }
        }
        return getPessoa().isUsuarioLogadoTemAcesso();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }

}
