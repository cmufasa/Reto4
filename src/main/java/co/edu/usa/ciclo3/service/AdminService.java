package co.edu.usa.ciclo3.service;

import co.edu.usa.ciclo3.entity.Admin;
import co.edu.usa.ciclo3.repository.AdminRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional(readOnly = true)
    public List<Admin> getList() {
        return adminRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Admin getById(Long id) {
        return adminRepository.findById(id).get();
    }

    @Transactional
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
    
    @Transactional
    public Admin update(Admin admin){
        if (admin.getId() != null) {
            Optional<Admin> adminId = adminRepository.findById(admin.getId());
            
            if (!adminId.isEmpty()) {
                Admin adminBd = adminId.get();
                
                adminBd.setMail(admin.getMail());
                adminBd.setName(admin.getName());
                adminBd.setPassword(admin.getPassword());
                
                return adminRepository.save(adminBd);
                               
            } else {
                
                return admin;
            }
        }
        return admin;
    }

    @Transactional
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

}
