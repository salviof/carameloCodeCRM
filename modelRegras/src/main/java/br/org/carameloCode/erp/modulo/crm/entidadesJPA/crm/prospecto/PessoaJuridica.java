package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexadoEmailEmConteudo.ArquivoAnexadoEmailEmConteudo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.porteEmpresa.Porte;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.LocalizacaoPostavel;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = {"Empresa"}, plural = "Empresas ")
@SuppressWarnings("ConsistentAccessType")
public class PessoaJuridica extends Pessoa {

    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_COMPLEMENTO, label = "Complemento")
    private String complemento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, label = "Site")
    @Column(unique = true)
    @InfoCampoValidadorLogico()
    @InfoCampoValorLogico(nomeCalculo = "Site", somenteLeitura = false)
    private String site;

    @InfoCampo(tipo = FabTipoAtributoObjeto.CNPJ, label = "Cnpj")
    @Column(unique = true)
    private String cnpj;

    @InfoCampo(label = "Origem Prospecto", tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = OrigemProspecto.class, optional = true)
    private OrigemProspecto origem;

    @OneToMany(mappedBy = "prospecto", targetEntity = ContatoProspecto.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES, camposExibidosEmLista = {"nome", "email"})
    private List<ContatoProspecto> contatosProspecto;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Arquivos Enviados", atualizarSempreQueSalvar = false)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<ArquivoAnexado> arquivosEnviados;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Arquivos recebidos")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<ArquivoAnexadoEmailEmConteudo> arquivosRecebidos;

    private String caminhoLocalImagem;
    private String caminhoUrlImagem;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean cadastrocompleto;

    private String enderecoSecundario;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Tipo Empresa", obrigatorio = false)
    @ManyToOne(targetEntity = TipoEmpresa.class)
    private TipoEmpresa tipoEmpresa;
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Porte", obrigatorio = false)
    @ManyToOne(targetEntity = Porte.class)
    private Porte porte;

    @Override
    public String getImgPequena() {
        return super.getImgPequena(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     *
     * Carrega a empresa localizando-a através das informações de contato, que
     * podem ser: NOME, TELEFONE, SITE
     *
     *
     * @param pTelefon_nome_cnpj Parametro
     * @param pEm Entity Manager
     * @return
     */
    public boolean loadEmpresabyContato(String pTelefon_nome_cnpj, EntityManager pEm) {
        Object registroEncontrado = UtilSBPersistencia.getEmpresa(this.getClass(), pTelefon_nome_cnpj, pEm);
        if (registroEncontrado == null) {
            return false;
        }

        copiaDados(registroEncontrado);
        return true;
    }

    public PessoaJuridica() {
        super();

    }

    public boolean isDadoJaColetado(TipoDadoCRM pTipo) {

        for (AtividadeCRM atividade : getAtividadesRealizadas()) {
            if (atividade != null) {
                atividade.ajustarColeta();
                for (DadoCRM dado : atividade.getDadosColetados()) {
                    if (dado.getTipoDadoCRM().equals(pTipo)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getDominio() {
        if (UtilCRCStringValidador.isNuloOuEmbranco(site)) {
            return null;
        } else {
            if (site.startsWith("http://")) {
                return site.substring(6);
            }
            if (site.startsWith("https://")) {
                return site.substring(7);
            }
            return site;
        }

    }

    public String getCaminhoLocalImagem() {
        return caminhoLocalImagem;
    }

    public void setCaminhoLocalImagem(String caminhoLocalImagem) {
        this.caminhoLocalImagem = caminhoLocalImagem;
    }

    public boolean isCadastrocompleto() {
        return cadastrocompleto;
    }

    public void setCadastrocompleto(boolean cadastrocompleto) {
        this.cadastrocompleto = cadastrocompleto;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public String getEnderecoSecundario() {
        return enderecoSecundario;
    }

    public void setEnderecoSecundario(String enderecoSecundario) {
        this.enderecoSecundario = enderecoSecundario;
    }

    public void setSite(String site) {

        this.site = site;
    }

    public String getCaminhoUrlImagem() {
        return caminhoUrlImagem;
    }

    public void setCaminhoUrlImagem(String caminhoUrlImagem) {
        this.caminhoUrlImagem = caminhoUrlImagem;
    }

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    @Override
    public String getComplemento() {
        return complemento;
    }

    @Override
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public List<ContatoProspecto> getContatosProspecto() {
        return contatosProspecto;
    }

    public OrigemProspecto getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemProspecto origemProspecto) {
        this.origem = origemProspecto;
    }

    @Override
    public UsuarioCRM getUsuarioInsersao() {
        return (UsuarioCRM) super.getUsuarioInsersao(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSite() {
        return site;
    }

    @Override
    public LocalizacaoPostavel getLocalizacao() {
        return super.getLocalizacao(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocalizacao(LocalizacaoPostavel localizacao) {
        super.setLocalizacao(localizacao); //To change body of generated methods, choose Tools | Templates.
    }

}
