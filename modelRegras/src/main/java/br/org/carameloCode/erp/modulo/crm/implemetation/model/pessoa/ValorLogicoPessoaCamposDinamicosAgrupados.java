package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import com.google.api.client.util.Lists;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.HashMap;
import java.util.Map;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.CAMPOSDINAMICOSAGRUPADOS)
public class ValorLogicoPessoaCamposDinamicosAgrupados
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaCamposDinamicosAgrupados(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    private Map<String, GrupoCampoInstanciado> grupos;

    @Override
    public Object getValor(Object... pEntidade) {
        GrupoCampoInstanciado grupoDiversos = new GrupoCampoInstanciado("Dados diversos");
        if (grupos == null) {
            grupos = new HashMap<>();
            getProsp().getDadosColetados().forEach(ddo -> {
                if (ddo.getTipoDadoCRM().getGrupoSubCamposExibicao() == null) {
                    grupoDiversos.adicionarCampo(ddo.getCampoInstanciado());
                } else {
                    String nomeGrupo = ddo.getTipoDadoCRM().getGrupoSubCamposExibicao().getNome();

                    if (!grupos.containsKey(ddo.getTipoDadoCRM().getGrupoSubCamposExibicao().getNome())) {
                        GrupoCampoInstanciado novoGrupo = new GrupoCampoInstanciado(nomeGrupo);
                        grupos.put(nomeGrupo, novoGrupo);

                    }
                    grupos.get(nomeGrupo).adicionarCampo(ddo.getCampoInstanciado());
                }
            });
            if (!grupoDiversos.getCampos().isEmpty()) {
                grupos.put(grupoDiversos.getNome(), grupoDiversos);
            }
        }

        return Lists.newArrayList(grupos.values());
    }

    public Pessoa getProsp() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
