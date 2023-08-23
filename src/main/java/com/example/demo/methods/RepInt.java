package com.example.demo.methods;

import com.example.demo.model.dipdb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepInt extends JpaRepository<dipdb,Integer> {
        void deleteSolById(Integer id);
}

