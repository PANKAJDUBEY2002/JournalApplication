package com.application.journalApplication.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private String id;
    private String title;
    private String content;
    private LocalDateTime date;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }



    public LocalDateTime getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }
}
