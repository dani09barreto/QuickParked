package org.devscite.Entities.Model;

public class Admin extends UserParking {
    private Integer sajnsa;
    public Admin(String username, String passWord) {
        super(username, passWord);
    }

    public Integer getSajnsa() {
        return sajnsa;
    }

    public void setSajnsa(Integer sajnsa) {
        this.sajnsa = sajnsa;
    }
}
