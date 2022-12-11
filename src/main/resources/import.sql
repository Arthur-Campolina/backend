INSERT INTO TB_ROLES VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO TB_ROLES VALUES (2, 'ROLE_STUDENT', 'Student');

INSERT INTO TB_STUDENTS VALUES (1, NULL, NULL, 'admin@admin.com', 'asda', TRUE, 'admin', 'Admim Neighbourhood', 12, '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '(31)9999-9999', 'Admim Street', 'adm', '312544')
INSERT INTO TB_STUDENTS VALUES (2, NULL, NULL, 'student1@deltaglobal.com', 'asd', TRUE, 'student1', 'Std1 Neighbourhood', 11, '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '(31)9999-9998', 'std1 Street', 'std1', '312543')
INSERT INTO TB_STUDENTS VALUES (3, NULL, NULL, 'student2@deltaglobal.com', 'gsd', TRUE, 'student2', 'Std2 Neighbourhood', 13, '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '(31)9999-9997', 'std2 Street','std2', '312542')

INSERT INTO TB_USERS_ROLES VALUES (1, 1);
INSERT INTO TB_USERS_ROLES VALUES (2, 2);



