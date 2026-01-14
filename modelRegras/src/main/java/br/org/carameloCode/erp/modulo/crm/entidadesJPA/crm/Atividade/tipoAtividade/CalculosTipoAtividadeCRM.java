/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.StatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.calculosListagens.FabTipoFiltroCalculo;
import com.super_bits.modulosSB.Persistencia.dao.calculosListagens.ItfCalculosJPA;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.InfoCalculo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ParametroCalculo;
import org.apache.commons.lang3.ArrayUtils;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public enum CalculosTipoAtividadeCRM implements ItfCalculosJPA {
    @InfoCalculo(descricao = "", nomeCalculo = "", classePrincipal = EnvioEmail.class,
            parametros = {}
    )
    FORMULARIO_EXECUCAO,
    ACAO_FORMULARIO_EXECUCAO,
    @InfoCalculo(nomeCalculo = "Ultima Atividade Aberta Usuario",
            descricao = "Retorna a ultima atividade aberta deste tipo pelo usuário",
            classePrincipal = AtividadeCRM.class,
            parametros = {
                @ParametroCalculo(classe = UsuarioSB.class, nomeParametro = "Usuário", valorPadrao = ""),
                @ParametroCalculo(classe = StatusAtividade.class, nomeParametro = "Status", valorPadrao = "", valorEstatico = true)
            }
    )
    ATIVIDADE_ABERTA_PELO_USUARIO_LOGADO;

    @Override
    public Object getValor(Object... pParamentros) {
        try {

            TipoAtividadeCRM tipoAtividade = (TipoAtividadeCRM) pParamentros[0];
            switch (this) {
                case ACAO_FORMULARIO_EXECUCAO:

                    if (tipoAtividade.isTemModeloDocumentoVinculado()) {
                        return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_RELATORIO.getRegistro();
                    } else {
                        if (tipoAtividade.isTemDisparoDeEmail()) {
                            return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_COM_EMAIL.getRegistro();
                        }
                    }
                    if (tipoAtividade.isGeraNovoRelacionamento()) {

                        return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_GERADORA.getRegistro();
                    }

                    return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_SIMPLES.getRegistro();

                case FORMULARIO_EXECUCAO:

                    return tipoAtividade.getAcaoFormularioExecucao().getComoFormulario().getXhtml();

                case ATIVIDADE_ABERTA_PELO_USUARIO_LOGADO:
                    pParamentros = ArrayUtils.add(pParamentros, SBCore.getUsuarioLogado());
                    pParamentros = ArrayUtils.add(pParamentros, FabStatusAtividade.EM_ANDAMENTO.getRegistro());

                    return getCalculoPorCriteriaAPI(pParamentros, FabTipoFiltroCalculo.REGISTRO_UNICO);

                default:
                    throw new AssertionError(this.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Calculo de valor em " + this.toString(), t);
            return null;
        }

    }

}
