package com.example.demo.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TriggerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createTrigger() {
        String sql = "CREATE TRIGGER update_booking_count\n" +
                "AFTER UPDATE ON booking FOR EACH ROW\n" +
                "BEGIN\n" +
                "    DECLARE bookingStatusId INT;\n" +
                "    SELECT id INTO bookingStatusId FROM booking_status WHERE status= 'ON-GOING';\n" +
                "\n" +
                "    UPDATE user u\n" +
                "    SET u.booking_ongoing = (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM booking b\n" +
                "        WHERE b.customer_id = u.id AND b.booking_status_id = bookingStatusId\n" +
                "    )\n" +
                "    WHERE u.id = NEW.customer_id;\n" +
                "END;";
        entityManager.createNativeQuery(sql).executeUpdate();
    }

    @Transactional
    public void createTriggerEmail() {
        String sql = "CREATE TRIGGER before_insert_user\n" +
                "BEFORE INSERT ON user\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "    CALL ValidateEmail(NEW.email);\n" +
                "END";
        entityManager.createNativeQuery(sql).executeUpdate();
    }
}
