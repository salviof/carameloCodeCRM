package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.QUANTIDADELEADSURGENTES)
public class ValorLogicoPesquisaLeadQuantidadeLeadsUrgentes
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadQuantidadeLeadsUrgentes(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private UsuarioCRM ultimoUsuarioDisponivel = null;

    @Override
    public synchronized Object getValor(Object... pEntidade) {
        if (ultimoUsuarioDisponivel == null || !ultimoUsuarioDisponivel.equals(getPesquisaLead().getUsuario())) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            ConsultaDinamicaDeEntidade consultaPesquisa = new ConsultaDinamicaDeEntidade(PessoaJuridica.class, em);

            consultaPesquisa.addCondicaoManyToManyContendoObjeto("usuariosResponsaveis", getPesquisaLead().getUsuario());
            consultaPesquisa.addCondicaoPositivo(CPPessoa.possuidemandaurgencia);
            getPesquisaLead().setQuantidadeLeadsUrgentes(new Long(consultaPesquisa.gerarResultadoSomarQuantidade()).intValue());

            ultimoUsuarioDisponivel = getPesquisaLead().getUsuario();
            UtilSBPersistencia.fecharEM(em);
        }
        return getPesquisaLead().getQuantidadeLeadsUrgentes();
    }

    public PesquisaLead getPesquisaLead() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }
}
