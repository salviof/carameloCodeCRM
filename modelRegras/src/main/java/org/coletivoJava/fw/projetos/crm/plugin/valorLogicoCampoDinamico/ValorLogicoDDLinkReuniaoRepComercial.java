package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author salvio
 */
public class ValorLogicoDDLinkReuniaoRepComercial
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDDLinkReuniaoRepComercial(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public Object getValor(Object... pEntidade) {
        if (getDadoDinamico().getProspectoEmpresa() == null) {
            return null;
        }
        if (getDadoDinamico().getProspectoEmpresa().getContatoPrincipal() == null) {
            return null;
        }
        if (getDadoDinamico().getProspectoEmpresa().getContatoPrincipal().getCelular() == null) {
            return null;
        }

        ContatoProspecto contato = getDadoDinamico().getProspectoEmpresa().getContatoPrincipal();
        TokenAcessoDinamico token = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO,
                contato, contato.getEmail());

        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB, token);
        url = url.replace("crm.", "atendimento.");
        getDadoDinamico().setValorEmpacotado(url);
        return url;

    }

    public DadoCRM getDadoDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }
}
