package br.org.carameloCode.erp.modulo.crm.api.model.reserva;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.FabTipoWidgetFormulario;

public class ValidacaoReservaHorarioPessoaRelacionada extends ValidacaoGenerica<ReservaHorario> {

    public ValidacaoReservaHorarioPessoaRelacionada(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        Pessoa pessoaSelecionada = (Pessoa) pValor;
        Pessoa pessoaAnterior = getReservaHorario().getPessoaRelacionada();

        if (pessoaAnterior != null) {
            if (!pessoaSelecionada.equals(pessoaAnterior)) {
                getReservaHorario().setPessoaRelacionada(pessoaSelecionada);
                //      List selecao = (List) getReservaHorario().getCPinst(CPReservaHorario.contatosatendidos).getValor();
//                selecao.clear();
                //          getReservaHorario().getCPinst(CPReservaHorario.contatosatendidos).getComoSeltorItens().limparSelecao();
                //         getReservaHorario().getCPinst(CPReservaHorario.contatosatendidos).getComoSeltorItens().atualizarListaCompleta();
                //        Object teste = getReservaHorario().getCPinst(CPReservaHorario.contatosatendidos).getComoSeltorItens().getCampoSeletorItens();
                //      System.out.println(teste);

            }
        }

        return FabTipoWidgetFormulario.getCampos(getReservaHorario().getCPinst("contatosAtendidos"));
    }

    public ReservaHorarioCRM getReservaHorario() {
        return (ReservaHorarioCRM) getObjetoDoAtributo();
    }

}
