package com.adeliosys.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/count-items")
    public Mono<Result> count() {
        return itemRepository.count().map(Result::new);
    }

    @GetMapping("/insert-items")
    public Mono<Void> insert(
            @RequestParam(required = false) boolean optimized,
            @RequestParam(required = false, defaultValue = "6") int count,
            @RequestParam(required = false, defaultValue = "3") int batchSize) {
        // First generate a flux of items
        Flux<Item> items = Flux.fromStream(IntStream.range(0, count).mapToObj(i -> new Item()));

        // Then insert them into the database
        if (optimized) {
            return items.buffer(batchSize).flatMap(itemRepository::insert).then();
        } else {
            return itemRepository.insert(items).then();
        }
    }

    @GetMapping("/delete-items")
    public Mono<Void> delete() {
        return itemRepository.deleteAll();
    }
}
