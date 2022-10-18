package co.edu.usa.ciclo3.custom;

import co.edu.usa.ciclo3.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountClient {
    
    private Long total;
    private Client client;
    
}
