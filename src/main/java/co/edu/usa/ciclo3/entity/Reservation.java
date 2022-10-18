package co.edu.usa.ciclo3.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RESERVATION")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long idReservation;
    @NotNull
    @Column(name = "START_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @NotNull
    @Column(name = "DEVOLUTION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date devolutionDate;
    @NotEmpty
    @Column(name = "STATUS")
    private String status = "created";
    @ManyToOne
    @JoinColumn(name = "cabinId")
    @JsonIgnoreProperties("reservations")
    private Cabin cabin;
    @ManyToOne
    @JoinColumn(name = "clientId")
    @JsonIgnoreProperties({"reservations", "messages"})
    private Client client;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "scoreId", referencedColumnName = "id")
    private Score score;

}
