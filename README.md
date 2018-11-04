# Number To Words

`number2words` is a demo project showcasing [Spring Cloud Consul](https://cloud.spring.io/spring-cloud-consul)
and [Spring Boot](https://spring.io/projects/spring-boot) with a microservices architecture.

This app is made of two components:
 - `number2words-backend`: a microservice exposing a REST endpoint, converting
 a number to words (`12` -> `twelve`);
 - `number2words-frontend`: a microservice exposing an API endpoint, connected to backend
 instances.

Each microservice connects to a [Consul](https://www.consul.io) server, which is used as
a service registry.
You may start several instances of `number2words-backend`: all these instances will be
visible to `number2words-frontend`.

This app is also powered by [Spring Cloud Netflix](https://spring.io/projects/spring-cloud-netflix)
to build reliable service clients, using:
 - [Netflix Ribbon](https://github.com/Netflix/ribbon): a client-side load balancer to
 distribute load among microservices;
 - [Netflix Hystrix](https://github.com/Netflix/Hystrix): a latency and fault tolerance
 library;
 - [Open Feign](https://github.com/OpenFeign/feign): a REST client library that allows
 you to consume HTTP APIs with minimal overhead.

## How to use it?

Compile this project using Maven and JDK 8:
```shell
$ ./mvnw clean package
```

Start a local Consul server, using the [Consul Docker image](https://hub.docker.com/_/consul/):
```shell
$ docker run --rm --name consul -e CONSUL_BIND_INTERFACE=eth0 -p "8500:8500/tcp" consul:1.3.0
```

Finally, start microservices:
```shell
$ java -jar number2words-backend/target/number2words-backend.jar
$ java -jar number2words-frontend/target/number2words-frontend.jar
```

Feel free to start many `number2words-backend` instances: a random port is used, so that
you can even start many processes on your host.

You are now ready to use the conversion endpoint:
```shell
$ curl -s http://localhost:8080/api/convert?n=1234 | jq
{
  "number": 1234,
  "words": "one thousand two hundred thirty-four"
}
```

On the [Consul dashboard](http://localhost:8500), you should be able to see
registered services:
<img src="https://imgur.com/download/WF6JuzC"/>

In case a service disappears, the Consul server takes care of removing the faulty process
in the registry. On the client side, API calls are automatically sent to healthy services.

Consul periodically checks service availability by executing service healthchecks:
<img src="https://imgur.com/download/NvhVFAJ"/>

Service registration & healthcheck setup are automatically done by Spring Cloud Consul.

## Contribute

Contributions are always welcome!

Feel free to open issues & send PR.

## License

Copyright &copy; 2018 Pivotal Software, Inc.

This project is licensed under the [Apache Software License version 2.0](https://www.apache.org/licenses/LICENSE-2.0).


