/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author salvio
 */
public interface ComoLead extends ComoEntidadeSimples {

    public String getDocumento();

    public String getEmail();

    public Long getId();

    public ItfMetaLead getMeta();

    public String getNome();

    public String getTelefonePrincipal();

    public String getTipoPessoa();

    public ComoUsuario getUsuarioEditou();

}
