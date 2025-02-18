package com.eazybytes.eazyschool.rest;

import com.eazybytes.eazyschool.constants.Constants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.model.Response;
import com.eazybytes.eazyschool.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping(path = "/api/contact",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public class ContactRestController {
    @Autowired
        ContactRepository contactRepository;
    @GetMapping("/getMessagesByStatus")
    public List<Contact>getMessagesByStatus(@RequestParam(name="status")String status) {
        return contactRepository.findByStatus(status);
    }
    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom")String invocationFrom,
                                            @Valid @RequestBody Contact contact){
     log.info(String.format(("Header invocationFrom=%s"),invocationFrom));
     contactRepository.save(contact);
     Response response=new Response();
     response.setStatusCode("200");
     response.setStatusMsg("message saved successfully");
     return ResponseEntity
             .status(HttpStatus.CREATED)
             .header("isMsgSaved","true")
             .body(response);
    }
    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity){
        HttpHeaders headers=requestEntity.getHeaders();
        headers.forEach((key,value)->{
            log.info(String.format("Header '%s'",key, String.join("|", value)));
        });
        Contact contact=requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());
        Response response=new Response("200","message successfully deleted");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq){
        Response response=new Response();
        Optional<Contact> contact=contactRepository.findById(contactReq.getContactId());
        if(contact.isPresent()){
            contact.get().setStatus(Constants.CLOSE);
            contactRepository.save(contact.get());
        }else{
            response.setStatusCode("400");
            response.setStatusMsg("Invalid ContactID received");
            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully closed");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
