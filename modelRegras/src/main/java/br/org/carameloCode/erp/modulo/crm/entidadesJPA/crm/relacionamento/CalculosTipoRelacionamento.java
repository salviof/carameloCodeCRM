/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.calculosListagens.ItfCalculosJPA;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.InfoCalculo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ParametroCalculo;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public enum CalculosTipoRelacionamento implements ItfCalculosJPA {
    @InfoCalculo(descricao = "", nomeCalculo = "", classePrincipal = AtividadeCRM.class)
    QUANTIDADE_ATIVIDADE,
    @InfoCalculo(parametros = {
        @ParametroCalculo(classe = UsuarioSB.class, nomeParametro = "Usuário", valorPadrao = "")},
            descricao = "", nomeCalculo = "", classePrincipal = AtividadeCRM.class)
    QUANTIDADE_POR_USUARIO;

    @Override
    public Object getValor(Object... pParamentros) {
        try {
            switch (this) {
                case QUANTIDADE_ATIVIDADE:
                    return getSomaPadrao(pParamentros);

                case QUANTIDADE_POR_USUARIO:
                    return getSomaPadrao(pParamentros);

                default:
                    throw new AssertionError(this.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Calculo de valor em " + this.toString(), t);
            return null;
        }

    }

}
