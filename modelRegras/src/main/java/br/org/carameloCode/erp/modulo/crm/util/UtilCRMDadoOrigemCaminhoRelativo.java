/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.util;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

/**
 *
 * @author salvio
 */
public class UtilCRMDadoOrigemCaminhoRelativo {

    public static void validarCaminhoRelativoContato(String pCaminho) throws ErroValidacao {

        ContatoProspecto novoCOntato = new ContatoProspecto();
        Pessoa pessoa = new Pessoa();
        novoCOntato.setProspecto(pessoa);
        ItfCampoInstanciado cp = getCampoInstanciadoContato(novoCOntato, pCaminho);
        if (cp == null) {
            throw new ErroValidacao("Falha obtendo dados pelo caminho" + pCaminho);
        }
        if (cp.isCampoNaoInstanciado()) {
            throw new ErroValidacao("Falha obtendo dados pelo caminho" + pCaminho);
        }
    }

    public static String getValor(ContatoProspecto pContato, String pCaminho) throws ErroValidacao {
        return getCampoInstanciadoContato(pContato, pCaminho).getValor().toString();
    }

    public static ItfCampoInstanciado getCampoInstanciadoContato(ContatoProspecto pContato, String pCaminho) {
        String[] partes = pCaminho.split(".");
        if (partes.length == 0) {
            partes = new String[]{pCaminho};
        }
        int i = 0;
        ItfCampoInstanciado campoInstanciado = null;
        for (String prt : partes) {
            if (i == 0) {
                campoInstanciado = pContato.getCPinst(prt);
            } else {
                campoInstanciado = campoInstanciado.getObjetoDoAtributo().getCampoInstanciadoByNomeOuAnotacao("prt");
            }
        }
        return campoInstanciado;
    }

}
