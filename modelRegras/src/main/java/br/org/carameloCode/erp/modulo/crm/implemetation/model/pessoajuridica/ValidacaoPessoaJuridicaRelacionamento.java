package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.coletivojava.fw.utils.agendador.UtilSBAgendadorTarefas;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.historicoRelacionamento.HistoricoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.FabAcaoCrmAplicacao;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.Date;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadorPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadoresPessoaJuridica;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

@ValidadorPessoaJuridica(validador = ValidadoresPessoaJuridica.RELACIONAMENTO)
public class ValidacaoPessoaJuridicaRelacionamento extends ValidacaoGenerica<PessoaJuridica> {

    public ValidacaoPessoaJuridicaRelacionamento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        HistoricoRelacionamento novoHistorico = new HistoricoRelacionamento();

        TipoRelacionamento relacionamento = (TipoRelacionamento) pValor;
        if (relacionamento == null) {
            throw new ErroValidacao("O Relacionamento não pode ser nulo");
        }
        if (relacionamento.equals(getProspectoEmpresa().getRelacionamento())) {
            return null;
        }
        getProspectoEmpresa().setDataHoraMudancaRelacionamento(new Date());
        try {

            novoHistorico.prepararNovoObjeto(getProspectoEmpresa());
            novoHistorico.setNovoRelacionamento(relacionamento);

            novoHistorico = (HistoricoRelacionamento) UtilSBPersistencia.mergeRegistro(novoHistorico, UtilSBPersistencia.getEMDoContexto());
            getProspectoEmpresa().getHistoricoRelacionamento().add(novoHistorico);
            if (novoHistorico == null) {
                throw new ErroValidacao("Erro armazenando histórico de alteração de relacionamento");
            }
            if (relacionamento.getRelacionamentoPeranteInercia() != null) {
                UtilSBAgendadorTarefas.agendarTarefa(FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_ALTERAR_RELACIONAMENTO_POR_INERCIA,
                        UtilCRCDataHora.incrementaHoras(new Date(), relacionamento.getTempoAceitavelResolucao() + relacionamento.getTempoAcaoInerciaRelacionamento()),
                        novoHistorico);
            }
        } catch (ErroPreparandoObjeto e) {
            throw new ErroValidacao("Falha gerando histórico de alterção de relacionamento");
        }
        getProspectoEmpresa().setRelacionamento(relacionamento);
        return null;
    }

    public PessoaJuridica getProspectoEmpresa() {
        return getObjetoDoAtributo();
    }
}
