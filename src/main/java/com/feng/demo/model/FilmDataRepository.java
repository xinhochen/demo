package com.feng.demo.model;

import java.util.List;

public interface FilmDataRepository {
    void putFilmData(FilmData data);
    List getLocationByTitle(String title);
    List getTitleList();
    List getLocationsList();
    List getDirectorList();
    List getWriterList();
}
