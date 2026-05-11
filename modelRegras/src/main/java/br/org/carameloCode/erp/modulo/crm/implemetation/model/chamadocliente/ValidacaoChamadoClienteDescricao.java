package br.org.carameloCode.erp.modulo.crm.implemetation.model.chamadocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValidadorChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValidadoresChamadoCliente;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorChamadoCliente(validador = ValidadoresChamadoCliente.DESCRICAO)
public class ValidacaoChamadoClienteDescricao
        extends
        ValidacaoGenerica<ChamadoCliente> {

    public ValidacaoChamadoClienteDescricao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List validar(java.lang.Object o) throws ErroValidacao {
        if (o == null) {
            throw new ErroValidacao("Descreva o problema");
        }
        if (o.toString().length() < 30) {
            throw new ErroValidacao("Descreva melhor problema, com o máximo de informações nescessárias para a resolução.");
        }
        return new ArrayList<>();
    }

    public ChamadoCliente getChamadoCliente() {
        return getObjetoDoAtributo();
    }
}
