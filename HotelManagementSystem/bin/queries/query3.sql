-- Print the schedule of staff given a week (input the start date of the week by the user). A schedule
-- contains the list of staff members working that week and a staff member’s working hours (start and stop
-- times).
SELECT E.NAME, S.STARTTIME, S.ENDTIME
FROM TAOSEEFAZIZ.ATAK_EMPLOYEE E
JOIN TAOSEEFAZIZ.ATAK_SCHEDULE S ON S.EMPLOYEENO = E.EMPLOYEENO
WHERE S.STARTTIME >= TO_DATE('&START_DATE', 'YYYY-MM-DD')
AND S.ENDTIME <= (TO_DATE('&START_DATE', 'YYYY-MM-DD') + INTERVAL '8' DAY);



