package org.example.k2_client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    public List<Word> words = new ArrayList<>();
    public Label wordCountLabel;
    public TextField filterField;
    public ListView wordList;

    public HelloController() {
        ClientReceiver.controller = this;
    }

    @FXML
    public void onWordReceived(String word) {
        words.add(new Word(word, LocalTime.now()));
        Platform.runLater(
                () -> {
                    wordCountLabel.setText(String.valueOf(words.size()));
                    this.update();
                }
        );
    }

    public void update() {
        DateTimeFormatter formatter;
        formatter = DateTimeFormatter.ofPattern("HH:mm:ss ");
        wordList.setItems(FXCollections.observableList(
                words.stream().
                        filter(
                                (item) -> item.content.startsWith(filterField.getText())
                        ).
                        sorted((word1, word2) -> word1.content.compareTo(word2.content)).
                        map(
                                (item) -> item.time.format(formatter) + item.content
                        ).
                        toList()
        ));
    }

    public void onEnter() {
        Platform.runLater(
                () -> update()
        );
    }
}