package dto;



public class InfoDto {

    public InfoDto(String city, String sex, String age, String education, String FN, String LN) {
        this.city = city;
        this.sex = sex;
        this.age = age;
        this.education = education;
        this.FN = FN;
        this.LN = LN;
    }

    public InfoDto() {

    }

    private int id;

    private String LN;

    private String FN;

    private String education;

    private String age;

    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLN() {
        return LN;
    }

    public void setLN(String LN) {
        this.LN = LN;
    }

    public String getFN() {
        return FN;
    }

    public void setFN(String FN) {
        this.FN = FN;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
