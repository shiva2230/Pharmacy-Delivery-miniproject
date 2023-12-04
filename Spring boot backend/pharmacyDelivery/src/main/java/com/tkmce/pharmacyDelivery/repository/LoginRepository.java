package com.tkmce.pharmacyDelivery.repository;

import com.tkmce.pharmacyDelivery.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {

}
