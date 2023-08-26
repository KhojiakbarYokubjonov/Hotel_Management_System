-- A query of your choice, subject to these restrictions: 
-- The question must use more than two relations and
-- must be constructed using at least one piece of information gathered from the user.
SELECT M.NAME,S.STARTTIME, S.ENDTIME
FROM TAOSEEFAZIZ.ATAK_MANAGER M
JOIN TAOSEEFAZIZ.ATAK_EMPLOYEE E ON E.MANAGERNO = M.MANAGERNO
JOIN TAOSEEFAZIZ.ATAK_SCHEDULE S ON E.EMPLOYEENO = S.EMPLOYEENO
WHERE E.EMPLOYEENO = '&employee_number';
