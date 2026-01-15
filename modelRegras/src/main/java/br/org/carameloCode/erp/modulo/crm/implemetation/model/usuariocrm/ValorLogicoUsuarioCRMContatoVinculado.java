package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoColaborador.ContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValorLogicoUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValoresLogicosUsuarioCRM;

@ValorLogicoUsuarioCRM(calculo = ValoresLogicosUsuarioCRM.CONTATOVINCULADO)
public class ValorLogicoUsuarioCRMContatoVinculado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCRMContatoVinculado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        UsuarioCRM usuarioAtualizado = null;
        boolean temContato = false;
        try {
            usuarioAtualizado = UtilSBPersistencia.loadEntidade(getUsuario(), em);
            temContato = usuarioAtualizado.getContatoVinculado() != null;
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        //Confirmando se o contato não foi cadastrado na tela onde é possível alterar a senha e alterar cadastro com a mesma intancia (podendo gerar falsos nulos)
        if (!temContato) {
            setValorPorReflexao(new ContatoColaborador());
        }
        getUsuario().getContatoVinculado().setUsuario(usuarioAtualizado);
        getUsuario().getContatoVinculado().setNome(getUsuario().getNome());
        getUsuario().getContatoVinculado().setEmail(getUsuario().getEmail());

        return getUsuario().getContatoVinculado();
    }

    public UsuarioCRM getUsuario() {
        return (UsuarioCRM) getCampoInst().getObjetoDoAtributo();
    }
}
