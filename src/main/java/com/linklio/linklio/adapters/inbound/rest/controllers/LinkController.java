package com.linklio.linklio.adapters.inbound.rest.controllers;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.application.service.LinkServices.CreateLinkService;
import com.linklio.linklio.application.service.LinkServices.LoadLinkService;
import com.linklio.linklio.application.service.LinkServices.UpdateLinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;


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
    public ResponseEntity<?> createLink(@RequestBody LinkRequest linkRequest, Principal principal){
        try {
            LinkResponse linkResponse = createLinkService.createLink(linkRequest, principal.getName());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "success", true,
                            "message", "Link created successfully!",
                            "data", linkResponse
                    ));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "success",false,
                            "message","Failed to create link "+e.getMessage()
                    ));
        }
        }

    @PutMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateLink(@PathVariable Long id, @RequestBody LinkRequest request, Principal principal){
         try {
             LinkResponse linkResponse= updateLinkService.updateLink(id,request,principal.getName());


             return ResponseEntity
                     .status(HttpStatus.OK)
                     .body(Map.of(
                             "success",true,
                             "message","Link updated successfully",
                             "data", linkResponse
                     ));
         }catch (Exception e){
             return ResponseEntity
                     .status(HttpStatus.BAD_REQUEST)
                     .body(Map.of(
                             "success", false,
                             "message","Failed to update link "+e.getMessage()
                     ));
         }
    }


}
