package br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.apresentacao.DocumentoApresentacao;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@InfoObjetoSB(descricao = "Serviço Disponivel", icone = "fa fa-coffee", plural = "Serviços Disponiveis", tags = "Serviço Disponivel")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoCobranca")
@EntityListeners(ListenerEntidadePadrao.class)
public class TipoServico extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(label = "Id", descricao = "Identificador", tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(label = "Nome", descricao = "Nome do Serviço", tipo = FabTipoAtributoObjeto.NOME, obrigatorio = true)
    private String nome;

    @InfoCampo(label = "Descrição", descricao = "Descrição do Serviço", tipo = FabTipoAtributoObjeto.DESCRITIVO)
    @Column(length = 8000, columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoCobranca;

    @InfoCampo(label = "Apresentação", descricao = "Apresentação descritiva do Serviço", tipo = FabTipoAtributoObjeto.DESCRITIVO)
    @Column(length = 8000, columnDefinition = "TEXT")
    private String descricaoApresentacao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.URL, label = "Url Descricao")
    private String urlDetalhes;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoValorLogico(nomeCalculo = "Integrado com Fatura")
    private boolean temIntegracaoFatura;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "Código sistema Fatura")
    private String codigoItengracaoFatura;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampoValidadorLogico()
    private boolean geraPgtoRecorrente;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampoValidadorLogico()
    private boolean geraPgtoSazonal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampoValorLogico(nomeCalculo = "Tipo de pagamento definido")
    private boolean foiDefinidoTipoPgto;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tipoSevico_documentoApresentacao",
            joinColumns = @JoinColumn(name = "tipoServico_id"),
            inverseJoinColumns = @JoinColumn(name = "documentoApresentacao_id"))
    @InfoCampo(label = "Apresentações", tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<DocumentoApresentacao> apresentacoes;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ARQUIVO_DE_ENTIDADE, label = "Arquivo Apresentação ")
    private String arquivoApresentacao;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoApresentacao() {
        return descricaoApresentacao;
    }

    public void setDescricaoApresentacao(String descricaoApresentacao) {
        this.descricaoApresentacao = descricaoApresentacao;
    }

    public String getUrlDetalhes() {
        return urlDetalhes;
    }

    public void setUrlDetalhes(String urlDetalhes) {
        this.urlDetalhes = urlDetalhes;
    }

    public String getArquivoApresentacao() {
        return arquivoApresentacao;
    }

    public void setArquivoApresentacao(String arquivoApresentacao) {
        this.arquivoApresentacao = arquivoApresentacao;
    }

    public List<DocumentoApresentacao> getApresentacoes() {
        return apresentacoes;
    }

    public void setApresentacoes(List<DocumentoApresentacao> apresentacoes) {
        this.apresentacoes = apresentacoes;
    }

    public boolean isGeraPgtoRecorrente() {
        return geraPgtoRecorrente;
    }

    public void setGeraPgtoRecorrente(boolean geraPgtoRecorrente) {
        this.geraPgtoRecorrente = geraPgtoRecorrente;
    }

    public boolean isGeraPgtoSazonal() {
        return geraPgtoSazonal;
    }

    public void setGeraPgtoSazonal(boolean geraPgtoSazonal) {
        this.geraPgtoSazonal = geraPgtoSazonal;
    }

    public boolean isFoiDefinidoTipoPgto() {
        return foiDefinidoTipoPgto;
    }

    public void setFoiDefinidoTipoPgto(boolean foiDefinidoTipoPgto) {
        this.foiDefinidoTipoPgto = foiDefinidoTipoPgto;
    }

    public boolean isTemIntegracaoFatura() {
        return temIntegracaoFatura;
    }

    public void setTemIntegracaoFatura(boolean temIntegracaoFatura) {
        this.temIntegracaoFatura = temIntegracaoFatura;
    }

    public String getCodigoItengracaoFatura() {
        return codigoItengracaoFatura;
    }

    public void setCodigoItengracaoFatura(String codigoItengracaoFatura) {
        this.codigoItengracaoFatura = codigoItengracaoFatura;
    }

    public String getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(String tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

}
