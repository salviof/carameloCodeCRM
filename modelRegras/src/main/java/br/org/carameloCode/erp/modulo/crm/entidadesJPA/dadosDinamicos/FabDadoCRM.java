/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import java.util.Optional;

/**
 *
 * @author sfurbino
 */
public enum FabDadoCRM implements ComoFabrica {

    novoDado;

    /**
     *
     * @return Dado CRM a ser coletado
     */
    @Override
    public DadoCRM getRegistro() {
        throw new UnsupportedClassVersionError("Para Gerar um Dado é nescessário uma especificar uma atividade, e Tipo de dados");
    }

    public static DadoCRM getNovoDadoDeAtividade(String pValor, AtividadeCRM pAtividade, TipoDadoCRM pTipoDado) {
        return configDadoCRM(pValor, pAtividade, pTipoDado);
    }

    public static DadoCRM getDadoNovoSeNaoExistir(AtividadeCRM pAtividade, TipoDadoCRM pTipoDado) {

        Optional<DadoCRM> dadoPesquisa = pAtividade.getProspectoEmpresa().getDadosColetados().stream().filter(td -> td.getTipoDadoCRM().equals(pTipoDado)).findFirst();
        if (dadoPesquisa.isPresent()) {
            DadoCRM dado = dadoPesquisa.get();
            dado.setAtividadeCRM(pAtividade);
            return dado;
        }
        DadoCRM novoDadoCRM = new DadoCRM();
        novoDadoCRM.setProspectoEmpresa(pAtividade.getProspectoEmpresa());
        novoDadoCRM.setAtividadeCRM(pAtividade);
        novoDadoCRM.setTipoDadoCRM(pTipoDado);
        novoDadoCRM.setValor(pTipoDado.getValorPadrao());
        return novoDadoCRM;
    }

    public static DadoCRM getDadoNovoSeNaoExistir(Pessoa pPessoa, TipoDadoCRM pTipoDado) {

        Optional<DadoCRM> dado = pPessoa.getDadosColetados().stream().filter(td -> td.getTipoDadoCRM().equals(pTipoDado)).findFirst();
        if (dado.isPresent()) {
            return dado.get();
        }
        DadoCRM novoDadoCRM = new DadoCRM();
        novoDadoCRM.setProspectoEmpresa(pPessoa);
        novoDadoCRM.setTipoDadoCRM(pTipoDado);
        novoDadoCRM.setValor(pTipoDado.getValorPadrao());
        return novoDadoCRM;
    }

    private static DadoCRM configDadoCRM(String pValor, AtividadeCRM pAtividade, TipoDadoCRM pTipoDado) {

        DadoCRM novoDadoCRM = new DadoCRM();
        novoDadoCRM.setProspectoEmpresa(pAtividade.getProspectoEmpresa());

        novoDadoCRM.setNome(pTipoDado.getLabel());
        novoDadoCRM.setTipoDadoCRM(pTipoDado);

        novoDadoCRM.setAtividadeCRM(pAtividade);
        novoDadoCRM.setValor(pValor);

        return novoDadoCRM;
    }

}
