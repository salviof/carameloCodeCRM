/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.util;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class UtilDadoDinamico {

    public boolean aplicarDadosDinamicos(ComoEntidadeSimples objeto, List<DadoCRM> dados) {

        Map<TipoDadoCRM, DadoCRM> mapaDados;
        mapaDados = new HashMap<>();
        for (DadoCRM dado : dados) {
            mapaDados.put(dado.getTipoDadoCRM(), dado);
        }

        List<Field> camposVinculados = UtilCRCReflexao.getCamposRecursivoPorAnotacao(objeto.getClass(), InfoDadoDinamico.class, EntidadeSimples.class);

        for (Field campoReflexao : camposVinculados) {
            InfoDadoDinamico infoDado = campoReflexao.getAnnotation(InfoDadoDinamico.class);
            DadoCRM dado = mapaDados.get(infoDado.dadoDinamico());
            try {
                if (dado != null) {
                    campoReflexao.setAccessible(true);

                    switch (dado.getTipoCampo().getTipoPrimitivo()) {
                        case INTEIRO:
                            break;
                        case LETRAS:
                            break;
                        case DATAS:
                            break;
                        case BOOLEAN:
                            break;
                        case DECIMAL:
                            break;
                        case ENTIDADE:
                            break;
                        case OUTROS_OBJETOS:
                            break;
                        default:
                            campoReflexao.set(objeto, dado.getValor());

                    }

                }
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro plicando dado Coletado em entidade", t);
                return false;
            }
        }
        return true;

    }

}
