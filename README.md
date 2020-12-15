# my-packlink-project

## Features



## Infrastructure and tool dependencies

This application has the following infrastructure dependencies:

- Consul (Docker image)
- Consul-template (Docker image)

## Developing

First of all, please remember to create your own fork!

```shell
git clone https://github.com/your_repo/my-packlink-project
cd my-packlink-project
```

That's all, now you are ready!!!

### Testing

> Note that, thanks to test-containers, you don't need any infrastructure component to run integration test on your local environment

To run Unit Tests execute:

```shell
./sbt test
```
To run Integration Tests execute:

```shell
./sbt it:test
``` 

### Building

```shell
./sbt stage
```

## Running

```shell
./bin/my-packlink-project --spring.config.location=file:application.yml
```
Remember that you'll need to populate properly the application.yml file with:

```shell
consul-template -consul consul.service.consul:8500 -template "./application.yml.ctmpl:./application.yml" -once
```