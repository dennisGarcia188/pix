package com.pix.repository;

import com.pix.model.UserPixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserPixRepository extends JpaRepository<UserPixEntity, UUID> {

    UserPixEntity findByKeyInformation(String key);

    List<UserPixEntity> findByTypeKey(String typeKey);
}
