package com.itau.transferencia.adapters.inbound.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/entrypoint")
public class WebFluxEntryPoint {

    @GetMapping("/sequential")
    public Mono<String> getSequentialResult() {
        return Mono.just("Estou funcionando perfeitamente de forma sequencial");
    }

    @GetMapping("/parallel")
    public Flux<String> getParallelResult() {
        return Flux.just("Estou funcionando perfeitamente de forma paralela");
    }
}
