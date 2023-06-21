package com.aml.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aml.Entity.MLRO;

@Repository
public interface MLROrepo extends JpaRepository<MLRO, Integer>{

}
