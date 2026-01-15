/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.util;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.List;
import javax.persistence.EntityManager;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

/**
 *
 * @author salvio
 */
public class UtilCRMValidacoesEspeciais {

    public static boolean validarEmailCliente(Pessoa pessoa, String pEmail) throws ErroValidacao {
        if (UtilCRCStringValidador.isNuloOuEmbranco(pEmail)) {

            return false;

        }

        validarEmail((String) pEmail);

        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        ErroValidacao erroValidacao = null;
        try {
            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(UsuarioCRM.class, em);
            consulta.addcondicaoCampoIgualA("email", pEmail);
            List<UsuarioCRM> usuarios = consulta.gerarResultados();
            if (!usuarios.isEmpty()) {
                for (UsuarioCRM usr : usuarios) {
                    if (!usr.isUmUsuarioDoCliente()) {
                        throw new ErroValidacao("Existe um usuário de atendimento com este e-mail");
                    } else {

                        if (!usr.getComoUsuarioCliente().getRepresentanteLegal().getId().equals(pessoa.getId())) {
                            throw new ErroValidacao("Já existe um usuário com este email em " + usr.getUsuarioComoUsrCliente().getRepresentanteLegal().getNome());
                        }

                    }
                }
            }
            return true;
        } catch (ErroValidacao erro) {
            erroValidacao = erro;
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        if (erroValidacao != null) {
            throw erroValidacao;
        }
        return true;
    }

    public static void validarEmail(String pEmail) throws ErroValidacao {

        if (UtilCRCStringValidador.isNuloOuEmbranco(pEmail)) {
            return;

        }
        String email = pEmail;
        if (!email.contains("@")) {
            throw new ErroValidacao("O e-mail não parece válido");
        }
        String dominio = email.substring(email.indexOf("@") + 1);

        try {
            Record[] records = new Lookup(dominio, Type.NS).run();
            if (records == null || records.length == 0) {
                throw new ErroValidacao("O domínio [" + dominio + "] não foi registrado");
            }
        } catch (TextParseException ex) {
            throw new ErroValidacao("O domínio [" + dominio + "] não é valido");
        }

        try {
            Record[] records = new Lookup(dominio, Type.MX).run();
            if (records == null || records.length == 0) {
                throw new ErroValidacao("O domínio [" + dominio + "] não contem caixas postais");
            }
        } catch (TextParseException ex) {
            throw new ErroValidacao("O domínio [" + dominio + "] não contem caixas postais");
        }
    }

}
