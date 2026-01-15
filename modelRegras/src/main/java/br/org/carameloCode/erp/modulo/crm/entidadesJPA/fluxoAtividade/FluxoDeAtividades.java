/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.fluxoAtividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.zonaRelacionamento.ResultadoTipoRelacionamento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = {"Fluxo de Atividades"}, plural = "Fluxos")
public class FluxoDeAtividades extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private final int id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private final String nome;

    private final List<TipoRelacionamento> relacionamentos;

    private TipoRelacionamento relacionamentoInicial;

    private final List<ResultadoTipoRelacionamento> resultados;

    @Deprecated
    public FluxoDeAtividades() {
        this.id = 0;
        this.nome = null;
        this.relacionamentos = null;
        this.relacionamentoInicial = null;
        this.resultados = null;
    }

    public FluxoDeAtividades(List<TipoRelacionamento> pRrelacionamentos) {
        relacionamentos = new ArrayList();
        for (TipoRelacionamento relac : pRrelacionamentos) {
            if (relac.getPeso() == 0) {
                relacionamentoInicial = relac;

            }
            relacionamentos.add(relac);
        }

        nome = "FLuxo " + SBCore.getNomeProjeto();
        id = nome.hashCode();

        resultados = new ArrayList<>();

        for (TipoRelacionamento rela : pRrelacionamentos) {
            resultados.add(rela.getResultado());
        }
    }

    public List<TipoRelacionamento> getRelacionamentos() {
        return relacionamentos;
    }

    public TipoRelacionamento getRelacionamentoInicial() {
        return relacionamentoInicial;
    }

}
