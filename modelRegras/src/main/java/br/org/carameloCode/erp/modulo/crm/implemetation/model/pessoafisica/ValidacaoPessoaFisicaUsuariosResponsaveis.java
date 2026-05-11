package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadorPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadoresPessoaFisica;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorPessoaFisica(validador = ValidadoresPessoaFisica.USUARIOSRESPONSAVEIS)
public class ValidacaoPessoaFisicaUsuariosResponsaveis
        extends
        ValidacaoGenerica<PessoaFisica> {

    public ValidacaoPessoaFisicaUsuariosResponsaveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List validar(java.lang.Object o) throws ErroValidacao {
        CarameloCode
                .getServicoMensagemFireForget()
                .enviarMsgErroAoUsuario(
                        "A Validação do campo  Usuarios Responsaveis não foi implementada");
        return new ArrayList<>();
    }

    public PessoaFisica getPessoaFisica() {
        return getObjetoDoAtributo();
    }
}
