-- Given a certain date, output the customers that are currently staying at the hotel along with their room
-- numbers. Order by room numbers and group by category of customer.
DEFINE given_date = '&THE_GIVEN_DATE'
SELECT G.NAME, R.ROOMNO
FROM TAOSEEFAZIZ.ATAK_BOOKING B
JOIN  TAOSEEFAZIZ.ATAK_GUEST G ON G.GUESTNO = B.GUESTNO
JOIN TAOSEEFAZIZ.ATAK_ROOM R ON R.ROOMNO = B.ROOMNO
WHERE B.dateFrom <= TO_DATE('&given_date', 'YYYY-MM-DD')
AND B.dateTo >= TO_DATE('&given_date', 'YYYY-MM-DD')
GROUP BY G.SUBNO, G.NAME, R.ROOMNO
ORDER BY R.ROOMNO;
