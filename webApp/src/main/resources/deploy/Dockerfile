FROM jetty:9.4-jre8

MAINTAINER Salvio Furbino Elias <salviof@gmail.com>

USER root

# === Correção para Stretch archive (2025+) ===
RUN sed -i 's/deb.debian.org/archive.debian.org/g' /etc/apt/sources.list && \
    sed -i 's/security.debian.org/archive.debian.org/g' /etc/apt/sources.list && \
    sed -i '/stretch-updates/d' /etc/apt/sources.list && \
    echo 'Acquire::Check-Valid-Until "false";' > /etc/apt/apt.conf.d/99no-check-valid-until

# === Instala LibreOffice + tzdata (importante para timezone) ===
RUN apt-get update && \
    apt-get install -y --no-install-recommends --allow-unauthenticated \
        libreoffice-common libreoffice-writer libreoffice-java-common ure \
        fonts-liberation fonts-dejavu-core fonts-crosextra-carlito fonts-crosextra-caladea \
        tzdata && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# === Ajuste de HOME para o usuário Jetty ===
RUN mkdir -p /var/lib/jetty/home && \
    chown jetty:jetty /var/lib/jetty/home && \
    usermod -d /var/lib/jetty/home jetty
ENV HOME=/var/lib/jetty/home

# === Timezone correto ===
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
    echo $TZ > /etc/timezone

# === Atualiza cache das fontes ===
RUN fc-cache -f -v

# === Seus arquivos ===
COPY Intranet_Marketing_Digital.war /var/lib/jetty/webapps/
COPY crm.xml /var/lib/jetty/webapps/
COPY CRMCasaNova.Homologacao.sql /home/git/publicados/Intranet_Marketing_Digital/

# === Pasta escrita pela aplicação ===
RUN mkdir -p /home/servidorSBFW/arquivosDeEntidade && \
    chown jetty:jetty /home/servidorSBFW/arquivosDeEntidade

VOLUME /home/servidorSBFW/arquivosDeEntidade

# Permanece root para evitar erros em bind mount
USER root

EXPOSE 8080
