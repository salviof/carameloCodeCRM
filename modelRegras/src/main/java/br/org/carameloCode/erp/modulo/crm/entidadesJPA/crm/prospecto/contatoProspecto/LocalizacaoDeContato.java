/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.LocalizacaoPostavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "endereço de contato", plural = "Endereços de contatos")
public class LocalizacaoDeContato extends LocalizacaoPostavel {

}
