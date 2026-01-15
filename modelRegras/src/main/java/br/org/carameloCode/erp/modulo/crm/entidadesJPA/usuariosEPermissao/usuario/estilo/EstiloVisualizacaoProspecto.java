/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estilo;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import java.util.Date;

/**
 *
 * @author salviofurbino
 * @since 09/09/2019
 * @version 1.0
 */
@InfoObjetoSB(tags = {"Estilo Visualização"}, plural = "Estilos")
public class EstiloVisualizacaoProspecto {

    private final Pessoa prosp;
    private String estiloCSS;

    public EstiloVisualizacaoProspecto(Pessoa prosp) {
        this.prosp = prosp;
        if (prosp == null) {
            throw new UnsupportedOperationException("Erro o prospecto enviado para estilo visualização é nulo");
        }
    }

    public String getEstiloCSS() {
        try {
            if (prosp == null) {
                return "prospectoPadrao";
            }
            if (UtilCRCStringValidador.isNuloOuEmbranco(estiloCSS)) {
                if (prosp.getRelacionamento().getTempoAceitavelResolucao() != 0) {
                    long horaspassadas = UtilCRCDataHora.intervaloTempoHoras((Date) prosp.getCPinst("dataHoraMudancaRelacionamento").getValor(), new Date());
                    if (horaspassadas >= prosp.getRelacionamento().getTempoAceitavelResolucao()) {
                        estiloCSS = "prospectoAtividadeAtrasada";
                    }
                } else {
                    estiloCSS = "prospectoPadrao";
                }
            }
            return estiloCSS;
        } catch (Throwable t) {
            return "prospectoPadrao";
        }
    }

}
