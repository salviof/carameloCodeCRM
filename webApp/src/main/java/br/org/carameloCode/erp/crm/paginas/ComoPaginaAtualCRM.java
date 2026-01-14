/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas;

import br.org.carameloCode.erp.crm.paginas.ItfPaginaComGestaoChamado;
import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalEmail;
import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalProspecto;
import br.org.carameloCode.erp.crm.paginas.crmAgenda.ItfPaginaListaDeHorariosDisponiveis;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;

/**
 *
 * @author salvio
 */
public interface ComoPaginaAtualCRM {

    public ItfPaginaListaDeHorariosDisponiveis getComoPaginaComHorarioDisponivel();

    public ItfPaginaComModalProspecto getComoPaginaComModal();

    public ItfPaginaComModalEmail getComoPaginaComModalEmail();

    public ItfPaginaComGestaoChamado getComoPaginaGestaoChamado();

    public UsuarioCRM getUsuarioCrmLogado();

}
