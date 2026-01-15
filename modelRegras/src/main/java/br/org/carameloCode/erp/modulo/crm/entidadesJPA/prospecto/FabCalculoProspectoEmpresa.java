/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculos;

/**
 *
 * @author SalvioF
 */
public enum FabCalculoProspectoEmpresa implements ItfCalculos {

    NOME_RESPONSAVEL,
    TELEFONE_PRINCIPAL,
    EMAIL_PRINCIPAL,
    CONTATO_PRINCIPAL,
    USUARIO_LOGADO_AUTORIZADO,
    CONTATO_INICIAL_CADASTRADO,
    QUANTIDADE_ATIVIDADES_NAO_FINALIZADAS,
    QUANTIDADE_ATIVIDAES_NAO_FINALIZADAS_POR_TIPO,
    QUANTIDADE_ATIVIDADE_NAO_FINALIZADAS_POR_TIPO_E_USUARIO;

    @Override
    public Object getValor(Object... pEntidade) {
        PessoaJuridica prosp = (PessoaJuridica) pEntidade[0];
        switch (this) {
            case NOME_RESPONSAVEL:
                String nome = "Nenhum Contato Associado";
                for (ContatoProspecto c : prosp.getContatosProspecto()) {
                    nome = c.getNome();
                    if (c.isUmContatoPrincipal()) {
                        return nome;
                    }
                }
                return nome;

            case TELEFONE_PRINCIPAL:
                String telefone = "Nenhum Contato Associado";
                for (ContatoProspecto c : prosp.getContatosProspecto()) {
                    if (UtilCRCStringValidador.isNuloOuEmbranco(c.getCelular())) {
                        telefone = c.getCelular();
                    } else {
                        if (UtilCRCStringValidador.isNAO_NuloNemBranco(c.getTelefoneAlternativo())) {
                            telefone = c.getTelefoneAlternativo();
                        }
                    }

                    if (c.isUmContatoPrincipal()) {
                        return telefone;
                    }
                }
                return telefone;

            case EMAIL_PRINCIPAL:
                String email = "email NaoDefinido";
                for (ContatoProspecto c : prosp.getContatosProspecto()) {
                    email = c.getEmail();
                    if (c.isUmContatoPrincipal()) {
                        return email;
                    }
                }
                return email;
            case CONTATO_PRINCIPAL:

                if (!prosp.getContatosProspecto().isEmpty()) {
                    for (ContatoProspecto c : prosp.getContatosProspecto()) {
                        email = c.getEmail();
                        if (c.isUmContatoPrincipal()) {
                            return c;
                        }
                    }
                    prosp.getContatosProspecto().get(0).setUmContatoPrincipal(true);
                    return prosp.getContatosProspecto().get(0);
                } else {
                    return null;
                }
            case USUARIO_LOGADO_AUTORIZADO:
                if (!prosp.isUmPerfilPrivado()) {
                    return true;
                } else {
                    for (UsuarioCRM usr : prosp.getUsuariosResponsaveis()) {
                        if (SBCore.getUsuarioLogado().equals(usr)) {
                            return true;
                        }
                    }
                    return false;
                }

            case QUANTIDADE_ATIVIDADES_NAO_FINALIZADAS:
                break;
            case QUANTIDADE_ATIVIDAES_NAO_FINALIZADAS_POR_TIPO:
                break;
            case QUANTIDADE_ATIVIDADE_NAO_FINALIZADAS_POR_TIPO_E_USUARIO:
                break;
            case CONTATO_INICIAL_CADASTRADO:
                if (UtilCRCStringValidador.isNuloOuEmbranco(prosp.getResponsavel())
                        && UtilCRCStringValidador.isNuloOuEmbranco(prosp.getEmail())) {
                    return false;
                } else {
                    return true;
                }
            default:
                throw new AssertionError(this.name());

        }
        return null;

    }

}
