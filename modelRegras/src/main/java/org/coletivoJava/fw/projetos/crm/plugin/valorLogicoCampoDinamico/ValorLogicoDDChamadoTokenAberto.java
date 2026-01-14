/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListasObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvio
 */
public class ValorLogicoDDChamadoTokenAberto extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDDChamadoTokenAberto(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public String getTokenFinalURL(ChamadoCliente pChamado) {

        TokenAcessoDinamico token
                = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR, pChamado, pChamado.getUsuarioCliente().getEmail());

        //  String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(SBCore.getServicoVisualizacao().getFORMULARIO_TOKEN_GENERICO(),
        //        token);
        // url = url.replace("https://crm.", "https://atendimento.");
        String tokenFInalURL = token.getCodigo() + ".html";
        return tokenFInalURL;
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getDadoDinamico().setValorEmpacotado(null);
        List<ChamadoCliente> chamadosAtivos = new ArrayList<>();
        getDadoDinamico().getProspectoEmpresa().getChamadosAbertos()
                .stream().filter(rs -> rs.getStatus().equals(FabStatusChamado.EM_ATENDIMENTO.getRegistro()))
                .forEach(chamadosAtivos::add);
        if (!chamadosAtivos.isEmpty()) {
            UtilCRCListasObjeto.ordernarPorCampoReverso(chamadosAtivos, "dataHoraCriacao");
            String fimUrl = getTokenFinalURL(chamadosAtivos.get(0));
            getDadoDinamico().setValorEmpacotado(fimUrl);
        }
        return getDadoDinamico().getValorEnpacotado();
    }

    public DadoCRM getDadoDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }
}
