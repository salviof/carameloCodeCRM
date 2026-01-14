/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.FabTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.porteEmpresa.FabPorteProspectoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.porteEmpresa.Porte;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaCampoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.PropriedadesReflexaoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FieldComSerializacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public enum FabTipoDadoCRM implements ComoFabricaCampoDinamico {

    @InfoCampo(label = "Nome Contato Principal", tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    NOME,
    @InfoCampo(label = "Telefone", tipo = FabTipoAtributoObjeto.TELEFONE_GENERICO)
    TELEFONE,
    @InfoCampo(label = "Telefone Principal", tipo = FabTipoAtributoObjeto.TELEFONE_GENERICO)
    TELEFONE_PRINCIPAL,
    @InfoCampo(label = "Site Atual", tipo = FabTipoAtributoObjeto.SITE)
    SITE,
    @InfoCampo(label = "Porte da Empresa", tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, fabricaDeOpcoes = FabPorteProspectoEmpresa.class)
    @ManyToOne(targetEntity = Porte.class)
    PORTE,
    @InfoCampo(label = "Outros", tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    OUTROS_NAO_CADASTRADO,
    @InfoCampo(label = "Indicação", tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    INDICACAO,
    @InfoCampo(label = "Responsável", tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    RESPONSAVEL,
    @InfoCampo(label = "Motivo Prospect", tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    MOTIVO_PROSPECT,
    @InfoCampo(label = "Site concorrente 1", tipo = FabTipoAtributoObjeto.SITE)
    BR_SITE_CONCORRENTE1,
    @InfoCampo(label = "Site concorrente 2", tipo = FabTipoAtributoObjeto.SITE)
    BR_SITE_CONCORRENTE2,
    @InfoCampo(label = "Site concorrente 3", tipo = FabTipoAtributoObjeto.SITE)
    BR_SITE_CONCORRENTE3,
    @InfoCampo(label = "é mobile?", tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    BR_E_MOBILE,
    @InfoCampo(label = "é Otimizado?", tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    BR_E_OTIMIZADO,
    @InfoCampo(label = "Tem perfil e-comerce?", tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    BR_POSSIVEL_ECOMERCE,
    @InfoCampo(label = "Já inverstil em Marketing Digital?", tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    BR_JA_FEZ_MARKETING_DIGITAL,
    @InfoCampo(label = "Gastro anual com Publicidade", tipo = FabTipoAtributoObjeto.MOEDA_REAL)
    BR_GASTO_ANUAL_PUBLICIDADE,
    @InfoCampo(label = "Produto exclusivo ou personalizado?", tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    BR_PRODUTOESPECIALIZADO,
    @InfoCampo(label = "Desejo do cliente?", tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    BR_DESEJO_CLIENTE,
    @InfoCampo(label = "CNPJ", tipo = FabTipoAtributoObjeto.CNPJ)
    CNPJ,
    @InfoCampo(tipo = FabTipoAtributoObjeto.ARQUIVO_DE_ENTIDADE)
    PROPOSTA_ATUAL,
    @InfoCampo(label = "Ramo de atuação", tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    RAMO_ATUACAO;

    public static List<FabTipoDadoCRM> tipoDadosPorTipoAtividade(FabTipoAtividadeCRM pTipoAtividade) {

        return pTipoAtividade.getDadosObrigatorios();
    }

    @Override
    public TipoDadoCRM getRegistro() {
        String dadoDinamico = this.toString();
        try {
            MapaObjetosProjetoAtual.adcionarObjeto(TipoDadoCRM.class);
            Field campo = this.getClass().getDeclaredField(this.toString());
            PropriedadesReflexaoCampo prop = new PropriedadesReflexaoCampo(new FieldComSerializacao(campo));
            TipoDadoCRM novoTipoDadoCRM = new TipoDadoCRM();
            novoTipoDadoCRM.setId((long) this.ordinal() + 1);
            prop.aplicarAnotacoesEmAtributo(novoTipoDadoCRM);

            if (this == PORTE) {
                System.out.println(novoTipoDadoCRM.getNomeClasseAtributoDeclarado());
            }

            novoTipoDadoCRM.setObjetoReferente(PessoaJuridica.class.getSimpleName());
            return novoTipoDadoCRM;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro instanciando cmapo dinamico" + this.toString(), t);
            try {
                Field campo = this.getClass().getDeclaredField(dadoDinamico);

                PropriedadesReflexaoCampo prop = new PropriedadesReflexaoCampo(new FieldComSerializacao(campo));
                TipoDadoCRM novoTipoDadoCRM = new TipoDadoCRM();
                prop.aplicarAnotacoesEmAtributo(novoTipoDadoCRM);
            } catch (Throwable tt) {
                return null;
            }

            return null;
        }
    }

    public ManyToOne getManyToOne() {
        try {
            Field campo = this.getClass().getDeclaredField(this.toString());
            ManyToOne manyToOle = campo.getAnnotation(ManyToOne.class);
            return manyToOle;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo Info Campo do campo dinamico" + this.toString(), t);
            return null;
        }
    }

}
