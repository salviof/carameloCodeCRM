/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.ia;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import groovy.json.JsonBuilder;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class UtilCRM_IA {

    public static JsonObject getDadosBasicosPessoa(Pessoa pPessoa) {

        JsonObjectBuilder dadosPesso = Json.createObjectBuilder();
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            Pessoa pessoa = UtilSBPersistencia.loadEntidade(pPessoa, em);
            dadosPesso.add("nome", pessoa.getNome());

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return dadosPesso.build();
    }

    public static JsonObject getDadosDinamicossPessoa(Pessoa pPessoa) {

        JsonObjectBuilder dadosPesso = Json.createObjectBuilder();
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {

            Pessoa pessoa = UtilSBPersistencia.loadEntidade(pPessoa, em);
            List<DadoCRM> dados = pessoa.getDadosColetados();
            for (DadoCRM dado : dados) {

            }

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return dadosPesso.build();
    }

    public static JsonObject getDadosAtividadePessoa(Pessoa pPessoa) {

        JsonObjectBuilder dadosPesso = Json.createObjectBuilder();
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            Pessoa pessoa = UtilSBPersistencia.loadEntidade(pPessoa, em);
            List<AtividadeCRM> dados = pessoa.getAtividadesExecutadas();
            for (AtividadeCRM atividade : dados) {

            }

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return dadosPesso.build();
    }

}
