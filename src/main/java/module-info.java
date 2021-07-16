open module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;
    requires spring.web;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires spring.beans;
    requires spring.core;

    exports org.example;
}

