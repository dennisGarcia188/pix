package com.pix.repository;

import com.pix.dtos.UserPixDTO;
import com.pix.model.UserPixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserPixRepository extends JpaRepository<UserPixEntity, UUID> {

    UserPixEntity findByKeyInformation(String key);

    List<UserPixEntity> findByTypeKey(String typeKey);

    @Query(value = "select * from user_pix where holder_name ILIKE %:holderName%", nativeQuery = true)
    List<UserPixEntity> findByHolderName(@Param("holderName") String holderName);
}
