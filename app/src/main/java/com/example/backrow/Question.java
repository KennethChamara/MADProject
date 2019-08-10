package com.example.backrow;

public class Question {

    private String question;
    private String opton1;
    private String opton2;
    private String opton3;
    private int answerNr;


    public Question(){



    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpton1() {
        return opton1;
    }

    public void setOpton1(String opton1) {
        this.opton1 = opton1;
    }

    public String getOpton2() {
        return opton2;
    }

    public void setOpton2(String opton2) {
        this.opton2 = opton2;
    }

    public String getOpton3() {
        return opton3;
    }

    public void setOpton3(String opton3) {
        this.opton3 = opton3;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    public Question(String question, String opton1, String opton2, String opton3, int answerNr) {
        this.question = question;
        this.opton1 = opton1;
        this.opton2 = opton2;
        this.opton3 = opton3;
        this.answerNr = answerNr;
    }


}
