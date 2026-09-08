package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmContato.ModuloCRMContatos;
import br.org.coletivoJava.integracoes.matrixChat.config.FabConfigApiMatrixChat;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValorLogicoContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValoresLogicosContatoProspecto;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import javax.persistence.EntityManager;

@ValorLogicoContatoProspecto(calculo = ValoresLogicosContatoProspecto.USUARIOVINCULADO)
public class ValorLogicoContatoProspectoUsuarioVinculado
        extends
        ValorLogicoCalculoGenerico {

    public static final String VALOR_PADRAO_SENHA = "S3nh@Padrão" + SBCore.getConfigModulo(FabConfigApiMatrixChat.class).getPropriedade(FabConfigApiMatrixChat.SEGREDO).hashCode();

    public ValorLogicoContatoProspectoUsuarioVinculado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getContato().getUsuarioVinculado() == null) {
            ItfRespostaAcaoDoSistema resp = ModuloCRMContatos.contatoAtualizarUsuario(getContato());
            if (resp.isSucesso()) {
                EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
                try {
                    ContatoProspecto contato = UtilSBPersistencia.loadEntidade(getContato(), em);
                    contato.getUsuarioVinculado().getRepresentanteLegal().getNome();
                    getContato().setUsuarioVinculado(contato.getUsuarioVinculado());

                } finally {
                    UtilSBPersistencia.fecharEM(em);
                }

            }
        }

        return getContato().getUsuarioVinculado();

    }

    public ContatoProspecto getContato() {
        return (ContatoProspecto) getCampoInst().getObjetoDoAtributo();
    }

}
