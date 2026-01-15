/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.dadosDinamicos;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.FabDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.FabTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class FabTipoDadoCRMTest extends TesteCRMCarameloCode {

    public FabTipoDadoCRMTest() {
    }

    /**
     * Test of values method, of class FabTipoDadoCRM.
     */
    @Test
    public void testValues() {

        for (FabTipoDadoCRM tipodado : FabTipoDadoCRM.values()) {
            System.out.println(tipodado.getRegistro().getValorMaximo());
        }

        List<TipoDadoCRM> tiposDado = UtilSBPersistencia.getListaTodos(TipoDadoCRM.class, getEMTeste());

        for (TipoDadoCRM tipo : tiposDado) {

            System.out.println(tipo.getLabel());
            System.out.println(tipo.getValorMaximo());
            System.out.println(tipo.getValorMinimo());
            DadoCRM dado = FabDadoCRM.getNovoDadoDeAtividade(null, new AtividadeCRM(), tipo);
            System.out.println(dado.getCampoInstanciado());
            System.out.println(dado.getCampoInstanciado().getLabelPadrao());
            System.out.println(dado.getCampoInstanciado().getNomeClasseAtributoDeclarado());
            switch (dado.getCampoInstanciado().getFabricaTipoAtributo()) {
                case NOME:
                    break;
                case NOME_LONGO:
                    break;
                case IMG_PEQUENA:
                    break;
                case IMG_MEDIA:
                    break;
                case IMG_GRANDE:
                    break;
                case DESCRITIVO:
                    break;
                case ID:
                    break;
                case QUANTIDADE:
                    break;
                case LATITUDE:
                    break;
                case LONGITUDE:
                    break;
                case LC_LOGRADOURO:
                    break;
                case LCCEP:
                    break;
                case LC_BAIRRO:
                    break;
                case LC_CIDADE:
                    break;
                case LC_LOCALIDADE:
                    break;
                case LC_UNIDADE_FEDERATIVA:
                    break;
                case SENHA:
                    break;
                case SENHA_SEGURANCA_MAXIMA:
                    break;
                case LC_COMPLEMENTO:
                    break;
                case LC_CAMPO_ABERTO:
                    break;
                case HTML:
                    break;
                case HTML_TEMPLATE:
                    break;
                case CHART_VALOR:
                    break;
                case CHART_LABEL:
                    break;
                case CHART_CATEGORIA:
                    break;
                case CALENDARIO:
                    break;
                case DATAHORA:
                    break;
                case DATA:
                    break;
                case HORA:
                    break;
                case TELEFONE_FIXO_NACIONAL:
                    break;
                case TELEFONE_FIXO_INTERNACIONAL:
                    break;
                case TELEFONE_CELULAR:
                    break;
                case TELEFONE_GENERICO:
                    break;
                case MOEDA_REAL:
                    break;
                case MOEDA_DOLAR:
                    break;
                case PERCENTUAL:
                    break;
                case OBJETO_DE_UMA_LISTA:
                    break;
                case ENUM_FABRICA:

                    System.out.println("Campo instanciado tipo enum fabrica ----------------------->" + dado.getCampoInstanciado().getLabel());
                    //  CampoInstanciadoEnumFabricaObjeto campo = dado.getCampoInstanciado().getComoEnumFabricaObjeto();
                    //   System.out.println(campo.getListaOpcoesObjeto());

                    break;
                case LISTA_OBJETOS_PUBLICOS:
                    break;
                case LISTA_OBJETOS_PARTICULARES:
                    break;
                case TEXTO_SIMPLES:
                    break;
                case VERDADEIRO_FALSO:
                    break;
                case COR:
                    break;
                case EMAIL:
                    break;
                case SITE:
                    break;
                case URL:
                    break;
                case RESPONSAVEL:
                    break;
                case NOME_COMPLETO:
                    break;
                case CNPJ:
                    break;
                case CPF:
                    break;
                case INSCRICAO_ESTADUAL:
                    break;
                case INSCRIACAO_MUNICIPAL:
                    break;
                case REG_DATAALTERACAO:
                    break;
                case REG_DATAINSERCAO:
                    break;
                case REG_USUARIO_ALTERACAO:
                    break;
                case REG_USUARIO_INSERCAO:
                    break;
                case REG_ATIVO_INATIVO:
                    break;
                case CODIGO_DE_BARRAS:
                    break;
                case ICONE:
                    break;
                case SEGURANCA_ATIVA:
                    break;
                case ARQUIVO_DE_ENTIDADE:
                    break;
                case LC_LOCALIZACAO:
                    break;
                case CAMPO_SEPARADOR:
                    break;
                case GRUPO_CAMPO:
                    break;
                case GRUPOS_DE_CAMPO:
                    break;
                case FORMULARIO_DE_ACAO:
                    break;
                case TIPO_PADRAO_POR_DECLARACAO:
                    break;
                default:
                    throw new AssertionError(dado.getCampoInstanciado().getFabricaTipoAtributo().name());

            }
        }

    }

}
