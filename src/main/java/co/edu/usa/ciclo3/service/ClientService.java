package co.edu.usa.ciclo3.service;

import co.edu.usa.ciclo3.entity.Client;
import co.edu.usa.ciclo3.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<Client> getList() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Client getById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> clientId = clientRepository.findById(client.getIdClient());

            if (!clientId.isEmpty()) {
                Client clientBd = clientId.get();

                clientBd.setName(client.getName());
                clientBd.setEmail(client.getEmail());
                clientBd.setPassword(client.getPassword());
                clientBd.setAge(client.getAge());
                clientBd.setMessages(client.getMessages());
                clientBd.setReservations(client.getReservations());

                return clientRepository.save(clientBd);

            } else {

                return client;
            }
        }
        return client;
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

}
