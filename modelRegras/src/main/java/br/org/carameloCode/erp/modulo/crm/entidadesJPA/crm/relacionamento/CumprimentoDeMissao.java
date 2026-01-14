/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import java.util.Date;

/**
 *
 * @author sfurbino
 */
public class CumprimentoDeMissao {

    private Long id;
    private UsuarioSB usuario;
    private Date datahora;
    private AtividadeCRM atividadeRelacionada;

}
