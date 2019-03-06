package ru.stqa.pft.mantis.model;

public class MailMessage {

    public final String to;
    public final String text;

    public MailMessage(String recipient, String content) {
        this.to = recipient;
        this.text = content;
    }
}
