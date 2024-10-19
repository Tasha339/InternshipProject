package com.tasha.readandchat.controller;

import com.tasha.readandchat.service.ProfilePicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/pic")
@RequiredArgsConstructor
public class ProfilePicController {
    private final ProfilePicService profilePicService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPic(@RequestParam("pic") MultipartFile file) throws IOException {
        var picData = profilePicService.uploadProfilePic(file);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/png"))
                .body(picData);
    }

    @GetMapping("/view")
    public ResponseEntity<?> viewPic() throws DataFormatException, IOException {
        var picData = profilePicService.viewPic();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/png"))
                .body(picData);
    }

//    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> updatePic(@RequestParam("pic") MultipartFile file) throws IOException {
//        var pic = profilePicService.updatePic(file);
//        return ResponseEntity.ok()
//                .contentType(MediaType.ALL)
//                .body(pic);
//    }
}

