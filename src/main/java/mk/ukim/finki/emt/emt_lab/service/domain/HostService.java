package mk.ukim.finki.emt.emt_lab.service.domain;


import mk.ukim.finki.emt.emt_lab.model.domain.Host;

import java.util.*;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(Host host);
    Optional<Host> update(Long id, Host host);
    void deleteById(Long id);


}