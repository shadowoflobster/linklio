package com.linklio.linklio.adapters.inbound.rest.controllers;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.application.service.LinkServices.CreateLinkService;
import com.linklio.linklio.domain.model.Link;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/link")
@AllArgsConstructor
public class LinkController {
    private CreateLinkService createLinkService;

    @PostMapping("/add")
    public Link createLink(@RequestBody LinkRequest linkRequest){
        return createLinkService.createLink(linkRequest);

    }
}
