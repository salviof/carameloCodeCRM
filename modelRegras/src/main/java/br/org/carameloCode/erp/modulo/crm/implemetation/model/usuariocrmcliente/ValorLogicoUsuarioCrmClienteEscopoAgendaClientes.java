package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrmcliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.CPUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValorLogicoUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValoresLogicosUsuarioCrmCliente;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValorLogicoUsuarioCrmCliente(calculo = ValoresLogicosUsuarioCrmCliente.ESCOPOAGENDACLIENTES)
public class ValorLogicoUsuarioCrmClienteEscopoAgendaClientes
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCrmClienteEscopoAgendaClientes(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (!isCacheAtivado()) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            try {
                UsuarioCrmCliente usuario = UtilSBPersistencia.loadEntidade(getUsuarioCliente(), em);

                Pessoa pessoa = (Pessoa) usuario.getRepresentanteLegal();
                if (pessoa != null) {
                    if (pessoa.getUsuarioResponsavel() != null) {
                        EscopoPesquisaMelhorHorario escopo = (EscopoPesquisaMelhorHorario) pessoa.getUsuarioResponsavel().getCampoInstanciadoByNomeOuAnotacao(CPUsuarioCRM.escopoagendaclientes).getValor();
                        getUsuarioCliente().setEscopoAgendaClientes(escopo);

                    }
                }
            } finally {
                UtilSBPersistencia.fecharEM(em);
                setCacheSegundosPadrao(10);
            }
        }
        return getUsuarioCliente().getEscopoAgendaClientes();
    }

    public UsuarioCrmCliente getUsuarioCliente() {
        return (UsuarioCrmCliente) getCampoInst().getObjetoDoAtributo();
    }
}
