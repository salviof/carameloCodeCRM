/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto;

import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.FabTipoPesquisaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilar;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;

/**
 *
 * @author sfurbino
 */
public class ComparadorPessoa extends ItemSimilar<ComparadorPessoa> {

    public ComparadorPessoa(ComoEntidadeSimples pObjetoAnalizado, String parametro, FabTipoPesquisaGenerica tipoPesquisa) {

        super(pObjetoAnalizado, parametro, tipoPesquisa);

    }

    @Override
    public String getTextoReferencia() {

        switch (tipoPesquisaGenerica) {
            case CODIGO:
                return String.valueOf(getObjetoAnalizado().getId());

            case NOME:
                return getObjetoAnalizado().getNome();
            case DESCRICAO:
                return getObjetoAnalizado().getNome();
            case TELEFONE:

                StringBuilder telefonesBuilder = new StringBuilder();
                if (getObjetoAnalizado().getTelefonePrincipal() != null && getObjetoAnalizado().getTelefonePrincipal().length() > 3) {
                    telefonesBuilder.append(getObjetoAnalizado().getTelefonePrincipal());
                }
                if (getObjetoAnalizado().getTelefone() != null && getObjetoAnalizado().getTelefone().length() > 3) {
                    telefonesBuilder.append(getObjetoAnalizado().getTelefone());
                }
                getObjetoAnalizado().getContatosProspecto().stream().filter(ct -> ct.getTelefone() != null && ct.getTelefone().length() > 3).map(ct -> ct.getCelular())
                        .forEach(telefonesBuilder::append);
                return telefonesBuilder.toString();

            case EMAIL:
                StringBuilder emailssBuilder = new StringBuilder();
                if (getObjetoAnalizado().getEmail() != null && getObjetoAnalizado().getEmail().length() > 3) {
                    emailssBuilder.append(getObjetoAnalizado().getTelefonePrincipal());
                }

                getObjetoAnalizado().getContatosProspecto().stream().filter(ct -> ct.getEmail() != null && ct.getEmail().length() > 3).map(ct -> ct.getEmail())
                        .forEach(emailssBuilder::append);
                return emailssBuilder.toString();

            case SITE:
                if (getObjetoAnalizado() instanceof PessoaJuridica) {
                    return ((PessoaJuridica) getObjetoAnalizado()).getSite();
                }
                if (getObjetoAnalizado() instanceof PessoaFisica) {
                    return ((PessoaFisica) getObjetoAnalizado()).getSite();
                }
                break;
            case DOCUMENTO:
                if (getObjetoAnalizado() instanceof PessoaJuridica) {
                    return ((PessoaJuridica) getObjetoAnalizado()).getCnpj();
                }
                if (getObjetoAnalizado() instanceof PessoaFisica) {
                    return ((PessoaFisica) getObjetoAnalizado()).getCpf();
                }

                break;
            case PERSONALIZADA:
                return getObjetoAnalizado().getNome();

            default:
                throw new AssertionError();
        }
        return getObjetoAnalizado().getNome();
    }

    @Override
    public Pessoa getObjetoAnalizado() {

        return (Pessoa) super.getObjetoAnalizado(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(ComparadorPessoa o) {
        if (termoPesquisa == null) {

            return comparacaoSimples(o);
        }
        return super.compareTo(o); //To change body of generated methods, choose Tools | Templates.

    }

    public int comparacaoSimples(ComparadorPessoa itemComparado) {
        try {
            if (getObjetoAnalizado().getRelacionamento().getPeso() < 0 && (itemComparado.getObjetoAnalizado().getRelacionamento().getPeso() >= 0)) {
                return 1;
            }
            if (itemComparado.getObjetoAnalizado().getRelacionamento().getPeso() < 0 && (getObjetoAnalizado().getRelacionamento().getPeso() >= 0)) {
                return -1;
            }

            if (getObjetoAnalizado().getDataHoraAlterouProspecto().getTime() > (itemComparado.getObjetoAnalizado().getDataHoraAlterouProspecto().getTime())) {
                return -1;
            } else {
                if (getObjetoAnalizado().getDataHoraAlterouProspecto().getTime() < (itemComparado.getObjetoAnalizado().getDataHoraAlterouProspecto().getTime())) {
                    return 1;
                }
                return 0;
            }

        } catch (Throwable t) {
            return 0;
        }
    }
}
