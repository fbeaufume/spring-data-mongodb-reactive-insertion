package com.adeliosys.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class ItemController {

    @Autowired
    private ItemReactiveRepository itemReactiveRepository;

    @Autowired
    private ItemBlockingRepository itemBlockingRepository;

    @GetMapping("/count-items")
    public Mono<Result> count() {
        return itemReactiveRepository.count().map(Result::new);
    }

    @GetMapping("/insert-items")
    public Mono<Void> insert(
            @RequestParam(required = false) boolean optimized,
            @RequestParam(required = false, defaultValue = "5") int count,
            @RequestParam(required = false, defaultValue = "3") int batchSize) {
        // First generate a flux of items
        Flux<Item> items = Flux.fromStream(IntStream.range(0, count).mapToObj(i -> new Item()));

        // Then insert them into the database
        if (optimized) {
            return items.buffer(batchSize).flatMap(itemReactiveRepository::insert).then();
        } else {
            return itemReactiveRepository.insert(items).then();
        }
    }

    /**
     * The blocking equivalent of "/insert-items".
     */
    @GetMapping("/blocking-insert-items")
    public void blockingInsert(
            @RequestParam(required = false, defaultValue = "5") int count,
            @RequestParam(required = false, defaultValue = "3") int batchSize) {
        for (int batch = 0; batch < count / batchSize; batch++) {
            List<Item> items = IntStream.range(0, batchSize).mapToObj(i -> new Item()).collect(Collectors.toList());
            itemBlockingRepository.insert(items);
        }
    }

    @GetMapping("/delete-items")
    public Mono<Void> delete() {
        return itemReactiveRepository.deleteAll();
    }
}
