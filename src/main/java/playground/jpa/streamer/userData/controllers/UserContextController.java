package playground.jpa.streamer.userData.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import playground.jpa.streamer.userData.repository.AddressRepository;
import playground.jpa.streamer.userData.repository.ContactRepository;
import playground.jpa.streamer.userData.repository.UserRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dummy-data")
public class UserContextController {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;

    @GetMapping("/users")
    public ResponseEntity<?> products(){
        return ResponseEntity.ok(userRepository.findAll());
    }
    @GetMapping("/contacts")
    public ResponseEntity<?> variants(){
        return ResponseEntity.ok(contactRepository.findAll());
    }
    @GetMapping("/addresses")
    public ResponseEntity<?> tags(){
        return ResponseEntity.ok(addressRepository.findAll());
    }
}
