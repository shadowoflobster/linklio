package com.linklio.linklio.adapters.inbound.rest.controllers;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.application.exceptions.LinkNotFoundException;
import com.linklio.linklio.application.service.LinkServices.CreateLinkService;
import com.linklio.linklio.application.service.LinkServices.LoadLinkService;
import com.linklio.linklio.application.service.LinkServices.UpdateLinkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/link")
@AllArgsConstructor
public class LinkController {
    private CreateLinkService createLinkService;
    private LoadLinkService loadLinkService;
    private UpdateLinkService updateLinkService;

    @GetMapping("/{id}")
    public LinkResponse getLink(@PathVariable Long id){
        return loadLinkService.loadLinkById(id);
    }

    @PostMapping("/add")
    public LinkResponse createLink(@RequestBody LinkRequest linkRequest, Principal principal){
        return createLinkService.createLink(linkRequest, principal.getName());
    }

    @PutMapping("/update/{id}")
    public LinkResponse updateLink(@PathVariable Long id, @RequestBody LinkRequest request, Principal principal){
        return updateLinkService.updateLink(id,request)
                .orElseThrow(() -> new LinkNotFoundException(id));
    }


}
