package com.fns.user.service.domain.rest;

import com.fns.user.service.domain.dto.create.CreateLocation;
import com.fns.user.service.domain.dto.get.LocationResponse;
import com.fns.user.service.domain.ports.input.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/address", produces = "application/vnd.api.v1+json")
public class AddressController {

    private final UserApplicationService userApplicationService;

    public AddressController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping
    public ResponseEntity<LocationResponse> createAddress(@RequestBody CreateLocation createLocation) {
        LocationResponse locationResponse = userApplicationService.createLocation(createLocation);
        return ResponseEntity.ok(locationResponse);
    }

    @GetMapping("/list-address/{id}")
    public ResponseEntity<List<LocationResponse>> getListAddress(@PathVariable("id") UUID id) {
        List<LocationResponse> locations = userApplicationService.getAllLocation(id);
        return ResponseEntity.ok(locations);
    }

}
