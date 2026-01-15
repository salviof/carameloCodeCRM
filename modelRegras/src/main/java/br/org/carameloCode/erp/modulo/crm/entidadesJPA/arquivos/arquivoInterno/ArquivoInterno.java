/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoInterno;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(plural = "Arquivos internos", tags = "Arquivos Internos", modulo = ERPCrm.NOME_MODULO_ERP)
public class ArquivoInterno extends ArquivoAnexado {

}
