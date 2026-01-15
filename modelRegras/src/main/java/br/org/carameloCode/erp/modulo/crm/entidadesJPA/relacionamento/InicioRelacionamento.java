/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import java.util.Date;

/**
 *
 * @author sfurbino
 */
public class InicioRelacionamento {

    private TipoRelacionamento pTipo;
    private Pessoa empresa;
    private Date dataHora;
    private UsuarioSB usuarioResponsavel;

}
