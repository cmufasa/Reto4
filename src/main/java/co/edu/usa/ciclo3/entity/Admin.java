package co.edu.usa.ciclo3.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADMIN")
public class Admin implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @NotEmpty
    @Column(name = "NAME", nullable = false, length = 250)
    private String name;
    @NotEmpty
    @Column(name = "MAIL", nullable = false, length = 45)
    private String mail;
    @NotEmpty
    @Column(name = "PASSWORD", nullable = false, length = 45)
    private String password;
    
}
