package iducs201912022.javaweb.blog201912022.model;

public class MemberDTO {
    private String fullname;
    private String email;

    public String getFullname() { // boiler-plate-code : 상용구, 관용구
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
