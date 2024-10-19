package com.tasha.readandchat.service;

import com.tasha.readandchat.entity.ProfilePic;
import com.tasha.readandchat.repository.ProfilePicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class ProfilePicService {
    private final ProfilePicRepository picRepository;
    private final UserService userService;
    private final ProfilePicRepository profilePicRepository;
    private final FileUtils fileUtils;

    public byte[] uploadProfilePic(MultipartFile file) throws IOException {
        var currentUser = userService.authenticatedUser();
        var profilePic = ProfilePic.builder()
                .name(file.getOriginalFilename())
                .pictureData(fileUtils.compressFile(file.getBytes()))
                .user(currentUser)
                .build();

        var savedPic =  picRepository.save(profilePic);
//        userService.authenticatedUser().setProfilePic(savedPic);
        return savedPic.getPictureData();
    }

    public byte[] viewPic() throws DataFormatException, IOException {
        return fileUtils.decompressFile(
                userService.authenticatedUser().getProfilePic().getPictureData()
        );
    }

//    public ProfilePic updatePic(MultipartFile file) throws IOException {
//        var currentUser = userService.authenticatedUser();
////        currentUser.getProfilePic().setPictureData(file.getBytes());
////        currentUser.getProfilePic().setName(file.getOriginalFilename());
////
//        var profilePic = ProfilePic.builder()
//                .name(file.getOriginalFilename())
//                .pictureData(file.getBytes())
//                .build();
//        var savedPic = profilePicRepository.save(profilePic);
//        currentUser.setProfilePic(profilePic);
//        return savedPic;
//    }
}
