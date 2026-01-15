package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrmcliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Optional;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValorLogicoUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValoresLogicosUsuarioCrmCliente;

@ValorLogicoUsuarioCrmCliente(calculo = ValoresLogicosUsuarioCrmCliente.CONTATOCLIENTEVINCULADO)
public class ValorLogicoUsuarioCrmClienteContatoClienteVinculado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCrmClienteContatoClienteVinculado(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        String email = getUsuarioCliente().getEmail();

        Optional<ContatoProspecto> pesquisaContato = getUsuarioCliente().getRepresentanteLegal().getContatosComEmail().stream().
                filter(contato -> contato.getEmail().equals(email)).findFirst();
        if (pesquisaContato.isPresent()) {
            getUsuarioCliente().setContatoClienteVinculado(pesquisaContato.get());
        }

        return getUsuarioCliente().getContatoClienteVinculado();
    }

    public UsuarioCrmCliente getUsuarioCliente() {
        return (UsuarioCrmCliente) getCampoInst().getObjetoDoAtributo();
    }
}
