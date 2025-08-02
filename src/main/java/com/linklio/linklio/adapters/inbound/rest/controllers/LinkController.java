package com.linklio.linklio.adapters.inbound.rest.controllers;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.application.service.LinkServices.CreateLinkService;
import com.linklio.linklio.application.service.LinkServices.LoadLinkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/link")
@AllArgsConstructor
public class LinkController {
    private CreateLinkService createLinkService;
    private LoadLinkService loadLinkService;

    @GetMapping("/{id}")
    public LinkResponse getLink(@PathVariable Long id){
        return loadLinkService.loadLinkById(id);
    }

    @PostMapping("/add")
    public LinkResponse createLink(@RequestBody LinkRequest linkRequest, Principal principal){
        return createLinkService.createLink(linkRequest, principal.getName());
    }


}
