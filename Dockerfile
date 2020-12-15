FROM eu.gcr.io/packlink-tools/packlink-java-11:jre-latest

WORKDIR /app

COPY target/universal/stage .
COPY application.yml.ctmpl .
COPY init.sh .

RUN apk add --no-cache bash && \
    chmod +x init.sh

EXPOSE 80

CMD ["./init.sh"]
