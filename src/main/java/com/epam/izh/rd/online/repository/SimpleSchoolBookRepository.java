package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return Arrays.stream(schoolBooks).filter(schoolBook -> name.equals(schoolBook.getName())).toArray(SchoolBook[]::new);
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] booksToRemove = findByName(name);
        if (booksToRemove.length == 0) {
            return false;
        }
        SchoolBook[] newArray = new SchoolBook[schoolBooks.length - booksToRemove.length];
        int curIndex = 0;
        for (SchoolBook schoolBook : booksToRemove) {
            for (int i = 0; i < schoolBooks.length; i++) {
                if (schoolBook == schoolBooks[i]) {
                    break;
                }
                newArray[curIndex] = schoolBooks[i];
                curIndex++;

            }
        }
        schoolBooks = newArray;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
