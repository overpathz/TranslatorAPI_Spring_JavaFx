package org.example.entity;

public class TranslateResponse {
    String SourceLanguage;
    String TargetLanguage;
    String Heading;
    Translation Translation;

    public TranslateResponse() {
    }

    public TranslateResponse(String sourceLanguage, String targetLanguage, String heading, Translation translation) {
        SourceLanguage = sourceLanguage;
        TargetLanguage = targetLanguage;
        Heading = heading;
        this.Translation = translation;
    }

    public String getSourceLanguage() {
        return SourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        SourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return TargetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        TargetLanguage = targetLanguage;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public Translation getTranslation() {
        return Translation;
    }

    public void setTranslation(Translation translation) {
        this.Translation = translation;
    }

    @Override
    public String toString() {
        return "TranslateResponse{" +
                "SourceLanguage='" + SourceLanguage + '\'' +
                ", TargetLanguage='" + TargetLanguage + '\'' +
                ", Heading='" + Heading + '\'' +
                ", translation=" + Translation +
                '}';
    }
}

