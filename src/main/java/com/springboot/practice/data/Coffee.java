package com.springboot.practice.data;

// Coffee 클래스 정의
public class Coffee {
    private String koreanName;
    private String englishName;
    private int price;
    private String code;

    public Coffee(String koreanName, String englishName, int price, String code) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.price = price;
        this.code = code;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public int getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }
}
