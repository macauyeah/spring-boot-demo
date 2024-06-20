package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Immutable
@Subselect("""
        select book.*, book_search.batch_search_num from
        book_search join book
        on book_search.search_book_uuid = book.uuid
        """)
public class BookSearchJoin {
    @Id
    private String uuid;
    private String name;
    private String batchSearchNum;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchSearchNum() {
        return batchSearchNum;
    }

    public void setBatchSearchNum(String batchSearchNum) {
        this.batchSearchNum = batchSearchNum;
    }

}
