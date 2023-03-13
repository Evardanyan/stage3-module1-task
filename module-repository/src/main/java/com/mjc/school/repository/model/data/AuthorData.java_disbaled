package com.mjc.school.repository.model.data;

import com.mjc.school.repository.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AuthorData {

    private static final String AUTHORS_FILE_NAME = "authors";
    private static AuthorData authorData;
    private List<AuthorModel> authorList;

    private AuthorData() {
        this.init();
    }

    public static AuthorData getAuthorData() {
        if (AuthorData.authorData == null) {
            AuthorData.authorData = new AuthorData();
        }
        return AuthorData.authorData;
    }

    private void init() {
        this.authorList = new ArrayList<AuthorModel>();
        for (long i = 1L; i <= 20L; ++i) {
            this.authorList.add(new AuthorModel(i, Utils.getRandomContentByFilePath("authors")));
        }
    }

    public List<AuthorModel> getAuthorList() {
        return this.authorList;
    }

}
