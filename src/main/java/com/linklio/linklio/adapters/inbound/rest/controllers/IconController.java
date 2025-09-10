package com.linklio.linklio.adapters.inbound.rest.controllers;

import com.linklio.linklio.application.service.LinkServices.LoadAllIconsService;
import com.linklio.linklio.domain.model.Icon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/icons")
public class IconController {
    private final LoadAllIconsService loadAllIconsService;

    @GetMapping
        public ResponseEntity<List<Icon>> loadAllIcons(){
        List<Icon> icons = loadAllIconsService.loadAllIcons();
        return ResponseEntity.ok(icons);
    }

}
