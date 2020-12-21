package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("credentials")
public class CredentialController {
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String getUserCredentials(Authentication authentication, Model model){
        User currentUser = (User) authentication.getPrincipal();
        model.addAttribute("credentials", credentialService.getUserCredentials(currentUser.getUserId()));
        model.addAttribute("encryptionService", encryptionService);
        return "redirect:/";
    }


    @PostMapping()
    public String create(Authentication authentication, @ModelAttribute Credential credential, Model model){
        User currentUser = (User) authentication.getPrincipal();
        credential.setUserId(currentUser.getUserId());

        if(credential.getCredentialId() != null )
            credentialService.update(credential);
        else
            credentialService.create(credential);

        model.addAttribute("credentials", credentialService.getUserCredentials(currentUser.getUserId()));
        return "redirect:/";
    }

    @GetMapping("delete-credential")
    public String delete(@RequestParam("credentialId") Integer credentialId){

        credentialService.delete(credentialId);
        return "redirect:/";
    }
}
