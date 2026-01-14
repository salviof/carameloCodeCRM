package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import com.google.common.collect.Lists;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariosb.CPUsuarioSB;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.USUARIOSDISPONIVEIS)
public class ValorLogicoPesquisaLeadUsuariosDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadUsuariosDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        boolean gerenteAdministrativo = false;
        if (SBCore.getServicoSessao().getSessaoAtual().isIdentificado()) {
            if (SBCore.getServicoSessao().getSessaoAtual().getUsuario().getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                gerenteAdministrativo = true;
            }
        }
        List<UsuarioCRM> usuarios = null;
        if (gerenteAdministrativo) {
            ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(UsuarioCRM.class, UtilSBPersistencia.getEMDoContexto());
            novaConsulta.addcondicaoCampoIgualA("tipoUsuario", UsuarioCRM.class.getSimpleName());
            novaConsulta.addCondicaoPositivo(CPUsuarioSB.ativo);
            usuarios = novaConsulta.resultadoRegistros();
        } else {
            usuarios = Lists.newArrayList((UsuarioCRM) SBCore.getUsuarioLogado());
        }
        getPesquisa().setUsuariosDisponiveis(usuarios);
        return getPesquisa().getUsuariosDisponiveis();
    }

    public PesquisaLead getPesquisa() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }
}
