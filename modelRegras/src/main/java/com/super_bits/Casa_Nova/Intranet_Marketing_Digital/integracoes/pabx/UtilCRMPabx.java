package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.pabx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilCRMPabx {
    public UtilCRMPabx() {
    }

    private static final Pattern NUMERO_EXTERNO_PATTERN =
            Pattern.compile("<(\\d{8,})>");

    private static final Pattern RAMAL_NO_NOME_PATTERN =
            Pattern.compile("-(\\d{2,6})$");

    public static ResultadoLigacao interpretar(String pClid) {
        if (pClid == null || pClid.isEmpty()) {
            return new ResultadoLigacao(TipoChamada.DESCONHECIDA, null, null);
        }
        String clidTrim = pClid.trim();
        String origem = clidTrim.contains("<") ? clidTrim.substring(0, clidTrim.indexOf("<")).trim() : clidTrim;
        String numeroExterno = extrairNumeroExterno(clidTrim);
        String ramal = extrairRamal(origem);

        TipoChamada tipo = resolverTipo(origem);
        return new ResultadoLigacao(tipo, numeroExterno, ramal);
    }

    private static String extrairNumeroExterno(String clid) {
        Matcher m = NUMERO_EXTERNO_PATTERN.matcher(clid);
        return m.find() ? m.group(1) : null;
    }

    private static String extrairRamal(String origem) {
        if (origem.matches("\\d{2,6}")) {
            return origem;
        }
        Matcher matcher = RAMAL_NO_NOME_PATTERN.matcher(origem);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static TipoChamada resolverTipo(String origem) {
        boolean contemLetras = origem.matches(".*[a-zA-Z].*");
        boolean numeroCurto = origem.matches("\\d{2,6}");
        boolean numeroLongo = origem.matches("\\d{8,}");

        if (contemLetras || numeroCurto) {
            return TipoChamada.REALIZADA;
        }

        if (numeroLongo) {
            return TipoChamada.RECEBIDA;
        }

        return TipoChamada.DESCONHECIDA;
    }
}
