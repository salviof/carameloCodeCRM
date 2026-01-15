/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListasObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvio
 */
public class ValorLogicoDDChamadoAbertoCodigo extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDDChamadoAbertoCodigo(ItfCampoInstanciado pCampo) {
        super(pCampo);
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

            getDadoDinamico().setValorEmpacotado(String.valueOf(chamadosAtivos.get(0).getId()));
        }
        return getDadoDinamico().getValorEnpacotado();
    }

    public DadoCRM getDadoDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }
}
