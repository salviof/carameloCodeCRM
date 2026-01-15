package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto;

import br.org.coletivoJava.integracoes.matrixChat.config.FabConfigApiMatrixChat;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValorLogicoContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValoresLogicosContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.CPUsuarioCrmCliente;

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
            if (getContato().getId() != null && getContato().getId() > 0) {
                if (!UtilCRCStringValidador.isNuloOuEmbranco(getContato().getEmail())) {
                    EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
                    try {
                        List<UsuarioCrmCliente> usuariosJaVinculados = new ConsultaDinamicaDeEntidade(UsuarioCrmCliente.class, em)
                                .addCondicaoManyToOneIgualA(CPUsuarioCrmCliente.contatoclientevinculado, getContato())
                                .gerarResultados();
                        if (!usuariosJaVinculados.isEmpty()) {
                            try {
                                usuariosJaVinculados.get(0).prepararNovoObjeto(getContato());
                                getContato().setUsuarioVinculado(UtilSBPersistencia.mergeRegistro(usuariosJaVinculados.get(0)));
                            } catch (Throwable t) {

                            }
                            getContato().setUsuarioVinculado(usuariosJaVinculados.get(0));
                        } else {
                            ConsultaDinamicaDeEntidade novaCOnsulta = new ConsultaDinamicaDeEntidade(UsuarioSB.class, em);
                            if (!UtilCRCStringValidador.isNuloOuEmbranco(getContato().getEmail())) {
                                novaCOnsulta.addcondicaoCampoIgualA("email", getContato().getEmail());

                                //novaCOnsulta.addcondicaoCampoIgualA("telefone", getContato().getCelular());
                                List<UsuarioCRM> usuarios = novaCOnsulta.gerarResultados();
                                if (usuarios.isEmpty()) {

                                    UsuarioCrmCliente usuarioNovo = new UsuarioCrmCliente();
                                    usuarioNovo.prepararNovoObjeto(getContato());
                                    usuarioNovo.setSenha(ValorLogicoContatoProspectoUsuarioVinculado.VALOR_PADRAO_SENHA);
                                    //  usuarioNovo = UtilSBPersistencia.mergeRegistro(usuarioNovo, em);

                                    usuarioNovo.getContatoClienteVinculado().getNome();
                                    usuarioNovo.getProspectos().size();
                                    usuarioNovo.getContatoClienteVinculado().getProspecto().getNome();
                                    getContato().setUsuarioVinculado(usuarioNovo);
                                } else {
                                    if (usuarios.get(0).isUmUsuarioDoCliente()) {
                                        getContato().setUsuarioVinculado(usuarios.get(0).getComoUsuarioCliente());
                                    } else {
                                        SBCore.enviarAvisoAoUsuario("Um usuário do tipo " + usuarios.get(0).getClass().getSimpleName() + " conflita com um contato deste cartão");
                                        if (usuarios.get(0).getProspectos().isEmpty()) {
                                            SBCore.enviarAvisoAoUsuario("Cartão conflitante:" + usuarios.get(0).getProspectos().get(0).getNome());
                                        }
                                    }
                                }
                            }
                        }

                        //  UtilSBPersistencia.mergeRegistro(getContato(), em);
                    } finally {

                        UtilSBPersistencia.fecharEM(em);
                    }

                    try {
                        getContato().getUsuarioVinculado().prepararNovoObjeto(getContato());
                    } catch (Throwable ex) {
                        getContato().setUsuarioVinculado(null);
                    }
                }
            }
        }

        return getContato().getUsuarioVinculado();
    }

    public ContatoProspecto getContato() {
        return (ContatoProspecto) getCampoInst().getObjetoDoAtributo();
    }

}
