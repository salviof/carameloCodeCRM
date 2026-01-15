package br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.telefone.Telefone;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Template Whatsapp", plural = "Templates Mensagem Whatzapp", icone = "fa fa-whatsapp")
public class TipoMensagemMktWhatsApp extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, obrigatorio = true)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = Telefone.class)
    private Telefone telefone;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, obrigatorio = true)
    private String slugTemplate;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean envioUnico;

    @OneToMany(mappedBy = "tipoMensagem")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @OrderBy("codigoParametro ASC")
    private List<ParametroMensagemWtzap> parametros;

    @InfoCampo(tipo = FabTipoAtributoObjeto.URL)
    private String urlMensagemPreview;

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSlugTemplate() {
        return slugTemplate;
    }

    public void setSlugTemplate(String slugTemplate) {
        this.slugTemplate = slugTemplate;
    }

    public List<ParametroMensagemWtzap> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroMensagemWtzap> parametros) {
        this.parametros = parametros;
    }

    public boolean isEnvioUnico() {
        return envioUnico;
    }

    public void setEnvioUnico(boolean envioUnico) {
        this.envioUnico = envioUnico;
    }

    public String getUrlMensagemPreview() {
        return urlMensagemPreview;
    }

    public void setUrlMensagemPreview(String urlMensagemPreview) {
        this.urlMensagemPreview = urlMensagemPreview;
    }

}
