/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.cliente.satisfacao.FabSatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.cliente.satisfacao.SatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Usuario cliente", plural = "Usuarios de clientes")
public class UsuarioCrmCliente extends UsuarioCRM {

    @ManyToOne(targetEntity = Pessoa.class, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Representante Legal")
    private Pessoa representanteLegal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean clienteVerificado = true;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValorLogico(nomeCalculo = "contatoClienteVinculado")
    @ManyToOne(targetEntity = ContatoProspecto.class, fetch = FetchType.LAZY)
    private ContatoProspecto contatoClienteVinculado;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date dataHoraPrecadastro;

    @InfoCampoValorLogico(nomeCalculo = "codigoAcessoRC")
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String chavesecretaRC;

    @ManyToOne(targetEntity = SatisfacaoCliente.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private SatisfacaoCliente satisfacao = FabSatisfacaoCliente.SATISFEITO.getRegistro();

    @OneToMany(mappedBy = "usuarioCliente", cascade = {CascadeType.REMOVE})
    private List<ChamadoCliente> chamados;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValorLogico(nomeCalculo = "Metadados")
    private MetadadoUsuarioCliente metadados;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Reserva mais próxima")
    private ReservaHorario reservaAtivaMaisProxima;

    @InfoCampoValorLogico(nomeCalculo = "Reserva mais próxima")
    @Transient
    private boolean possuiReservaAtiva;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = ContatoProspecto.class)
    public void prepararNovoObjeto(Object... parametros) {

        ContatoProspecto contato = null;
        try {
            contato = getParametroInicialEnviado(ContatoProspecto.class, parametros);
        } catch (ErroPreparandoObjeto ex) {
            Logger.getLogger(UsuarioCrmCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        setRepresentanteLegal(contato.getProspecto());
        setTelefone(contato.getCelular());
        setEmail(contato.getEmail());
        setNome(contato.getNome());
        setApelido(contato.getEmail() + contato.getId());
        contatoClienteVinculado = contato;
        setGrupo(FabGruposIntranetCasaNova.CRM_CLIENTE.getRegistro());

    }

    @Override
    public String getImgPequena() {
        if (!isTemImagemPequenaAdicionada()) {
            return getRepresentanteLegal().getImgPequena();
        } else {
            return super.getImgPequena(); //To change body of generated methods, choose Tools | Templates.

        }
    }

    public Pessoa getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(Pessoa representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public boolean isVerificado() {
        return clienteVerificado = true;
    }

    public Date getDataHoraPrecadastro() {
        return dataHoraPrecadastro;
    }

    public void setDataHoraPrecadastro(Date dataHoraPrecadastro) {
        this.dataHoraPrecadastro = dataHoraPrecadastro;
    }

    public String getChavesecretaRC() {
        return chavesecretaRC;
    }

    public void setChavesecretaRC(String chavesecretaRC) {
        this.chavesecretaRC = chavesecretaRC;
    }

    @Override
    public boolean isUmUsuarioDoCliente() {
        return true;
    }

    public SatisfacaoCliente getSatisfacao() {
        return satisfacao;
    }

    public void setSatisfacao(SatisfacaoCliente satisfacao) {
        this.satisfacao = satisfacao;
    }

    public MetadadoUsuarioCliente getMetadados() {
        return metadados;
    }

    public void setMetadados(MetadadoUsuarioCliente metadados) {
        this.metadados = metadados;
    }

    public ContatoProspecto getContatoClienteVinculado() {
        return contatoClienteVinculado;
    }

    public void setContatoClienteVinculado(ContatoProspecto contatoClienteVinculado) {
        this.contatoClienteVinculado = contatoClienteVinculado;
    }

    public boolean isClienteVerificado() {
        return clienteVerificado;
    }

    public void setClienteVerificado(boolean clienteVerificado) {
        this.clienteVerificado = clienteVerificado;
    }

    public ReservaHorario getReservaAtivaMaisProxima() {
        return reservaAtivaMaisProxima;
    }

    public void setReservaAtivaMaisProxima(ReservaHorario reservaAtivaMaisProxima) {
        this.reservaAtivaMaisProxima = reservaAtivaMaisProxima;
    }

    public boolean isPossuiReservaAtiva() {
        return possuiReservaAtiva;
    }

    public void setPossuiReservaAtiva(boolean possuiReservaAtiva) {
        this.possuiReservaAtiva = possuiReservaAtiva;
    }

    public List<ChamadoCliente> getChamados() {
        return chamados;
    }

    public void setChamados(List<ChamadoCliente> chamados) {
        this.chamados = chamados;
    }

}
