package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
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

    UsuarioCRM ultimoHorarioDefinido = null;

    @Override
    public synchronized Object getValor(Object... pEntidade) {
        if (ultimoHorarioDefinido == null || !ultimoHorarioDefinido.equals(getPesquisaLead().getUsuario())) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            ConsultaDinamicaDeEntidade consultaPesquisa = new ConsultaDinamicaDeEntidade(PessoaJuridica.class, em);

            consultaPesquisa.addCondicaoManyToManyContendoObjeto("usuariosResponsaveis", getPesquisaLead().getUsuario());
            List<PessoaJuridica> listaCompleta = consultaPesquisa.gerarResultados();
            List<PessoaJuridica> listaComFiltro = new ArrayList<>();
            listaCompleta.stream().filter(cp -> cp.getCampoInstanciadoByNomeOuAnotacao(CPPessoa.possuidemandaurgencia).getValorComoBoolean())
                    .forEach(listaComFiltro::add);
            getPesquisaLead().setQuantidadeLeadsUrgentes(listaComFiltro.size());
            ultimoHorarioDefinido = getPesquisaLead().getUsuario();
            UtilSBPersistencia.fecharEM(em);
        }
        return getPesquisaLead().getQuantidadeLeadsUrgentes();
    }

    public PesquisaLead getPesquisaLead() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }
}
