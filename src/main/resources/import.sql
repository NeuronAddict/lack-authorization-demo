INSERT INTO Profile (id, name, email, address, phone, active, role, grade, salary, annualEvaluation)
VALUES (1, 'alice', 'alice@example.com', '123 Admin St', '555-0100', true, 'admin', 0, 55000.0,
        'Alice est une employée exceptionnelle. Elle fait preuve d''un grand leadership.');
INSERT INTO Profile (id, name, email, address, phone, active, role, grade, salary, annualEvaluation)
VALUES (2, 'bob', 'bob@example.com', '456 User Ave', '555-0101', true, 'user', 1, 42000.0,
        'Bob a fait beaucoup de progrès cette année. Il doit continuer à se former sur les nouvelles technologies.');
INSERT INTO Profile (id, name, email, address, phone, active, role, grade, salary, annualEvaluation)
VALUES (3, 'eve', 'eve@example.com', '789 User Blvd', '555-0102', true, 'user', 2, 45000.0,
        'Eve est très rigoureuse dans son travail. Ses résultats sont constants.');

INSERT INTO Message (id, content, timestamp, profile_id)
VALUES (nextval('Message_SEQ'), 'Je serai en congé maladie pour une semaine.', CURRENT_TIMESTAMP, 1);
INSERT INTO Message (id, content, timestamp, profile_id)
VALUES (nextval('Message_SEQ'), 'Quand est ce que mon augmentation sera présente sur ma fiche de paie ?',
        CURRENT_TIMESTAMP, 1);
INSERT INTO Message (id, content, timestamp, profile_id)
VALUES (nextval('Message_SEQ'), 'Deuxième message pour Alice', CURRENT_TIMESTAMP, 1);
INSERT INTO Message (id, content, timestamp, profile_id)
VALUES (nextval('Message_SEQ'), 'Combien de congés me reste-t-il cette année ?', CURRENT_TIMESTAMP, 2);
