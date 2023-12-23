package com.example.demo.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProcedureCreationService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createProcedure() {
        String procedureQuery =
                "CREATE PROCEDURE MakeInvoice(IN bookingId VARCHAR(50), IN adminId INT)\n" +
                "                  BEGIN\n" +
                "                      DECLARE bookingPrice DECIMAL(10, 2);\n" +
                "                      DECLARE bookingStatusId INT;\n" +
                "                      DECLARE invoiceId varchar(50);\n" +
                "                  \n" +
                "                      set invoiceId = UUID();\n" +
                "                  \n" +
                "                      -- 获取预订价格\n" +
                "                      SELECT price_to_pay INTO bookingPrice FROM Booking WHERE id = bookingId;\n" +
                "                  \n" +
                "                      -- 创建发票\n" +
                "                      INSERT INTO Invoice (`id`,`admin-in-charge`, `paid`, `total_price`, `booking_id`) VALUES (invoiceId, adminId, true, bookingPrice, bookingId);\n" +
                "                  \n" +
                "                      -- 更新预订状态为“进行中”\n" +
                "                      SELECT id INTO bookingStatusId FROM booking_status WHERE status= 'ON-GOING';\n" +
                "                  \n" +
                "                      UPDATE Booking\n" +
                "                      SET booking_status_id = bookingStatusId, invoice_id = invoiceId\n" +
                "                      WHERE id = bookingId;\n" +
                        "   SELECT * FROM invoice where id = invoiceId;" +
                "                  END";

        entityManager.createNativeQuery(procedureQuery).executeUpdate();
    }

    @Transactional
    public void createProcedureValidateEmail() {
        String procedureQuery =
                "CREATE PROCEDURE ValidateEmail(email VARCHAR(255))\n" +
                        "BEGIN\n" +
                        "    IF email NOT REGEXP '^[a-zA-Z0-9]+[-._a-zA-Z0-9]*@[a-zA-Z0-9]+([-.][a-zA-Z0-9]+)*\\\\.[a-zA-Z]{2,}$' THEN\n" +
                        "        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Check constraint ''EmailFormatCheck'' is violated.';\n" +
                        "    END IF;\n" +
                        "END";
        entityManager.createNativeQuery(procedureQuery).executeUpdate();
    }
}
