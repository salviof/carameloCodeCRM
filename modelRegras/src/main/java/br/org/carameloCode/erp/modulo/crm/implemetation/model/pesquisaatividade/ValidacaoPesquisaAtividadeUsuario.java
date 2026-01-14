package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValidadorPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValidadoresPesquisaAtividade;

@ValidadorPesquisaAtividade(validador = ValidadoresPesquisaAtividade.USUARIO)
public class ValidacaoPesquisaAtividadeUsuario extends ValidacaoGenerica<PesquisaAtividade> {

    public ValidacaoPesquisaAtividadeUsuario(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        getPesquisaAtividade().setUsuario((UsuarioCRM) pValor);
        return null;
    }

    public PesquisaAtividade getPesquisaAtividade() {
        return getObjetoDoAtributo();
    }
}
