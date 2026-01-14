/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;

/**
 *
 * @author salvio
 */
public class ValorLogicoDDTokenAreadoCliente extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDDTokenAreadoCliente(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public Object getValor(Object... pEntidade) {

        ContatoProspecto contato = (ContatoProspecto) getDadoDinamico().getProspectoEmpresa().getCPinst("contatoPrincipal").getValorComoEntidadeSimples();
        if (contato == null) {
            getDadoDinamico().setValorEmpacotado(null);
            return null;
        }
        TokenAcessoDinamico token = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.RESERVAS_MB_GESTAO,
                null, contato.getEmail());

        String tokenFInalURL = token.getCodigo() + ".html";
        return tokenFInalURL;

    }

    public DadoCRM getDadoDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }

}
