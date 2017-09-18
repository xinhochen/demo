package com.feng.demo.controller;

import com.feng.demo.model.FilmDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class FilmDataController {
    @Autowired
    private FilmDataRepository filmDataRepository;

    @GetMapping("/getLocationByTitle")
    public List getLocationByTitle(@RequestParam(value = "title") String title) {
        return filmDataRepository.getLocationByTitle(title);
    }

    @GetMapping("/title")
    public List getTitle() {
        return filmDataRepository.getTitleList();
    }

    @GetMapping("/locations")
    public List getLocations() {
        return filmDataRepository.getLocationsList();
    }

    @GetMapping("/director")
    public List getDirector() {
        return filmDataRepository.getDirectorList();
    }

    @GetMapping("/writer")
    public List getWriter() {
        return filmDataRepository.getWriterList();
    }
}
