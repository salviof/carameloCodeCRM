/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmContato;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.CPUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto.ValorLogicoContatoProspectoUsuarioVinculado;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class ModuloCRMContatos extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.CONTATO_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema contatoRemover(ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void executarAcoesIniciais() throws ErroEmBancoDeDados {
                super.executarAcoesIniciais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ContatoProspecto contato = loadEntidade(pContato);
                boolean permissao = false;
                if (pContato.getProspecto().getContatoPrincipal().equals(pContato)) {
                    throw new ErroRegraDeNegocio("o contato principal não pode ser removido");
                }
                Pessoa pessoa = loadEntidade(pContato.getProspecto());
                if (SBCore.isEmModoProducao()) {
                    try {
                        permissao = pessoa.getCPinst("atendenteLogadoResponsavelPrincipal").getValorComoBoolean();
                        //pessoa.getContatosProspecto().remove(contato);
                        if (!permissao) {
                            throw new ErroRegraDeNegocio("Sem permissão para exclusão");
                        }
                    } catch (Throwable t) {

                    }
                }

                //removerEntidade(contato.getUsuarioVinculado());
                // removerEntidade(contato);
                UtilSBPersistencia.executaSQL(getEMResposta(), "delete from envioDocumento_contatos where contatos_id = " + pContato.getId());

                UsuarioCrmCliente usuarioVinculado = contato.getUsuarioVinculado();
                if (usuarioVinculado == null) {
                    List<UsuarioCrmCliente> usuarios = new ConsultaDinamicaDeEntidade(UsuarioCrmCliente.class, getEm()).addCondicaoManyToOneIgualA(CPUsuarioCrmCliente.contatoclientevinculado, contato).resultadoRegistros();
                    if (!usuarios.isEmpty()) {
                        usuarioVinculado = usuarios.get(0);
                    }
                }

                if (usuarioVinculado != null) {
                    if (pessoa.getContatoPrincipal().getUsuarioVinculado() != null) {
                        UtilSBPersistencia.executaSQL(getEMResposta(), "update ChamadoCliente set usuarioCliente_id = " + pessoa.getContatoPrincipal().getUsuarioVinculado().getId() + " where usuarioCliente_id =" + usuarioVinculado.getId());
                    } else {
                        UtilSBPersistencia.executaSQL(getEMResposta(), "delete ChamadoCliente  where usuarioCliente_id =" + usuarioVinculado.getId());
                    }
                    UtilSBPersistencia.executaSQL(getEMResposta(), "update ContatoProspecto set usuarioVinculado_id = null where usuarioVinculado_id =" + usuarioVinculado.getId());
                    UtilSBPersistencia.executaSQL(getEMResposta(), "delete from UsuarioSB where id = " + usuarioVinculado.getId());

                }

                UtilSBPersistencia.executaSQL(getEMResposta(), "delete from ContatoProspecto where id = " + pContato.getId());
                //atualizarEntidade(pessoa);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.CONTATO_CTR_SALVAR)
    public static ItfRespostaAcaoDoSistema contatoSalvarMerge(ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (UtilCRCStringValidador.isNuloOuEmbranco(pContato.getEmail())) {
                    throw new ErroRegraDeNegocio("O e-mail é obrigatório");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(pContato.getNome())) {
                    throw new ErroRegraDeNegocio("O nome é obrigatorio");
                }
                if (pContato.getProspecto() == null) {
                    throw new ErroRegraDeNegocio("O prospecto é obrigatorio");
                }

                ContatoProspecto contato = atualizarEntidade(pContato);

                setRetorno(contato);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.CONTATO_CTR_ATUALIZAR_USUARIO)
    public static ItfRespostaAcaoDoSistema contatoAtualizarUsuario(ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                if (pContato.getId() == null) {
                    throw new ErroRegraDeNegocio("Salve os dados do contato primeiro");
                }
                ContatoProspecto contato = loadEntidade(pContato);
                if (UtilCRCStringValidador.isNuloOuEmbranco(contato.getEmail())) {
                    throw new ErroRegraDeNegocio("O e-mail é obrigatório");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(contato.getNome())) {
                    throw new ErroRegraDeNegocio("O nome é obrigatorio");
                }
                if (contato.getProspecto() == null) {
                    throw new ErroRegraDeNegocio("O prospecto é obrigatorio");
                }

                if (contato.getId() != null && contato.getId() > 0) {
                    if (!UtilCRCStringValidador.isNuloOuEmbranco(contato.getEmail())) {

                        if (contato.getUsuarioVinculado() != null) {
                            if (contato.getEmail() != null) {
                                if (contato.getUsuarioVinculado().getEmail() != null) {
                                    if (contato.getEmail().equals(contato.getUsuarioVinculado().getEmail())) {
                                        contato.getUsuarioVinculado().setEmail(contato.getEmail());
                                        atualizarEntidade(contato.getUsuarioVinculado());
                                    }
                                }
                            }
                            if (contato.getTelefone() != null) {
                                if (contato.getCelular() != null) {
                                    if (contato.getUsuarioVinculado().getTelefone() != null) {
                                        contato.getUsuarioVinculado().setTelefone(contato.getCelular());
                                        atualizarEntidade(contato.getUsuarioVinculado());
                                    }
                                }
                            }

                        }
                        if (contato.getUsuarioVinculado() == null) {

                            List<UsuarioCrmCliente> usuariosJaVinculados = new ConsultaDinamicaDeEntidade(UsuarioCrmCliente.class, getEMResposta())
                                    .addCondicaoManyToOneIgualA(CPUsuarioCrmCliente.contatoclientevinculado, contato)
                                    .gerarResultados();
                            if (!usuariosJaVinculados.isEmpty()) {
                                try {
                                    usuariosJaVinculados.get(0).prepararNovoObjeto(contato);
                                    contato.setUsuarioVinculado(UtilSBPersistencia.mergeRegistro(usuariosJaVinculados.get(0)));
                                } catch (Throwable t) {

                                }
                                contato.setUsuarioVinculado(usuariosJaVinculados.get(0));
                            } else {
                                ConsultaDinamicaDeEntidade novaCOnsulta = new ConsultaDinamicaDeEntidade(UsuarioSB.class, getEMResposta());
                                if (!UtilCRCStringValidador.isNuloOuEmbranco(contato.getEmail())) {
                                    novaCOnsulta.addcondicaoCampoIgualA("email", contato.getEmail());

                                    //novaCOnsulta.addcondicaoCampoIgualA("telefone", getContato().getCelular());
                                    List<UsuarioCRM> usuarios = novaCOnsulta.gerarResultados();
                                    if (usuarios.isEmpty()) {

                                        UsuarioCrmCliente usuarioNovo = new UsuarioCrmCliente();
                                        usuarioNovo.prepararNovoObjeto(contato);
                                        usuarioNovo.setSenha(ValorLogicoContatoProspectoUsuarioVinculado.VALOR_PADRAO_SENHA);

                                        usuarioNovo.getContatoClienteVinculado().getNome();
                                        usuarioNovo.getProspectos().size();
                                        usuarioNovo.getContatoClienteVinculado().getProspecto().getNome();
                                        usuarioNovo = UtilSBPersistencia.mergeRegistro(usuarioNovo, getEMResposta());
                                        contato.setUsuarioVinculado(usuarioNovo);
                                    } else {
                                        if (usuarios.get(0).isUmUsuarioDoCliente()) {
                                            contato.setUsuarioVinculado(usuarios.get(0).getComoUsuarioCliente());
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
                            try {
                                contato.getUsuarioVinculado().prepararNovoObjeto(contato);
                            } catch (Throwable ex) {
                                contato.setUsuarioVinculado(null);
                            }
                        }
                    }
                }

                setRetorno(atualizarEntidade(contato));

            }
        }
                .getResposta();
    }

}
