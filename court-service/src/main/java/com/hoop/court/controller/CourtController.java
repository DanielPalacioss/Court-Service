package com.hoop.court.controller;

import com.hoop.court.dto.request.CourtRequest;
import com.hoop.court.model.Court;
import com.hoop.court.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("court")
@CrossOrigin(origins = "http://localhost:8080")
public class CourtController {

    @Autowired
    private CourtService courtService;

    @GetMapping("/{id}")
    public ResponseEntity<Court> getCourt(@PathVariable String id) {
        return ResponseEntity.ok(courtService.getCourt(id));
    }

    @GetMapping("/cityId/{id}")
    public ResponseEntity<List<Court>> getCourtsByCityId(@PathVariable long id) {
        return ResponseEntity.ok(courtService.getCourtsByCityId(id));
    }

    @PutMapping
    public ResponseEntity<?> updateCourt(@RequestBody Court court) {
        courtService.updateCourt(court);
        return ResponseEntity.status(HttpStatus.CREATED).body("Court updated successfully");
    }

    @PostMapping
    public ResponseEntity<?> saveCourts(@RequestBody CourtRequest courtRequest) {
        courtService.save(courtRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Courts saved successfully");
    }
}
