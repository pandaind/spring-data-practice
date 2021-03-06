
INSERT INTO Person (ID, NAME, LOCATION, BIRTH_DATE) VALUES (100001, 'person1', 'bangalore',sysdate());
INSERT INTO Person (ID, NAME, LOCATION, BIRTH_DATE) VALUES (100002, 'person2', 'delhi',sysdate());
INSERT INTO Person (ID, NAME, LOCATION, BIRTH_DATE) VALUES (100003, 'person3', 'mumbai',sysdate());

INSERT INTO COURSE (ID, NAME, CREATED_DATE, LAST_UPDATED_DATE, IS_DELETED) VALUES (10001, 'Spring Data JPA', sysdate(), sysdate(), false);
INSERT INTO COURSE (ID, NAME, CREATED_DATE, LAST_UPDATED_DATE, IS_DELETED) VALUES (10002, 'Spring Boot', sysdate(), sysdate(), false);
INSERT INTO COURSE (ID, NAME, CREATED_DATE, LAST_UPDATED_DATE, IS_DELETED) VALUES (10003, 'Spring Kafka', sysdate(), sysdate(), false);
INSERT INTO COURSE (ID, NAME, CREATED_DATE, LAST_UPDATED_DATE, IS_DELETED) VALUES (10004, 'Spring Batch', sysdate(), sysdate(), false);
INSERT INTO COURSE (ID, NAME, CREATED_DATE, LAST_UPDATED_DATE, IS_DELETED) VALUES (10005, 'Spring Integration', sysdate(), sysdate(), false);

INSERT INTO PASSPORT (ID, NUMBER) VALUES (10001, 'PS001');
INSERT INTO PASSPORT (ID, NUMBER) VALUES (10002, 'PS002');
INSERT INTO PASSPORT (ID, NUMBER) VALUES (10003, 'PS003');
INSERT INTO PASSPORT (ID, NUMBER) VALUES (10004, 'PS004');
INSERT INTO PASSPORT (ID, NUMBER) VALUES (10005, 'PS005');

INSERT INTO STUDENT (ID, NAME, PASSPORT_ID) VALUES (10001, 'student1', 10001);
INSERT INTO STUDENT (ID, NAME, PASSPORT_ID) VALUES (10002, 'student2', 10002);
INSERT INTO STUDENT (ID, NAME, PASSPORT_ID) VALUES (10003, 'student3', 10003);
INSERT INTO STUDENT (ID, NAME, PASSPORT_ID) VALUES (10004, 'student4', 10004);
INSERT INTO STUDENT (ID, NAME, PASSPORT_ID) VALUES (10005, 'student5', 10005);

INSERT INTO REVIEW (ID, RATING, DESCRIPTION, COURSE_ID) VALUES (10001, 'FIVE', 'Good', 10001);
INSERT INTO REVIEW (ID, RATING, DESCRIPTION, COURSE_ID) VALUES (10002, 'TWO', 'Bad', 10002);
INSERT INTO REVIEW (ID, RATING, DESCRIPTION, COURSE_ID) VALUES (10003, 'TWO', 'Ok', 10003);
INSERT INTO REVIEW (ID, RATING, DESCRIPTION, COURSE_ID) VALUES (10004, 'FOUR', 'Nice', 10001);
INSERT INTO REVIEW (ID, RATING, DESCRIPTION, COURSE_ID) VALUES (10005, 'FIVE', 'Great', 10001);

INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES ('10001','10001');
INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES ('10002','10002');
INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES ('10003','10003');
INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES ('10004','10004');
INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES ('10005','10005');

