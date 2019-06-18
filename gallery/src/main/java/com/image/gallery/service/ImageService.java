package com.image.gallery.service;

import com.image.gallery.dto.UpdateImageInfoDTO;
import com.image.gallery.dto.UploadFileInfoDTO;
import com.image.gallery.dto.ViewImagesDTO;
import com.image.gallery.model.Image;
import com.image.gallery.model.People;
import com.image.gallery.model.Tag;
import com.image.gallery.model.User;
import com.image.gallery.repository.ImageRepository;
import com.image.gallery.repository.PeopleRepository;
import com.image.gallery.repository.TagRepository;
import com.image.gallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    TagRepository tagRepository;

    @Transactional
    public Long addNewImage(MultipartFile file, String username) {

        try {
            String imageName = FilenameUtils.removeExtension(file.getOriginalFilename());
            File convertedFile = convert(file);
            File checkFile = new File("D:/Nowoczesne/front/src/assets/" + imageName + ".jpg");

            if (checkFile.exists()) return -1L;

            BufferedImage bufferedImage;
            Date date = new Date();
            Image image = new Image();
            User user = userRepository.findByUsername(username);
            image.setName(imageName + ".jpg");
            image.setDate(date);
            image.setUser(user);
            imageRepository.save(image);

            bufferedImage = ImageIO.read(convertedFile);

            ImageIO.write(bufferedImage, "jpg", new File("D:/Nowoczesne/front/src/assets/" + imageName + ".jpg"));
            return image.getId();

        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public File convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }


    public ResponseEntity<String> setImageInformation(UploadFileInfoDTO uploadFileInfoDTO) {
        Image image = imageRepository.findById(uploadFileInfoDTO.getImageId()).orElse(null);

        try {
            if (!uploadFileInfoDTO.getPeople().isEmpty()) {
                List<People> peopleList = uploadFileInfoDTO.getPeople().stream()
                        .map(x -> {
                            People person = peopleRepository.findById(x.getId()).orElse(null);
                            return person;
                        }).collect(Collectors.toList());
                image.setPeoples(peopleList);
            } else image.setPeoples(null);

            if (!uploadFileInfoDTO.getTags().isEmpty()) {
                List<Tag> tagList = uploadFileInfoDTO.getTags().stream()
                        .map(x -> {
                            Tag tag = tagRepository.findById(x.getId()).orElse(null);
                            return tag;
                        }).collect(Collectors.toList());
                image.setTags(tagList);
            } else image.setTags(null);


            imageRepository.save(image);

            return ResponseEntity.status(HttpStatus.OK).body("Dodano pomyślnie");
        } catch (Exception x) {
            image.setUser(null);
            image.setPeoples(null);
            image.setTags(null);
            imageRepository.deleteById(image.getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Błąd - nie ustawiono informacji zdjęcia");
        }
    }

    public List<ViewImagesDTO> getAllImages(String username) {
        User user = userRepository.findByUsername(username);

        List<ViewImagesDTO> imagesList = imageRepository.findAllByUserId(user.getId())
                .stream()
                .map(x -> {
                    ViewImagesDTO image = new ViewImagesDTO();
                    image.setId(x.getId());
                    image.setName(x.getName());
                    return image;
                }).collect(Collectors.toList());

        return imagesList;
    }

    public Long updateImageInfo(UpdateImageInfoDTO updateImageInfoDTO) {
        Image image = imageRepository.findByName(updateImageInfoDTO.getImage());
        image.setPeoples(updateImageInfoDTO.getPeople());
        image.setTags(updateImageInfoDTO.getTags());
        imageRepository.save(image);
        return 1L;
    }

    public List<ViewImagesDTO> getImagesByElement(String username, String person, String tag) {
        User user = userRepository.findByUsername(username);

        List<Image> imageList = imageRepository.findAllByUserId(user.getId());
        List<ViewImagesDTO> userImages = new ArrayList<>();
        if(tag.equals("undefined")) tag = null;
        if(person.equals("undefined")) person = null;
        if (person != null && tag == null) {
            userImages = searchByPerson(imageList, person);
            return userImages;
        } else if (person == null && tag != null) {
            userImages = searchByTag(imageList, tag);
            return userImages;
        } else if (person != null && tag != null) {
            userImages = searchByPerson(imageList, person);
            userImages = searchByTagIfPerson(userImages, tag);
            return userImages;
        } else return userImages;


    }

    private List<ViewImagesDTO> searchByTagIfPerson(List<ViewImagesDTO> imageList, String tag) {
        List<ViewImagesDTO> imagesByTagIfPerson = imageList
                .stream()
                .map(x -> {
                    Image image = imageRepository.findByName(x.getName());
                    List<Tag> tags = image.getTags();
                    boolean isTag = tags
                            .stream()
                            .anyMatch(y -> y.getName().equals(tag));
                    if (isTag) {
                        ViewImagesDTO imageDTO = new ViewImagesDTO();
                        imageDTO.setId(image.getId());
                        imageDTO.setName(image.getName());
                        return imageDTO;
                    } else return null;
                }).collect(Collectors.toList());
        while (imagesByTagIfPerson.remove(null)) ;

        return imagesByTagIfPerson;
    }

    private List<ViewImagesDTO> searchByTag(List<Image> imageList, String tag) {
        List<ViewImagesDTO> imagesByTag = imageList
                .stream()
                .map(x -> {
                    List<Tag> tags = x.getTags();
                    boolean isTag = tags
                            .stream()
                            .anyMatch(y -> y.getName().equals(tag));
                    if (isTag) {
                        ViewImagesDTO image = new ViewImagesDTO();
                        image.setId(x.getId());
                        image.setName(x.getName());
                        return image;
                    } else return null;
                }).collect(Collectors.toList());
        while (imagesByTag.remove(null)) ;
        return imagesByTag;
    }

    private List<ViewImagesDTO> searchByPerson(List<Image> imageList, String person) {
        List<ViewImagesDTO> imagesByPerson = imageList
                .stream()
                .map(x -> {
                    List<People> people = x.getPeoples();
                    boolean isPerson = people
                            .stream()
                            .anyMatch(y -> y.getName().equals(person));
                    if (isPerson) {
                        ViewImagesDTO image = new ViewImagesDTO();
                        image.setId(x.getId());
                        image.setName(x.getName());
                        return image;
                    } else return null;

                }).collect(Collectors.toList());
        while (imagesByPerson.remove(null)) ;
        return imagesByPerson;
    }

}
