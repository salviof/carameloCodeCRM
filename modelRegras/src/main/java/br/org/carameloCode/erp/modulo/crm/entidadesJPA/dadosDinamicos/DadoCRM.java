package br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfDadoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfTipoAtributoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TipoAtributoMetodosBase;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoNaoImplementado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciadoDInamico.CampoInstanciadoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.lang.reflect.Field;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model.ItfTDadoDinamicoCRM;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 *
 * O dado CRM é um registro de um dado que irá acontecer em determinada etapa do
 * sistema, e pode ser adicionado no sistema de maneira dinamica
 *
 * @author sfurbino
 */
@InfoObjetoSB(tags = "Informação complementar de prospecto", plural = "Dados CRM")
@Entity
@EntityListeners(ListenerEntidadePadrao.class)
public class DadoCRM extends EntidadeORMNormal implements ItfDadoDinamico, ItfTDadoDinamicoCRM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, label = "Dado a coletar")
    @Column(length = 2000)
    private String valor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datahora;

    @ManyToOne(targetEntity = Pessoa.class, optional = true)
    private Pessoa prospectoEmpresa;

    @ManyToOne(targetEntity = AtividadeCRM.class, optional = true)
    private AtividadeCRM atividadeCRM;

    @Transient
    private Field campoValorReflection;

    @ManyToOne(targetEntity = TipoDadoCRM.class, optional = false)
    private TipoDadoCRM tipoDadoCRM;

    @Transient
    private CampoCRMInstanciado campoCRMInstanciado;

    @ManyToOne(targetEntity = TipoRelacionamento.class)
    private TipoRelacionamento tipoRelacionamentoVinculado;

    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    private UsuarioCRM usuarioCadastrou;
    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    private UsuarioCRM usuarioEditou;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCadastrou;
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEditou;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "documentoPessoa")
    private String documentoPessoa;

    @Transient
    private boolean informacaoValidada;

    @Override
    public String getDocumentoPessoa() {
        return documentoPessoa;
    }

    public void setDocumentoPessoa(String documentoPessoa) {
        this.documentoPessoa = documentoPessoa;
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciado() {
        try {
            if (campoCRMInstanciado == null) {
                if (campoValorReflection == null) {
                    init();
                }
                tipoDadoCRM.getLabel();

                if (tipoDadoCRM == null) {
                    throw new UnsupportedOperationException("não foi possível determinar o formato do campo para o dado dinamico");
                }
                if (campoCRMInstanciado == null) {

                    campoCRMInstanciado = new CampoCRMInstanciado(campoValorReflection,
                            getTipoDadoCRM());
                }

            }
        } catch (Throwable t) {
            if (SBCore.isEmModoProducao()) {
                return new CampoNaoImplementado();
            } else {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo campo instanciado do Dado", t);
            }
        }
        return campoCRMInstanciado;
    }

    public Object getObjetoDaLista() {
        return null;
    }

    @Override
    public FabTipoAtributoObjeto getTipoCampo() {
        return getCampoInstanciado().getFabricaTipoAtributo();
    }

    @Override
    public void setNome(String pNome) {
        nome = pNome;
    }

    @Override
    public String getValorEnpacotado() {
        return valor;
    }

    @Override
    public void setValorEmpacotado(String pValor) {
        valor = pValor;
    }

    private class CampoCRMInstanciado extends CampoInstanciadoDinamico implements ItfCampoInstanciado {

        public CampoCRMInstanciado(Field campoComOValor,
                ItfAtributoObjetoEditavel campoDinamico
        ) {
            super(campoComOValor, campoDinamico);

        }

        @Override
        public String getLabel() {
            return getTipoDadoCRM().getLabel();
        }

        @Override
        public void setValor(Object pValor) {
            try {
                if (!isProximaTentativaDeAlteracaoBloqueado()) {

                    valor = (String) TipoAtributoMetodosBase.converterValorDinamicoEmString(this, pValor);

                    if (!UtilCRCStringValidador.isNuloOuEmbranco(getTipoDadoCRM().getCampoProspectoCorrespondente())) {
                        if (UtilCRCStringValidador.isNuloOuEmbranco(valor)) {
                            valor = (String) getValor();
                        }
                        try {
                            if (getTipoDadoCRM().getCampoProspectoCorrespondente().startsWith("atividade")) {
                                if (getAtividadeCRM() != null) {
                                    getCPinst(getTipoDadoCRM().getCampoProspectoCorrespondente()).setValorSeValido(pValor);
                                }
                            } else {
                                if (!getProspectoEmpresa().getCampoInstanciadoByNomeOuAnotacao(getTipoDadoCRM().getCampoProspectoCorrespondente()).isCampoNaoInstanciado()) {
                                    getProspectoEmpresa().getCPinst(getTipoDadoCRM().getCampoProspectoCorrespondente()).setValorSeValido(pValor);
                                }
                            }
                        } catch (ErroValidacao pErro) {
                            throw pErro;
                        } catch (Throwable t) {

                        }
                    }
                } else {
                    desbloquearProximaAlteracao();
                }

            } catch (ErroValidacao pErrocalidacao) {
                valor = TipoAtributoMetodosBase.converterValorDinamicoEmString(this, getValor());
                SBCore.getServicoMensagens().enviarMsgErroAoUsuario((pErrocalidacao.getMessage() + " Validando:" + getProspectoEmpresa().getCPinst(getTipoDadoCRM().getCampoProspectoCorrespondente()).getLabel()));

            } catch (Throwable ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro set-valor atributo dinamico" + this.toString(), ex);

            }
        }

        @Override
        public Object getValor() {
            if (!UtilCRCStringValidador.isNuloOuEmbranco(getTipoDadoCRM().getCampoProspectoCorrespondente())) {
                try {
                    if (!getTipoDadoCRM().getCampoProspectoCorrespondente().startsWith("atividadeCRM")) {
                        valor = (String) TipoAtributoMetodosBase.converterValorDinamicoEmString(this, getProspectoEmpresa().getCPinst(getTipoDadoCRM().getCampoProspectoCorrespondente()).getValor());
                    } else {
                        valor = (String) TipoAtributoMetodosBase.converterValorDinamicoEmString(this, getCPinst(getTipoDadoCRM().getCampoProspectoCorrespondente()).getValor());
                    }

                } catch (Throwable t) {

                }
            }
            return TipoAtributoMetodosBase.converterStringDinamicoEmValorReal(this, valor);

        }

        @Override
        public Object getParent() {
            return getInstancia();
        }

        @Override
        public boolean validarCampo() {
            return true;

        }

        @Override
        public ItfAtributoObjetoSB getAtributosCampoDinamico() {
            return getTipoDadoCRM();
        }

    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoByNomeOuAnotacao(String pNome) {
        if (pNome.equals("valor")) {
            return getCampoInstanciado();
        }
        return super.getCampoInstanciadoByNomeOuAnotacao(pNome); //To change body of generated methods, choose Tools | Templates.
    }

    private void init() {

        datahora = new Date();

        try {
            campoValorReflection = DadoCRM.class.getDeclaredField("valor");
            campoValorReflection.setAccessible(true);
        } catch (NoSuchFieldException | SecurityException ex) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "A reflexão do campo valor para o dado de CRM está inacessivel", ex);
        }

    }

    public DadoCRM() {
        super();
        init();
    }

    public DadoCRM(Pessoa pEmpresa) {
        super();
        prospectoEmpresa = pEmpresa;

        init();

    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getValor() {
        return valor;
    }

    public AtividadeCRM getAtividadeCRM() {
        return atividadeCRM;
    }

    public void setAtividadeCRM(AtividadeCRM atividadeCRM) {
        this.atividadeCRM = atividadeCRM;
    }

    /**
     * Utilize getCampoinstanciado().setValor
     *
     * @param valor
     * @deprecated
     */
    @Deprecated()
    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public ItfTipoAtributoSB getCampoPropriedades() {
        return tipoDadoCRM;
    }

    public Pessoa getProspectoEmpresa() {
        return prospectoEmpresa;
    }

    public void setProspectoEmpresa(Pessoa prospectoEmpresa) {
        this.prospectoEmpresa = prospectoEmpresa;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public TipoDadoCRM getTipoDadoCRM() {
        return tipoDadoCRM;
    }

    public void setTipoDadoCRM(TipoDadoCRM tipoDadoCRM) {
        this.tipoDadoCRM = tipoDadoCRM;
        nome = tipoDadoCRM.getLabel();
    }

    @Override
    public String toString() {

        String prosp = "prospNãoDeterminado";
        if (prospectoEmpresa != null) {
            prosp = String.valueOf(prospectoEmpresa.getId());
        }
        return getNome() + "-" + prosp;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(this.toString());
    }

    public TipoRelacionamento getTipoRelacionamentoVinculado() {
        return tipoRelacionamentoVinculado;
    }

    public void setTipoRelacionamentoVinculado(TipoRelacionamento tipoRelacionamentoVinculado) {
        this.tipoRelacionamentoVinculado = tipoRelacionamentoVinculado;
    }

    @PrePersist
    public void teste() {
        if (!SBCore.isEmModoProducao()) {
            System.out.println("Salvando dado dinamico");
        }
    }
}
