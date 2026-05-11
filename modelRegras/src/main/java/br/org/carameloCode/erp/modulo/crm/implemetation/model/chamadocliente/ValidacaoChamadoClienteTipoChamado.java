package br.org.carameloCode.erp.modulo.crm.implemetation.model.chamadocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValidadorChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValidadoresChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.TipoChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorChamadoCliente(validador = ValidadoresChamadoCliente.TIPOCHAMADO)
public class ValidacaoChamadoClienteTipoChamado
        extends
        ValidacaoGenerica<ChamadoCliente> {

    public ValidacaoChamadoClienteTipoChamado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List validar(java.lang.Object o) throws ErroValidacao {

        if (o != null) {
            if (getChamadoCliente().getTipoChamado() == null
                    || getChamadoCliente().getTipoChamado().equals((TipoChamado) o)) {
                if (getChamadoCliente().getDadosDoChamado() != null) {
                    getChamadoCliente().getDadosDoChamado().clear();
                }
            }
        }

        return getChamadoCliente().getDadosDoChamado();
    }

    public ChamadoCliente getChamadoCliente() {
        return getObjetoDoAtributo();
    }
}
