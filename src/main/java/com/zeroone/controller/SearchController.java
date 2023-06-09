package com.zeroone.controller;


import com.zeroone.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<?> getSearchedTickets() {
        return new ResponseEntity<>(searchService.searchTicketsByName(), HttpStatus.OK);
    }

}
