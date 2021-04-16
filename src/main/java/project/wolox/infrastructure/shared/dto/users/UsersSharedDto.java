package project.wolox.infrastructure.shared.dto.users;

public class UsersSharedDto {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private AddressDto address;
    private String phone;
    private String website;
    private CompanyDto company;
    private Boolean seeAlbum;
    private Boolean writeAlbum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public Boolean getSeeAlbum() {
        return seeAlbum;
    }

    public void setSeeAlbum(Boolean seeAlbum) {
        this.seeAlbum = seeAlbum;
    }

    public Boolean getWriteAlbum() {
        return writeAlbum;
    }

    public void setWriteAlbum(Boolean writeAlbum) {
        this.writeAlbum = writeAlbum;
    }
}
