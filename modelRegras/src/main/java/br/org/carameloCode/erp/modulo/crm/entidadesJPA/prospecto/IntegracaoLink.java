/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import cucumber.api.Transpose;
import javax.persistence.Transient;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(plural = "Integrações Link", tags = "Integrações")
public class IntegracaoLink extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.URL)
    private String imagem;
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private Long codigoPessoa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoDadoCrmLinkIntegracao tipoDado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.URL)
    @InfoCampoValidadorLogico()
    @InfoCampoValorLogico(nomeCalculo = "url")
    private String url;

    @Transient
    private DadoCRM dadoCRM;

    public IntegracaoLink() {
    }

    public DadoCRM getDadoCRM() {
        return dadoCRM;
    }

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {Pessoa.class})
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        Pessoa pessoa = getParametroInicialEnviado(Pessoa.class, parametros);
        TipoDadoCrmLinkIntegracao tipoLinkIntegracao = getParametroInicialEnviado(TipoDadoCrmLinkIntegracao.class, parametros);
        dadoCRM = new DadoCRM(pessoa);
        dadoCRM.setTipoDadoCRM(tipoLinkIntegracao);
        // String url = dadoCRM.getValor();

        setImagem(tipoLinkIntegracao.getImgPequena());
        //integracador.setUrl(url);
        setTipoDado(tipoLinkIntegracao);
        setCodigoPessoa(pessoa.getId());
        setNome(tipoLinkIntegracao.getNome());

    }

    public boolean isTemUrl() {

        return !UtilCRCStringValidador.isNuloOuEmbranco(url);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public TipoDadoCrmLinkIntegracao getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(TipoDadoCrmLinkIntegracao tipoDado) {
        this.tipoDado = tipoDado;
    }

}
