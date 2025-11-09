package com.bose.my_services.repository;
import com.bose.my_services.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//This is the interface Spring uses to automatically generate all the required database access methods.
@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    //  Inheriting basic CRUD operations (save, findAll, etc.) for MessageEntity.
}
