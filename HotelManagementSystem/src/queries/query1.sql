-- Print a current bill (total $) for a customer for their stay and all unpaid amenities.
SELECT B.GUESTNO, B.BOOKINGNO, ((100 - S.DISCOUNT)/100 * (R.PRICE * EXTRACT(DAY FROM NUMTODSINTERVAL(DATETO - DATEFROM, 'DAY')) + A.PRICE * AU.QUANTITY)) AS TOTAL_BILL
FROM TAOSEEFAZIZ.ATAK_BOOKING B
JOIN TAOSEEFAZIZ.ATAK_GUEST G ON G.GUESTNO = B.BOOKINGNO
JOIN TAOSEEFAZIZ.ATAK_SUBSCRIPTION S ON S.SUBNO = G.SUBNO
JOIN TAOSEEFAZIZ.ATAK_ROOM R ON B.ROOMNO = R.ROOMNO
LEFT JOIN TAOSEEFAZIZ.ATAK_AMENITYUSAGE AU ON B.BOOKINGNO = AU.BOOKINGNO
LEFT JOIN TAOSEEFAZIZ.ATAK_AMENITY A ON AU.AMENITYNO = A.AMENITYNO
WHERE B.GUESTNO = '&user_input';





