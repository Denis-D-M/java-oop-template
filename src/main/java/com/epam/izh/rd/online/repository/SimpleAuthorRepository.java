package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    Author[] authors = new Author[]{};
    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        authors = Arrays.copyOf(authors, authors.length + 1);
        authors[authors.length - 1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for(Author author : authors){
            if (author.getName().equals(name) && author.getLastName().equals(lastname)){
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        int authorPosition = -1;
        for (int i = 0; i < authors.length; i++) {
            if (author.getName().equals(authors[i].getName()) && author.getLastName().equals(authors[i].getLastName())) {
                authorPosition = i;
                break;
            }
        }
        if (authorPosition == -1) {
            return false;
        }
        Author[] newAuthors = new Author[authors.length - 1];
        System.arraycopy(authors, 0, newAuthors, 0, authorPosition);
        System.arraycopy(authors, authorPosition + 1, newAuthors, authorPosition, authors.length - authorPosition - 1 );
        authors = newAuthors;
        return true;

    }

    @Override
    public int count() {
        return authors.length;
    }
}
