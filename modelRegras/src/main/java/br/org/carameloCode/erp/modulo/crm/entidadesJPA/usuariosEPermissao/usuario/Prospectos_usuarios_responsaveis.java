/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMGenerica;
import javax.persistence.ManyToOne;

/**
 *
 * @author salviofurbino
 * @since 15/04/2019
 * @version 1.0
 */
//@Entity(name = "prospectos_usuarios_responsaveis")
public class Prospectos_usuarios_responsaveis extends EntidadeORMGenerica {

    private Long id;

    @ManyToOne(targetEntity = UsuarioCRM.class)
    private UsuarioCRM usuarioCRM;
    @ManyToOne(targetEntity = Pessoa.class)
    private Pessoa prospecto;

}
