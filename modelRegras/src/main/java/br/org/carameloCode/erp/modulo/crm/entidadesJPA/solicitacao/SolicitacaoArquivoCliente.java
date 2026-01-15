/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoal.ContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Solicitação de arquivo do cliente", plural = "Solicitaçoẽs de arquivos do cliente")
public class SolicitacaoArquivoCliente extends Solicitacao {

    @ManyToOne
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    private CategoriaArquivoCliente categoriaArqCliente;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Contato", obrigatorio = true)
    @ManyToOne(targetEntity = ContatoPessoal.class)
    private ContatoPessoal contatoPessoa;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {Pessoa.class, UsuarioCRM.class})
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        setPessoa(getParametroInicialEnviado(Pessoa.class, parametros));

        setUsuarioSolicitante((UsuarioCRM) SBCore.getUsuarioLogado());
    }

    public SolicitacaoArquivoCliente() {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_NOVO_DOCUMENTO_CLIENTE.getRegistro());
    }

    public CategoriaArquivoCliente getCategoriaArqCliente() {
        return categoriaArqCliente;
    }

    public void setCategoriaArqCliente(CategoriaArquivoCliente categoriaArqCliente) {
        this.categoriaArqCliente = categoriaArqCliente;
    }

    public ContatoPessoal getContatoPessoa() {
        return contatoPessoa;
    }

    public void setContatoPessoa(ContatoPessoal contatoPessoa) {
        this.contatoPessoa = contatoPessoa;
    }

}
