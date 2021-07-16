package org.example.entity;

public class Translation {
    String Heading;
    String Translation;
    String DictionaryName;
    String SoundName;
    String Type;
    String OriginalWord;

    public Translation() {
    }

    public Translation(String heading, String translation, String dictionaryName, String soundName, String type, String originalWord) {
        Heading = heading;
        Translation = translation;
        DictionaryName = dictionaryName;
        SoundName = soundName;
        Type = type;
        OriginalWord = originalWord;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public String getTranslation() {
        return Translation;
    }

    public void setTranslation(String translation) {
        Translation = translation;
    }

    public String getDictionaryName() {
        return DictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        DictionaryName = dictionaryName;
    }

    public String getSoundName() {
        return SoundName;
    }

    public void setSoundName(String soundName) {
        SoundName = soundName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getOriginalWord() {
        return OriginalWord;
    }

    public void setOriginalWord(String originalWord) {
        OriginalWord = originalWord;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "Heading='" + Heading + '\'' +
                ", Translation='" + Translation + '\'' +
                ", DictionaryName='" + DictionaryName + '\'' +
                ", SoundName='" + SoundName + '\'' +
                ", Type='" + Type + '\'' +
                ", OriginalWord='" + OriginalWord + '\'' +
                '}';
    }
}
