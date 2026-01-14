/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.objetoNativo.comunicacao.RespostaComunicacao;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Solicitação ", plural = "Solicitações de arquivos para equipe", icone = "fa fa-upload")
@EntityListeners(ListenerEntidadePadrao.class)
public class SolicitacaoArquivoEquipe extends Solicitacao {

    @ManyToOne(targetEntity = CategoriaArquivoEquipe.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    private CategoriaArquivoEquipe categoriaArqEquipe;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {Pessoa.class, UsuarioCRM.class})
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        setPessoa(getParametroInicialEnviado(Pessoa.class, parametros));
        UsuarioCRM userPr = getParametroInicialEnviado(UsuarioCRM.class, parametros);
        String emailusrpr = userPr.getEmail();
        setUsuarioSolicitado(userPr);
        setUsuarioSolicitante((UsuarioCRM) SBCore.getUsuarioLogado());
    }

    public SolicitacaoArquivoEquipe() {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_NOVO_DOCUMENTO_EQUIPE.getRegistro());
    }

    public CategoriaArquivoEquipe getCategoriaArqEquipe() {
        return categoriaArqEquipe;
    }

    public void setCategoriaArqEquipe(CategoriaArquivoEquipe categoriaArqEquipe) {
        this.categoriaArqEquipe = categoriaArqEquipe;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ItfRespostaComunicacao> getRepostasPossiveis() {
        respostas = new ArrayList<>();
        respostas.add(new RespostaComunicacao(this, FabTipoRespostaComunicacao.ENVIAR_INFORMACAO.getRegistro()));
        return respostas;
    }

}
