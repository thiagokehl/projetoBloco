package com.infnet.projeto.usermanagement;
  
import java.io.Serializable;
import java.util.List;
 
import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
import org.apache.commons.codec.digest.DigestUtils;

import com.infnet.projeto.usermanagement.dto.UserDTO;
 
@Entity
@Table(name="users")
@Cacheable(false)
public class User implements Serializable {
          
    @Id
    @Column(unique=true, nullable=false, length=128)
    private String email;
      
    /**
     * A sha512 is 512 bits long -- as its name indicates. If you are using an hexadecimal representation, 
     * each digit codes for 4 bits ; so you need 512 : 4 = 128 digits to represent 512 bits -- so, you need a varchar(128), 
     * or a char(128), as the length is always the same, not varying at all.
     */
    @Column(nullable=false, length=128) //sha-512 + hex
    private String password;
      
          
    @ElementCollection(targetClass = Group.class)
    @CollectionTable(name = "USERS_GROUPS", 
                    joinColumns       = @JoinColumn(name = "email", nullable=false), 
                    uniqueConstraints = { @UniqueConstraint(columnNames={"email","groupname"}) } )
    @Enumerated(EnumType.STRING)
    @Column(name="groupname", length=64, nullable=false)
    private List<Group> groups;
     
    public User(){
         
    }
     
    public User(UserDTO user){
         
        if (user.getPassword1() == null || user.getPassword1().length() == 0
                || !user.getPassword1().equals(user.getPassword2()) )
            throw new RuntimeException("Password 1 and Password 2 have to be equal (typo?)");
         
        this.email        = user.getEmail();
      
        this.password     = DigestUtils.sha256Hex(user.getPassword1() );
    }
 
  
    public String getEmail() {
        return email;
    }
  
    public void setEmail(String email) {
        this.email = email;
    }
  
    /**
     * @return the password in SHA512 HEX representation
     */
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
  
    public List<Group> getGroups() {
        return groups;
    }
 
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
 
    @Override
    public String toString() {
        return "User [email=" + email +  ", password=" + password + ", groups=" + groups + "]";
    }
}