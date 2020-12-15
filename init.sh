#!/bin/bash
source /.packlinkjavarc

run pli config check -t ./application.yml.ctmpl -c consul.service.consul:8500
run consul-template -consul consul.service.consul:8500 -template "./application.yml.ctmpl:./application.yml" -once
export JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/java_pid%p.hprof"
run ./bin/my-packlink-project --spring.config.location=file:application.yml
