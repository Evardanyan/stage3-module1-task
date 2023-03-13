package com.mjc.school.repository.model.data;

import com.mjc.school.repository.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AuthorData {

    private static final String AUTHORS_FILE_NAME = "authors";
    private static final int NUM_AUTHORS = 20;

    private static final AuthorData INSTANCE = new AuthorData();

    private final List<AuthorModel> authorList;

    private AuthorData() {
        this.authorList = initAuthorList();
    }

    public static AuthorData getInstance() {
        return INSTANCE;
    }

    private List<AuthorModel> initAuthorList() {
        List<AuthorModel> authorList = new ArrayList<>(NUM_AUTHORS);
        for (long i = 1L; i <= NUM_AUTHORS; ++i) {
            authorList.add(new AuthorModel(i, Utils.getRandomContentByFilePath(AUTHORS_FILE_NAME)));
        }
        return authorList;
    }

    public List<AuthorModel> getAuthors() {
        return authorList;
    }

}

